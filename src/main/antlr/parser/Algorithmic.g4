
grammar Algorithmic;

@header {
package parser;
}

program
    : newLines? algorithm*
    ;

algorithm
    : KW_ALG scalarType? IDENTIFIER parameterList? newLines
    ;

scalarType
    : KW_TRAM | KW_AMB | KW_IRK | KW_TEQST
    ;

parameterList
    : '(' (parameter (',' parameter)*)? ')'
    ;

parameter
    : (KW_ARG | KW_ARD)? scalarType paramName (',' paramName)*
    ;

paramName
    : IDENTIFIER
    | KW_AGHYUSAK IDENTIFIER '[' ']'
    | KW_AGHYUSAK IDENTIFIER '[' ':' ']'
    ;

newLines
    : NL+
    ;

declarationList
    : declaration (',' declaration)*
    ;

declaration
    : scalarType declName
    ;

declName
    : IDENTIFIER
    | KW_AGHYUSAK IDENTIFIER '[' range ']'
    | KW_AGHYUSAK IDENTIFIER '[' range ',' range ']'
    ;

range
    : lower=INTEGER ':' upper=INTEGER
    ;


// statements
statementList
    : (statement newLines)*
    ;

statement
    : assign | branch | condLoop | countLoop | select | algCall
    ;

assign
    : IDENTIFIER index? ':=' expression
    ;

index
    : '[' INTEGER ']'
    | '[' INTEGER ',' INTEGER ']'
    ;

branch
    : KW_YETE expression newLines
      KW_APA statementList
      (KW_AYLAPES statementList)
      KW_AVART
    ;

condLoop
    : KW_QANI KW_DER expression newLines
      KW_CS statementList KW_CV
    ;

countLoop
    : KW_TOGH IDENTIFIER KW_MINCHEV expression (KW_QAYL INTEGER) newLines
      KW_CS statementList KW_CV
    ;

select
    : KW_YNTREL newLines
      (KW_YERB expression ':' statementList)+
      (KW_AYLAPES statementList)?
      KW_AVART
    ;

algCall
    : IDENTIFIER ('(' (expression (',' expression)*)? ')')?
    ;

// expressions
expression
    : addition (oper=('=' | '<>' | '>' | '>=' | '<' | '<=') addition)?
    ;

addition
    : multiplication (oper=(OP_PLUS | OP_MINUS | KW_KAM) multiplication)*
    ;

multiplication
    : power (oper=(OP_MULT | OP_DIV | KW_YEV) power)*
    ;

power
    : unary (OP_POW power)?
    ;

unary
    : '-' factor
    | KW_VOCH factor
    | factor
    ;

factor
    : '(' expression ')' # highPriority
    | IDENTIFIER '(' (expression (',' expression)*)? ')' # funcCall
    | IDENTIFIER index # arrayElement
    | IDENTIFIER # variable
    | INTEGER # integerLiteral
    | REAL # realLiteral
    | TEXT # textLiteral
    ;


// tokens
KW_ALG : 'ալգ';
KW_ARG : 'արգ';
KW_ARD : 'արդ';
KW_TRAM : 'տրամ';
KW_AMB : 'ամբ';
KW_IRK : 'իրկ';
KW_TEQST : 'տեքստ';
KW_AGHYUSAK : 'աղյուսակ';
KW_SKIZB : 'սկիզբ';
KW_VERJ : 'վերջ';
KW_YETE : 'եթե';
KW_APA : 'ապա';
KW_AYLAPES : 'այլապես';
KW_AVART : 'ավարտ';
KW_QANI : 'քանի';
KW_DER : 'դեռ';
KW_TOGH : 'թող';
KW_MINCHEV : 'մինչև';
KW_QAYL : 'քայլ';
KW_CS : 'ցս';
KW_CV : 'ցվ';
KW_YNTREL : 'ընտրել';
KW_YERB : 'երբ';
KW_YEV : 'և' | 'եւ';
KW_KAM : 'կամ';
KW_VOCH : 'ոչ';

OP_PLUS : '+';
OP_MINUS : '-';
OP_MULT : '*';
OP_DIV : '/';
OP_POW : '**';
OP_EQ : '=';
OP_NE : '<>';
OP_GT : '>';
OP_GE : '>=';
OP_LT : '<';
OP_LE : '<=';


IDENTIFIER : LETTER(LETTER | DIGIT)*;

REAL : DIGIT+'.'DIGIT+;
INTEGER : DIGIT+;
TEXT : '"'(~'"')*'"' | '«'(~'»')*'»';

NL : ';' | '\n';

WS : [ \t\r]+ -> skip;

fragment LETTER
    : [ա-ևa-z]
    ;

fragment DIGIT
    : [0-9]
    ;

