// This is a generated file. Not intended for manual editing.
package com.github.xepozz.robots_txt.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.xepozz.robots_txt.language.psi.impl.*;

public interface RobotsTxtTypes {

  IElementType DIRECTIVE = new RobotsTxtElementType("DIRECTIVE");
  IElementType RULE = new RobotsTxtElementType("RULE");
  IElementType VALUE = new RobotsTxtElementType("VALUE");

  IElementType COMMENT = new RobotsTxtTokenType("COMMENT");
  IElementType DELIMITER = new RobotsTxtTokenType("DELIMITER");
  IElementType EOL = new RobotsTxtTokenType("EOL");
  IElementType TEXT = new RobotsTxtTokenType("TEXT");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == DIRECTIVE) {
        return new RobotsTxtDirectiveImpl(node);
      }
      else if (type == RULE) {
        return new RobotsTxtRuleImpl(node);
      }
      else if (type == VALUE) {
        return new RobotsTxtValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
