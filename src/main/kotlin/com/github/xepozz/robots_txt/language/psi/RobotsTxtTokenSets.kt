package com.github.xepozz.robots_txt.language.psi

import com.intellij.psi.tree.TokenSet

object RobotsTxtTokenSets {
    val EMPTY_SET = TokenSet.EMPTY

    val COMMENTS = TokenSet.create(RobotsTxtTypes.COMMENT)
    val STRING_LITERALS = TokenSet.create(RobotsTxtTypes.DIRECTIVE, RobotsTxtTypes.VALUE)
    val WHITESPACES = TokenSet.WHITE_SPACE
}