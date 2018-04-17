package ast;

public class IntegerLiteral extends Expression {
    public Integer value = null;

    public IntegerLiteral( String vl )
    {
        value = Integer.valueOf(vl);
    }
}
