
package ast;

public class Symbol {
    public String name = null;
    public Type type = null;

    public Symbol( String nm, Type ty )
    {
        name = nm;
        type = ty;
    }
}
