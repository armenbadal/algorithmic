
package ast;

public class Algorithm implements Node {
    public String name = null;
    public Scalar type = null;
    public Symbol[] parameters = null;
    public Statement body = null;

    public Symbol[] locals = null;

    public Algorithm( String nm, Scalar rty )
    {
        name = nm;
        type = rty;
    }
}
