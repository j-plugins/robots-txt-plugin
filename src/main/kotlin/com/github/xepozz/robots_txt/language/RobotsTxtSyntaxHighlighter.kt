package com.github.xepozz.robots_txt.language

import com.github.xepozz.robots_txt.language.parser.RobotsTxtLexerAdapter
import com.github.xepozz.robots_txt.language.psi.RobotsTxtTypes
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class RobotsTxtSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer() = RobotsTxtLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType) = when (tokenType) {
        RobotsTxtTypes.COMMENT -> COMMENT_KEYS
        RobotsTxtTypes.DIRECTIVE -> DIRECTIVE_KEYS
        RobotsTxtTypes.VALUE -> VALUE_KEYS
        TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
        else -> EMPTY_KEYS
    }

    companion object {
        private val BAD_CHAR_KEYS = arrayOf(
            HighlighterColors.BAD_CHARACTER,
        )

        private val COMMENT_KEYS = arrayOf(
            DefaultLanguageHighlighterColors.DOC_COMMENT
        )
        private val DIRECTIVE_KEYS = arrayOf(
            DefaultLanguageHighlighterColors.IDENTIFIER
        )
        private val VALUE_KEYS = arrayOf(
            DefaultLanguageHighlighterColors.STRING
        )
        private val EMPTY_KEYS = emptyArray<TextAttributesKey>()
    }
}