
package ast;

public class Symbol implements Node {
    public String name = null;
    public Type type = null;

    public Symbol( String nm, Type ty )
    {
        name = nm;
        type = ty;
    }

	@Override
	public String toString()
	{
		return type.toString() + " " + name;
	}
}

