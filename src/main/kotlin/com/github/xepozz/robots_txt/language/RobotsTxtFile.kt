package com.github.xepozz.robots_txt.language

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class RobotsTxtFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, RobotsTxtLanguage.INSTANCE) {
    override fun getFileType() = RobotsTxtFileType
}
