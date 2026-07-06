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
                when (rule.directive.text.lowercase()) {
                    "sitemap" -> {
                        validateSitemap(holder, rule)
                    }

                    "allow", "disallow" -> {
                        validateAllowDisallow(holder, rule)
                    }
                }

                super.visitRule(rule)
            }

            override fun visitFile(file: PsiFile) {
                val file = file as? RobotsTxtFile ?: return
                val children = file.children

                var eolCount = 0
                var hasAllowance = false

                for (element in children) {
                    when (element.elementType) {
                        RobotsTxtTypes.RULE -> {
                            eolCount = 0
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
                                }

                                "allow", "disallow" -> {
                                    hasAllowance = true
                                }
                            }
                        }

                        RobotsTxtTypes.EOL -> {
                            eolCount++
                            if (eolCount >= 2) {
                                hasAllowance = false
                                eolCount = 0
                            }
                        }

                        RobotsTxtTypes.COMMENT -> {
                            eolCount = 0
                        }
                    }
                }

                super.visitFile(file)
            }

            private fun validateSitemap(holder: ProblemsHolder, rule: RobotsTxtRule) {
                val value = rule.value ?: run {
                    holder.registerProblem(
                        rule,
                        "Sitemap URL is missing",
                        ProblemHighlightType.ERROR,
                    )
                    return
                }
                val url = value.text.trim()

                if (!url.startsWith("http://", ignoreCase = true) && !url.startsWith("https://", ignoreCase = true)) {
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
                val path = value.text.trim()

                if (!path.startsWith("/")) {
                    holder.registerProblem(
                        value,
                        "${directive.text}'s value must start with slash: $path",
                        ProblemHighlightType.ERROR,
                    )
                }
            }
        }
    }
}
