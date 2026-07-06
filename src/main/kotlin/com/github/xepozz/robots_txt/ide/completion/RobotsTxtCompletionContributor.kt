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
    private val keywords = listOf<Pair<String, String>>(
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
                    val result = result.caseInsensitive()
                    keywords.forEach { keyword ->
                        result.addElement(
                            LookupElementBuilder.create(keyword.first)
                                .withIcon(RobotsTxtIcons.FILE)
                                .withInsertHandler { context, _ ->
                                    val document = context.document
                                    val insertionOffset = context.selectionEndOffset

                                    val delimiter = ": "
                                    var delimiterStart = insertionOffset
                                    val text = document.charsSequence
                                    while (
                                        delimiterStart < document.textLength &&
                                        text[delimiterStart].isHorizontalWhitespace()
                                    ) {
                                        delimiterStart++
                                    }

                                    if (delimiterStart >= document.textLength || text[delimiterStart].isLineBreak()) {
                                        val postfix = delimiter + keyword.second
                                        document.insertString(insertionOffset, postfix)
                                        context.editor.caretModel.moveToOffset(insertionOffset + postfix.length)
                                    } else if (text[delimiterStart] != ':') {
                                        document.insertString(
                                            insertionOffset,
                                            if (delimiterStart == insertionOffset) delimiter else ":"
                                        )
                                        context.editor.caretModel.moveToOffset(insertionOffset + delimiter.length)
                                    } else {
                                        var valueOffset = delimiterStart + 1
                                        if (
                                            valueOffset >= document.textLength ||
                                            document.charsSequence[valueOffset].isLineBreak()
                                        ) {
                                            document.insertString(valueOffset, " " + keyword.second)
                                            valueOffset += 1 + keyword.second.length
                                        } else {
                                            if (!document.charsSequence[valueOffset].isHorizontalWhitespace()) {
                                                document.insertString(valueOffset, " ")
                                            }
                                            valueOffset++
                                            while (
                                                valueOffset < document.textLength &&
                                                document.charsSequence[valueOffset].isHorizontalWhitespace()
                                            ) {
                                                valueOffset++
                                            }
                                        }
                                        context.editor.caretModel.moveToOffset(valueOffset)
                                    }
                                }
                        )
                    }
                }
            }
        )
    }

    private fun Char.isHorizontalWhitespace() = this == ' ' || this == '\t'

    private fun Char.isLineBreak() = this == '\n' || this == '\r'
}
