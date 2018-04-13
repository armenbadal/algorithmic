
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
    : scalarType declname
    ;

declName
    : IDENTIFIER
    | KW_AGHYUSAK IDENTIFIER '[' range ']'
    | KW_AGHYUSAK IDENTIFIER '[' range ',' range ]'
    ;

range
    : INTEGER ':' INTEGER
    ;

power
    : unary ('**' power)?
    ;

unary
    : '-' factor
    | KW_VOCH factor
    | factor
    ;

factor
    : IDENTIFIER
    | INTEGER
    | REAL
    | TEXT
    ;



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


IDENTIFIER : [ա-ևa-z][ա-ևa-z0-9]*;

REAL : [0-9]+'.'[0-9]+;
INTEGER : [0-9]+;
TEXT : '"'.*'"' | '«'.*'»';

NL : ';' | '\n';

WS : [ \t\r]+ -> skip;
