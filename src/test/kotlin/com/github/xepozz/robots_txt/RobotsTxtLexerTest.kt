package com.github.xepozz.robots_txt

import com.github.xepozz.robots_txt.language.parser.RobotsTxtLexerAdapter
import com.github.xepozz.robots_txt.language.psi.RobotsTxtTypes
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RobotsTxtLexerTest {
    @Test
    fun returnsBadCharacterTokenForUnexpectedInput() {
        val tokens = lex("\uFEFFUser-agent: *")

        assertEquals(TokenType.BAD_CHARACTER, tokens.first().type)
        assertEquals("\uFEFF", tokens.first().text)
    }

    @Test
    fun splitsInlineCommentsFromValues() {
        val tokens = lex(
            """
            Allow: /public # comment
            Disallow: # empty
            User-agent: *
            """.trimIndent()
        ).withoutWhitespace()

        assertContainsToken(tokens, RobotsTxtTypes.TEXT, "/public")
        assertContainsToken(tokens, RobotsTxtTypes.COMMENT, "# comment")
        assertContainsToken(tokens, RobotsTxtTypes.COMMENT, "# empty")
        assertContainsToken(tokens, RobotsTxtTypes.TEXT, "*")
    }

    @Test
    fun keepsSpacesInsideValueAndStopsAtComment() {
        val tokens = lex("Clean-param: ref /path # comment").withoutWhitespace()

        assertContainsToken(tokens, RobotsTxtTypes.TEXT, "Clean-param")
        assertContainsToken(tokens, RobotsTxtTypes.TEXT, "ref /path")
        assertContainsToken(tokens, RobotsTxtTypes.COMMENT, "# comment")
    }

    @Test
    fun handlesCrLfWithoutIncludingCarriageReturnInValueOrComment() {
        val tokens = lex("Allow: /public\r\n# comment\r\nDisallow: /private").withoutWhitespace()

        assertContainsToken(tokens, RobotsTxtTypes.TEXT, "/public")
        assertContainsToken(tokens, RobotsTxtTypes.COMMENT, "# comment")
        assertTrue(
            "Carriage return must stay inside EOL tokens only, got $tokens",
            tokens.filter { it.type != RobotsTxtTypes.EOL }.none { it.text.contains('\r') }
        )
    }

    private fun lex(text: String): List<Token> {
        val lexer = RobotsTxtLexerAdapter()
        lexer.start(text)

        val tokens = mutableListOf<Token>()
        while (true) {
            val tokenType = lexer.tokenType ?: break
            tokens += Token(tokenType, lexer.tokenText)
            lexer.advance()
        }
        return tokens
    }

    private fun List<Token>.withoutWhitespace() =
        filter { it.type != TokenType.WHITE_SPACE && it.type != RobotsTxtTypes.EOL }

    private fun assertContainsToken(tokens: List<Token>, type: IElementType, text: String) {
        assertTrue(
            "Expected token $type with text '$text', got $tokens",
            tokens.any { it.type == type && it.text == text }
        )
    }

    private data class Token(val type: IElementType, val text: String)
}
