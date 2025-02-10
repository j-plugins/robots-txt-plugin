// This is a generated file. Not intended for manual editing.
package com.github.xepozz.robots_txt.language.psi;

import com.github.xepozz.robots_txt.language.psi.impl.RobotsTxtDirectiveImpl;
import com.github.xepozz.robots_txt.language.psi.impl.RobotsTxtEntryImpl;
import com.github.xepozz.robots_txt.language.psi.impl.RobotsTxtRuleImpl;
import com.github.xepozz.robots_txt.language.psi.impl.RobotsTxtValueImpl;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

public interface RobotsTxtTypes {

    IElementType DIRECTIVE = new RobotsTxtElementType("DIRECTIVE");
  IElementType ENTRY = new RobotsTxtElementType("ENTRY");
  IElementType RULE = new RobotsTxtElementType("RULE");
    IElementType VALUE = new RobotsTxtElementType("VALUE");

  IElementType COMMENT = new RobotsTxtTokenType("COMMENT");
  IElementType DELIMITER = new RobotsTxtTokenType("DELIMITER");
    IElementType TEXT = new RobotsTxtTokenType("TEXT");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
        if (type == DIRECTIVE) {
            return new RobotsTxtDirectiveImpl(node);
        } else if (type == ENTRY) {
        return new RobotsTxtEntryImpl(node);
      }
      else if (type == RULE) {
        return new RobotsTxtRuleImpl(node);
      } else if (type == VALUE) {
            return new RobotsTxtValueImpl(node);
        }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
