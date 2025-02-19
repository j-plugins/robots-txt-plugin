package com.github.xepozz.robots_txt.language.psi.impl

import com.github.xepozz.robots_txt.language.psi.RobotsTxtValue
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry

object RobotsTxtImplUtil {
    @JvmStatic
    fun getValue(element: RobotsTxtValue): String = element.text

    @JvmStatic
    fun getReferences(element: PsiElement): Array<PsiReference> =
        ReferenceProvidersRegistry.getReferencesFromProviders(element)
}