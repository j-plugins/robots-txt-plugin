package com.github.xepozz.robots_txt.language.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.github.xepozz.robots_txt.language.psi.RobotsTxtTypes;

%%
%class RobotsTxtLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}


NEWLINE=\r|\n|\r\n
WHITESPACE=[ \t]+

%state VALUE
%%

<YYINITIAL> {
    #[^\r\n]*     { return RobotsTxtTypes.COMMENT; }
    ([\w-]+)      { return RobotsTxtTypes.TEXT; }
    ":"           { yybegin(VALUE); return RobotsTxtTypes.DELIMITER; }
}
<VALUE> {
    #[^\r\n]*     { return RobotsTxtTypes.COMMENT; }
    [^\s#]([^\r\n#]*[^\s#])? { return RobotsTxtTypes.TEXT; }
}

{WHITESPACE}      { return TokenType.WHITE_SPACE; }
{NEWLINE}         { yybegin(YYINITIAL); return RobotsTxtTypes.EOL; }
[^]               { return TokenType.BAD_CHARACTER; }
