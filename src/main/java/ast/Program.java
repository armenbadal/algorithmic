
package ast;

public class Program implements Node {
    public String source = null;
    public Algorithm[] members = null;

    public Program()
    {}

	@Override
	public String toString()
	{
		String result = "";
		for( Algorithm al : members )
			result = result + al.toString();

		return result;
	}
}
