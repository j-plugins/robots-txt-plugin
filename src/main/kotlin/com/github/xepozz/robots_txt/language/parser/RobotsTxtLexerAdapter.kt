package com.github.xepozz.robots_txt.language.parser

import com.intellij.lexer.FlexAdapter

class RobotsTxtLexerAdapter : FlexAdapter(RobotsTxtLexer(null))