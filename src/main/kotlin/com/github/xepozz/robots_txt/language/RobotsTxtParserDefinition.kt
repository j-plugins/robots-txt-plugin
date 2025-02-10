package com.github.xepozz.robots_txt.language

import com.github.xepozz.robots_txt.language.parser.RobotsTxtLexerAdapter
import com.github.xepozz.robots_txt.language.parser.RobotsTxtParser
import com.github.xepozz.robots_txt.language.psi.RobotsTxtTokenSets
import com.github.xepozz.robots_txt.language.psi.RobotsTxtTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.psi.FileViewProvider
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class RobotsTxtParserDefinition : ParserDefinition {
    override fun createLexer(project: com.intellij.openapi.project.Project?) = RobotsTxtLexerAdapter()

    override fun getWhitespaceTokens() = TokenSet.WHITE_SPACE

    override fun createParser(project: com.intellij.openapi.project.Project?) = RobotsTxtParser()

    override fun getFileNodeType() = FILE

    override fun getCommentTokens() = RobotsTxtTokenSets.COMMENTS

    override fun getStringLiteralElements() = TokenSet.EMPTY

    override fun createElement(node: ASTNode) = RobotsTxtTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider) = RobotsTxtFile(viewProvider)

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode?, right: ASTNode?) =
        ParserDefinition.SpaceRequirements.MAY


    companion object {
        val FILE = IFileElementType(RobotsTxtLanguage.INSTANCE)
    }
}
