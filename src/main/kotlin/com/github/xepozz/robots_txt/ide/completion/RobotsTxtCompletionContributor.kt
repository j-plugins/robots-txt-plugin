package com.github.xepozz.robots_txt.ide.completion

import com.github.xepozz.robots_txt.RobotsTxtIcons
import com.github.xepozz.robots_txt.language.psi.RobotsTxtDirective
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.project.DumbAware
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext

class RobotsTxtCompletionContributor : CompletionContributor(), DumbAware {
    val keywords = listOf(
        "Sitemap",
        "Crawl-delay",
        "Allow",
        "Disallow",
        "User-agent",
    )

    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement()
                .withParent(RobotsTxtDirective::class.java),
            object : CompletionProvider<CompletionParameters>(), DumbAware {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    result.caseInsensitive()
                    keywords.forEach { keyword ->
                        result.addElement(
                            LookupElementBuilder.create(keyword)
                                .withIcon(RobotsTxtIcons.FILE)
                        )
                    }
                }
            }
        )
    }
}

//PlatformPatterns.psiElement().withParent(PlatformPatterns.psiElement(RobotsTxtDirective::class.java)).accepts(element)