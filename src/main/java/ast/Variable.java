
package ast;

public class Variable extends Expression {
    public Symbol symbol = null;

    public Variable( Symbol sy )
    {
        symbol = sy;
    }
}
