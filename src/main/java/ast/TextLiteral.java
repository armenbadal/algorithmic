package ast;

public class TextLiteral extends Expression {
    public String value = null;

    public TextLiteral( String vl )
    {
        value = vl;
    }
}
