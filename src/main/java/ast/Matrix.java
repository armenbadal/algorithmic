
package ast;

public class Matrix implements Type {
    public Scalar base;
    public int colow;
    public int coup;
    public int rolow;
    public int roup;

	public Matrix()
	{
		this(null, 0, 0, 0, 0);
	}
	
    public Matrix( Scalar b, int cl, int cu, int rl, int ru )
    {
        base = b;
        colow = cl;
        coup = cu;
        rolow = rl;
        roup = ru;
    }

	@Override
	public String toString()
	{
		return String.format("%s աղյուսակ[%d:%d, %d:%d]",
              base.toString(), colow, coup, rolow, roup);
	}
}
