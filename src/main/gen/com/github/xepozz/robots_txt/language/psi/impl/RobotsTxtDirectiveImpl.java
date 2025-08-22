// This is a generated file. Not intended for manual editing.
package com.github.xepozz.robots_txt.language.psi.impl;

import com.github.xepozz.robots_txt.language.psi.RobotsTxtDirective;
import com.github.xepozz.robots_txt.language.psi.RobotsTxtVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class RobotsTxtDirectiveImpl extends ASTWrapperPsiElement implements RobotsTxtDirective {

  public RobotsTxtDirectiveImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RobotsTxtVisitor visitor) {
    visitor.visitDirective(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
      if (visitor instanceof RobotsTxtVisitor) accept((RobotsTxtVisitor) visitor);
    else super.accept(visitor);
  }

}
