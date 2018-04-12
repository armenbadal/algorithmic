
package ast;

public class Vector implements Type {
    public Scalar base = null;
    public int lower = 0;
    public int upper = 0;

    public Vector( Scalar b, int l, int u )
    {
        base = b;
        lower = l;
        upper = u;
    }
}
