
package ast;

public class Vector implements Type {
    public Scalar base;
    public int lower;
    public int upper;

	public Vector()
	{
		this(null, 0, 0);
	}
	
    public Vector( Scalar b, int l, int u )
    {
        base = b;
        lower = l;
        upper = u;
    }

	@Override
	public String toString()
	{
		return String.format("%s աղյուսակ[%d:%d]",
                   base.toString(), lower, upper);
	}
}

