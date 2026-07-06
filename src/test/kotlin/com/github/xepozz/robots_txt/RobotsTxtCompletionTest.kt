package com.github.xepozz.robots_txt

import com.github.xepozz.robots_txt.language.RobotsTxtFileType
import com.intellij.codeInsight.lookup.Lookup
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class RobotsTxtCompletionTest : BasePlatformTestCase() {
    fun testCompletionInsertsDefaultPostfix() {
        complete("Dis<caret>", "Disallow: /")
    }

    fun testCompletionDoesNotDuplicateExistingColon() {
        complete("All<caret>:/private", "Allow: /private")
    }

    fun testCompletionDoesNotDuplicateExistingColonWithSpaces() {
        complete("Site<caret> : https://example.com/sitemap.xml", "Sitemap : https://example.com/sitemap.xml")
    }

    fun testCompletionAddsValuePlaceholderBeforeLineBreakAfterExistingColon() {
        complete("User-ag<caret>:\nDisallow: /", "User-agent: \nDisallow: /")
    }

    fun testCompletionAddsOnlyColonBeforeExistingValue() {
        complete("All<caret> /private", "Allow: /private")
    }

    fun testCompletionAddsOnlyColonBeforeAdjacentExistingValue() {
        complete("All<caret>/private", "Allow: /private")
    }

    private fun complete(before: String, after: String) {
        myFixture.configureByText(RobotsTxtFileType.INSTANCE, before)
        myFixture.completeBasic()
        myFixture.lookup?.let {
            myFixture.finishLookup(Lookup.NORMAL_SELECT_CHAR)
        }
        myFixture.checkResult(after)
    }
}
