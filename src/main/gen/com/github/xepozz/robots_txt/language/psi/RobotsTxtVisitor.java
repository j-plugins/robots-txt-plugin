// This is a generated file. Not intended for manual editing.
package com.github.xepozz.robots_txt.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralValue;

public class RobotsTxtVisitor extends PsiElementVisitor {

  public void visitDirective(@NotNull RobotsTxtDirective o) {
    visitPsiElement(o);
  }

  public void visitRule(@NotNull RobotsTxtRule o) {
    visitPsiElement(o);
  }

  public void visitValue(@NotNull RobotsTxtValue o) {
    visitPsiLiteralValue(o);
  }

  public void visitPsiLiteralValue(@NotNull PsiLiteralValue o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
