// This is a generated file. Not intended for manual editing.
package com.github.xepozz.robots_txt.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface RobotsTxtRule extends PsiElement {

  @NotNull
  RobotsTxtDirective getDirective();

  @Nullable
  RobotsTxtValue getValue();

}
