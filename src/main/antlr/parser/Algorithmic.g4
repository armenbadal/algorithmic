
grammar Algorithmic;

// ծրագիրը ալգորիթմների շարք է
program
    : NL? (algorithm NL)*
    ;

algorithm
    : 'ալգ' scalar? IDENT ('(' (parameter (',' parameter)*)? ')')? NL
      'սկիզբ' NL? (declaration NL)* sequence 'վերջ'
    ;

// սկալյար տիպեր
scalar
    : KW_TRAM | KW_AMB | KW_IRK | KW_TEQST
    ;

// պարամետրեր
parameter
    : (KW_ARG | KW_ARD)? scalar paramName (',' paramName)*
    ;

paramName
    : IDENT                         # simpleParam
    | 'աղյուսակ' IDENT '[' ']'      # vectorParam
    | 'աղյուսակ' IDENT '[' ',' ']'  # matrixParam
    ;

// հայտարարություններ
declaration
    : scalar declName (',' declName)*
    ;

declName
    : IDENT                                     # simpleDecl
    | 'աղյուսակ' IDENT '[' range ']'            # vectorDecl
    | 'աղյուսակ' IDENT '[' range ',' range ']'  # matrixDecl
    ;

range
    : lower=INTEGER ':' upper=INTEGER
    ;

// ղեկավարող կառուցվածքներ
sequence
    : (statement NL)*
    ;

statement
    : assign | branch | condLoop | countLoop | select | algCall
    ;

assign
    : IDENT index? ':=' expression
    ;

index
    : '[' expression ']'
    | '[' expression ',' expression ']'
    ;

branch
    : 'եթե' expression NL 'ապա' NL? sequence alternative? 'ավարտ'
    ;

alternative
    : 'այլապես' NL? sequence
    ;

condLoop
    : 'քանի' 'դեռ' expression NL 'ցս' NL? sequence 'ցվ'
    ;

countLoop
    : 'թող' IDENT 'սկսած' start=expression 'մինչև' stop=expression
      ('քայլ' step=INTEGER)? NL 'ցս' NL? sequence 'ցվ'
    ;

select
    : 'ընտրել' NL oneCase+ alternative? 'ավարտ'
    ;

oneCase
    : 'երբ' expression ':' NL? sequence
    ;

algCall
    : IDENT ('(' (expression (',' expression)*)? ')')?
    ;

// expressions
expression
    : simple                                                    # simpleExpr
    | '(' expression ')'                                        # priority
    | IDENT '[' expression (',' expression)? ']'                # arrayElem
    | IDENT '(' (expression (',' expression)*)? ')'             # funcall
    | op=('ոչ' | '-' | '+') expression                          # unary
    | <assoc=right> le=expression op='**' re=expression         # power
    | le=expression op=('*' | '/') re=expression                # multiplication
    | le=expression op=('+' | '-') re=expression                # addition
    | le=expression op=('>' | '>=' | '<' | '<=') re=expression  # comparison
    | le=expression op=('=' | '<>') re=expression               # equality
    | le=expression op='և' re=expression                        # conjunction
    | le=expression op='կամ' re=expression                      # disjunction
    ;

simple
    : TEXT     # textLiteral
    | REAL     # realLiteral
    | INTEGER  # integerLiteral
    | LOGICAL  # logicalLiteral
    | IDENT    # variable
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
KW_SKSAC : 'սկսած';
KW_MINCHEV : 'մինչև';
KW_QAYL : 'քայլ';
KW_CS : 'ցս';
KW_CV : 'ցվ';
KW_YNTREL : 'ընտրել';
KW_YERB : 'երբ';
KW_YEV : 'և' | 'եւ';
KW_KAM : 'կամ';
KW_VOCH : 'ոչ';
//KW_CHISHT : 'ճիշտ';
//KW_KEGHC : 'կեղծ';

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

REAL    : DIGIT+'.'DIGIT+;
INTEGER : ('-'|'+')? DIGIT+;
TEXT    : '"'(~'"')*'"' | '«'(~'»')*'»';
LOGICAL : 'ճիշտ' | 'կեղծ';

IDENT : LETTER(LETTER | DIGIT)*;


NL : [;\n]+;

WS : [ \t\r]+ -> skip;

fragment LETTER
    : [ա-ևa-z]
    ;

fragment DIGIT
    : [0-9]
    ;
