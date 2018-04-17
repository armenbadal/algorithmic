package ast;

public class RealLiteral extends Expression {
    public Double value = null;

    public RealLiteral( String vl )
    {
        value = Double.valueOf(vl);
    }
}
