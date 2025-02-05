// This is a generated file. Not intended for manual editing.
package com.github.xepozz.robots_txt.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.xepozz.robots_txt.language.psi.impl.*;

public interface RobotsTxtTypes {

  IElementType ENTRY = new RobotsTxtElementType("ENTRY");
  IElementType RULE = new RobotsTxtElementType("RULE");

  IElementType COMMENT = new RobotsTxtTokenType("COMMENT");
  IElementType DELIMITER = new RobotsTxtTokenType("DELIMITER");
  IElementType DIRECTIVE = new RobotsTxtTokenType("DIRECTIVE");
  IElementType VALUE = new RobotsTxtTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ENTRY) {
        return new RobotsTxtEntryImpl(node);
      }
      else if (type == RULE) {
        return new RobotsTxtRuleImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
