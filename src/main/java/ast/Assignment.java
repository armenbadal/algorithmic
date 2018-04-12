
package ast;

public class Assignment implements Statement {
    public Variable place = null;
    public Expression value = null;

    public Assignment( Variable pl, Expression vl )
    {
        place = pl;
        value = vl;
    }
}
