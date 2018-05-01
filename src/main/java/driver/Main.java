
package driver;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import parser.*;

public class Main {

    public static void main( String[] args )
    {
		try {
			CharStream input = CharStreams.fromFileName(args[0]);
			AlgorithmicLexer lexer = new AlgorithmicLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			AlgorithmicParser parser = new AlgorithmicParser(tokens);
			ParseTree tree = parser.program();

			System.out.println("----------------------------------------------------");
			System.out.println(tree.toStringTree(parser));
			System.out.println("----------------------------------------------------");

			AstBuilder ab = new AstBuilder();
			String s = ab.visit(tree).toString();
			System.out.println(s);

		}
		catch( Exception ex ) {
			System.err.println("Error: " + ex.getMessage());
		}
		
        System.out.println("Այո");
    }
}
