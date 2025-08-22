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
    val keywords = listOf<Pair<String, String>>(
        "Sitemap" to "https://",
        "Allow" to "/",
        "Disallow" to "/",
        "User-agent" to "",
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
                            LookupElementBuilder.create(keyword.first)
                                .withIcon(RobotsTxtIcons.FILE)
                                .withInsertHandler { context, _ ->
                                    val document = context.document
                                    val insertionOffset = context.selectionEndOffset

                                    // Insert ": /" after the selected keyword
                                    val delimiter = ": "
                                    val postfix = delimiter + keyword.second

                                    // todo: check if postfix already exist
                                    val realPostfix = document.charsSequence.substring(insertionOffset)
                                    if (!realPostfix.startsWith(delimiter)) {
                                        document.insertString(insertionOffset, postfix)
                                        context.editor.caretModel.moveToOffset(insertionOffset + postfix.length)
                                    } else {
                                        context.editor.caretModel.moveToOffset(insertionOffset + delimiter.length)
                                    }
                                }
                        )
                    }
                }
            }
        )
    }
}
