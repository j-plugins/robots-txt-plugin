package com.github.xepozz.robots_txt

import com.github.xepozz.robots_txt.language.RobotsTxtAnnotator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RobotsTxtResourcesTest {
    @Test
    fun inspectionDescriptionIsNotTemplateText() {
        val resource = javaClass.classLoader.getResource("inspectionDescriptions/RobotsTxtInspection.html")
        assertNotNull(resource)

        val text = requireNotNull(resource).readText()
        assertFalse(text.contains("Write your description here."))
        assertTrue(text.contains("Reports invalid robots.txt directives and values."))
    }

    @Test
    fun directiveHighlightKeyIsRobotsSpecific() {
        assertEquals("ROBOTS_TXT_DIRECTIVE", RobotsTxtAnnotator.DIRECTIVE_HIGHLIGHT.externalName)
    }

    @Test
    fun fileTypeRegistrationUsesExactRobotsTxtName() {
        val resource = javaClass.classLoader.getResource("META-INF/plugin.xml")
        assertNotNull(resource)

        val text = requireNotNull(resource).readText()
        assertTrue(text.contains("fileNamesCaseInsensitive=\"robots.txt\""))
        assertFalse(text.contains("patterns=\"*robots.txt\""))
    }
}
