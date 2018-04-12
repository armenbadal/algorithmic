
package ast;

public class MatrixElement extends Variable {
    public Expression column = null;
    public Expression row = null;

    public MatrixElement( Symbol sy, Expression cl, Expression rw )
    {
        super(sy);
        column = cl;
        row = rw;
    }
}