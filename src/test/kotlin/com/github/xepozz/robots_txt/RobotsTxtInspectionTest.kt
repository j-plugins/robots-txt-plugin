package com.github.xepozz.robots_txt

import com.github.xepozz.robots_txt.ide.inspection.RobotsTxtInspection
import com.github.xepozz.robots_txt.language.RobotsTxtFile
import com.github.xepozz.robots_txt.language.RobotsTxtFileType
import com.intellij.codeInspection.InspectionManager
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElement
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class RobotsTxtInspectionTest : BasePlatformTestCase() {
    fun testLowercaseDirectivesAreValidated() {
        val problems = inspect(
            """
            allow: private
            sitemap: /sitemap.xml 
            """.trimIndent()
        )

        assertContainsProblem(problems, "allow's value must start with slash: private")
        assertContainsProblem(problems, "Sitemap URL should be absolute: /sitemap.xml")
    }

    fun testSitemapSchemeIsCaseInsensitive() {
        val problems = inspect("Sitemap: HTTPS://example.com/sitemap.xml")

        assertFalse(
            problems.toString(),
            problems.any { it.contains("Sitemap URL should be absolute") }
        )
    }

    fun testEmptySitemapIsReported() {
        val problems = inspect("Sitemap:")

        assertContainsProblem(problems, "Sitemap URL is missing")
    }

    fun testUserAgentAfterAllowanceInSameBlockIsReported() {
        val problems = inspect(
            """
            User-agent: A
            Disallow: /
            User-agent: B
            """.trimIndent()
        )

        assertContainsProblem(problems, "User-agent directive must not follow after allowance directives")
    }

    fun testUserAgentAfterBlankLineStartsNewBlock() {
        val problems = inspect(
            """
            User-agent: A
            Disallow: /

            User-agent: B
            Allow: /
            """.trimIndent()
        )

        assertFalse(
            problems.toString(),
            problems.any { it.contains("User-agent directive must not follow after allowance directives") }
        )
    }

    fun testInlineCommentAfterDisallowDoesNotCreatePathProblem() {
        val problems = inspect("Disallow: # empty disallow is valid")

        assertFalse(
            problems.toString(),
            problems.any { it.contains("value must start with slash") }
        )
    }

    private fun inspect(text: String): List<String> {
        val file = myFixture.configureByText(RobotsTxtFileType.INSTANCE, text) as RobotsTxtFile
        val holder = ProblemsHolder(InspectionManager.getInstance(project), file, true)
        val visitor = RobotsTxtInspection().buildVisitor(holder, true)

        file.accept(visitor)
        file.acceptChildrenRecursively(visitor)

        return holder.results.map { it.descriptionTemplate }
    }

    private fun PsiElement.acceptChildrenRecursively(visitor: com.intellij.psi.PsiElementVisitor) {
        acceptChildren(visitor)
        children.forEach { it.acceptChildrenRecursively(visitor) }
    }

    private fun assertContainsProblem(problems: List<String>, expected: String) {
        assertTrue(problems.toString(), problems.any { it.contains(expected) })
    }
}
