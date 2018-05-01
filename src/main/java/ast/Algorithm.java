
package ast;

public class Algorithm implements Node {
    public String name = null;
    public Scalar type = null;
    public Symbol[] parameters = null;
    public Statement body = null;

    public Symbol[] locals = null;

    public Algorithm( String nm )
    {
        name = nm;
		type = new Scalar('V');
    }

	@Override
	public String toString()
	{
		StringBuilder srbu = new StringBuilder();
		
		srbu.append("ալգ ");
		srbu.append(name);
		srbu.append("( ");
		for( Symbol s : parameters )
			srbu.append(s.toString()).append(" ");
		srbu.append(")\nսկիզբ\n");
		for( Symbol s : locals )
			srbu.append("  ").append(s.toString()).append("\n");
		srbu.append("վերջ\n");
		
		return srbu.toString();
	}
}
