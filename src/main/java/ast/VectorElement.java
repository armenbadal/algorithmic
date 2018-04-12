
package ast;

public class VectorElement extends Variable {
    public Expression index = null;

    public VectorElement( Symbol sy, Expression ix )
    {
        super(sy);
        index = ix;
    }
}
