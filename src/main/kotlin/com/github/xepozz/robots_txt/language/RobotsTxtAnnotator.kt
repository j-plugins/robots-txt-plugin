package com.github.xepozz.robots_txt.language

import com.github.xepozz.robots_txt.language.psi.RobotsTxtDirective
import com.github.xepozz.robots_txt.language.psi.RobotsTxtValue
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement

class RobotsTxtAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is RobotsTxtDirective -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .textAttributes(DIRECTIVE_HIGHLIGHT)
                    .create()
            }

            is RobotsTxtValue -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .textAttributes(VALUE_HIGHLIGHT)
                    .create()
            }
        }
    }

    companion object {
        val DIRECTIVE_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
            "GITATTRIBUTES_PATTERN",
            DefaultLanguageHighlighterColors.KEYWORD,
        )
        private val VALUE_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
            "GITATTRIBUTES_IDENTIFIER",
            DefaultLanguageHighlighterColors.STRING,
        )
    }
}