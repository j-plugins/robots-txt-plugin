package com.github.xepozz.robots_txt.language.psi

import com.github.xepozz.robots_txt.language.RobotsTxtLanguage
import com.intellij.psi.tree.IElementType

class RobotsTxtElementType(debugName: String) :
    IElementType("RobotsTxtElementType($debugName)", RobotsTxtLanguage.INSTANCE)
