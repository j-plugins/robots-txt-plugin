// This is a generated file. Not intended for manual editing.
package com.github.xepozz.robots_txt.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.xepozz.robots_txt.language.psi.RobotsTxtTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.xepozz.robots_txt.language.psi.*;
import com.intellij.psi.PsiReference;

public class RobotsTxtValueImpl extends ASTWrapperPsiElement implements RobotsTxtValue {

  public RobotsTxtValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RobotsTxtVisitor visitor) {
    visitor.visitValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RobotsTxtVisitor) accept((RobotsTxtVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public @NotNull String getValue() {
    return RobotsTxtImplUtil.getValue(this);
  }

  @Override
  public @NotNull PsiReference @NotNull [] getReferences() {
    return RobotsTxtImplUtil.getReferences(this);
  }

}
