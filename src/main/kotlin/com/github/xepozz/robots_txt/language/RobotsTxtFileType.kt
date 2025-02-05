package com.github.xepozz.robots_txt.language

import com.github.xepozz.robots_txt.RobotsTxtIcons
import com.intellij.openapi.fileTypes.LanguageFileType

object RobotsTxtFileType : LanguageFileType(RobotsTxtLanguage.INSTANCE) {
    override fun getName() = "robots.txt File"
    override fun getDescription() = "robots.txt file for controlling web crawler access"
    override fun getDefaultExtension() = "txt"
    override fun getIcon() = RobotsTxtIcons.FILE
}