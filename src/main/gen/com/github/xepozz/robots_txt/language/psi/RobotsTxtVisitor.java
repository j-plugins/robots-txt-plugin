// This is a generated file. Not intended for manual editing.
package com.github.xepozz.robots_txt.language.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class RobotsTxtVisitor extends PsiElementVisitor {

  public void visitDirective(@NotNull RobotsTxtDirective o) {
    visitPsiElement(o);
  }

  public void visitRule(@NotNull RobotsTxtRule o) {
    visitPsiElement(o);
  }

  public void visitValue(@NotNull RobotsTxtValue o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
