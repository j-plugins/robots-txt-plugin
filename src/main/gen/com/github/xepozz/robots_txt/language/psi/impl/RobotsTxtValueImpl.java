// This is a generated file. Not intended for manual editing.
package com.github.xepozz.robots_txt.language.psi.impl;

import com.github.xepozz.robots_txt.language.psi.RobotsTxtValue;
import com.github.xepozz.robots_txt.language.psi.RobotsTxtVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class RobotsTxtValueImpl extends ASTWrapperPsiElement implements RobotsTxtValue {

    public RobotsTxtValueImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull RobotsTxtVisitor visitor) {
        visitor.visitValue(this);
    }

    @Override
    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof RobotsTxtVisitor) accept((RobotsTxtVisitor) visitor);
        else super.accept(visitor);
    }

}
