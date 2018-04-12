
package ast;

public class Algorithm implements Node {
    public String name = null;
    public Type type = null;
    public Symbol[] parameters = null;
    public Statement body = null;

    public Algorithm()
    {}
}
