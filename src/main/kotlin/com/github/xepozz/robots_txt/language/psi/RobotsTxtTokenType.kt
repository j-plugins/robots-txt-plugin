package com.github.xepozz.robots_txt.language.psi

import com.github.xepozz.robots_txt.language.RobotsTxtLanguage
import com.intellij.psi.tree.IElementType

class RobotsTxtTokenType(debugName: String) : IElementType(debugName, RobotsTxtLanguage.INSTANCE) {
    override fun toString() = "RobotsTxtTokenType." + super.toString()
}