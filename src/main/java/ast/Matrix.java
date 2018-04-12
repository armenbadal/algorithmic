
package ast;

public class Matrix implements Type {
    public Scalar base = null;
    public int colow = 0;
    public int coup = 0;
    public int rolow = 0;
    public int roup = 0;

    public Matrix( Scalar b, int cl, int cu, int rl, int ru )
    {
        base = b;
        colow = cl;
        coup = cu;
        rolow = rl;
        roup = ru;
    }
}
