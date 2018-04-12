
package driver;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import parser.*;

public class Main {

    public static void main( String[] args ) throws Exception
    {
        CharStream input = CharStreams.fromFileName("C:\\Projects\\algorithmic\\cases\\case00.alg");
        AlgorithmicLexer lexer = new AlgorithmicLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AlgorithmicParser parser = new AlgorithmicParser(tokens);
        ParseTree tree = parser.program();
        System.out.println(tree.toStringTree(parser));

        System.out.println("Այո");
    }
}
