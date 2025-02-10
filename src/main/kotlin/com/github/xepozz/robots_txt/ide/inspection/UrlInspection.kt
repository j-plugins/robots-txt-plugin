package com.github.xepozz.robots_txt.ide.inspection

import com.github.xepozz.robots_txt.language.RobotsTxtFile
import com.github.xepozz.robots_txt.language.psi.RobotsTxtRule
import com.github.xepozz.robots_txt.language.psi.RobotsTxtTypes
import com.github.xepozz.robots_txt.language.psi.RobotsTxtVisitor
import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiFile
import com.intellij.psi.util.elementType

class RobotsTxtInspection : LocalInspectionTool() {
    override fun getDisplayName() = "Robots.txt rules validation"
    override fun getShortName() = "RobotsTxtInspection"
    override fun isAvailableForFile(file: PsiFile) = file is RobotsTxtFile

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : RobotsTxtVisitor() {
            override fun visitRule(rule: RobotsTxtRule) {
                when (rule.directive.text) {
                    "Sitemap" -> {
                        validateSitemap(holder, rule)
                    }

                    "Allow", "Disallow" -> {
                        validateAllowDisallow(holder, rule)
                    }
                }

                super.visitRule(rule)
            }

            override fun visitFile(file: PsiFile) {
                val file = file as? RobotsTxtFile ?: return
                val children = file.children

                var eolCount = 0
                var blockStarted = false
                var inBlock = false
                var hasUserAgent = false
                var hasAllowance = false

                for (element in children) {
                    println("element: $element / ${element.elementType}")

                    if (!blockStarted && element.elementType != RobotsTxtTypes.EOL) {
                        println("block started")
                        blockStarted = true
                    }

                    when (element.elementType) {
                        RobotsTxtTypes.RULE -> {
                            val element = element as RobotsTxtRule
                            when (element.directive.text.lowercase()) {
                                "user-agent" -> {
                                    if (hasAllowance) {
                                        holder.registerProblem(
                                            element,
                                            "User-agent directive must not follow after allowance directives",
                                            ProblemHighlightType.GENERIC_ERROR_OR_WARNING,
                                        )
                                    }
                                    hasUserAgent = true
                                }

                                "allow", "disallow" -> {
                                    hasAllowance = true
                                }
                            }
                        }

                        RobotsTxtTypes.EOL -> {
                            if (blockStarted) {
                                eolCount++
                            }
                            if (eolCount >= 2) {
                                blockStarted = false
                                hasUserAgent = false
                                hasAllowance = false
                                eolCount = 0
                            }
                        }
                    }
                    if (element is RobotsTxtRule || element.elementType == RobotsTxtTypes.EOL) {
                        inBlock = true
                    }
                }

                super.visitFile(file)
            }

            private fun validateSitemap(holder: ProblemsHolder, rule: RobotsTxtRule) {
                val value = rule.value ?: return
                val url = value.text

                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    holder.registerProblem(
                        value,
                        "Sitemap URL should be absolute: $url",
                        ProblemHighlightType.ERROR,
                    )
                }
            }

            private fun validateAllowDisallow(holder: ProblemsHolder, rule: RobotsTxtRule) {
                val directive = rule.directive
                val value = rule.value ?: return

                if (!value.text.startsWith("/")) {
                    holder.registerProblem(
                        value,
                        "${directive.text}'s value must start with slash: $directive",
                        ProblemHighlightType.ERROR,
                    )
                }
            }
        }
    }
}