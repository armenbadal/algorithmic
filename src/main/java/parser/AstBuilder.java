
package parser;

import ast.*;

import java.util.List;
import java.util.ArrayList;

public class AstBuilder extends AlgorithmicBaseVisitor<Node> {

	/// DEBUG (remove)
	private static void P( String sv )
	{
		System.out.println(sv);
	}
	
	
    // ընթացիկ կառուցվող պրոցեդուրան
    private Algorithm current = null;
	
	// պարամետրեր
	private List<Symbol> params = null;
	// միջանկյալ մեծություններ
	private List<Symbol> locals = null;

    @Override
    public Node visitProgram(AlgorithmicParser.ProgramContext ctx)
    {
		P("visitProgram()");
		
        Program prog = new Program();

        List<AlgorithmicParser.AlgorithmContext> als = ctx.algorithm();
        prog.members = new Algorithm[als.size()];
        for( int i = 0; i < prog.members.length; ++i )
            prog.members[i] = (Algorithm)visitAlgorithm(als.get(i));

        return prog;
    }

    @Override
    public Node visitAlgorithm(AlgorithmicParser.AlgorithmContext ctx)
    {
		P("visitAlgorithm()");
		
		current = new Algorithm(ctx.IDENT().getText());
		// վերադարձվող տիպը
		if( ctx.scalar() != null )
			current.type = (Scalar)visitScalar(ctx.scalar());

		// պարամետրեր
		params = new ArrayList<>();
		for( AlgorithmicParser.ParameterContext pc : ctx.parameter() )
			visitParameter(pc);
		current.parameters = new Symbol[params.size()];
		for( int i = 0; i < params.size(); ++i )
			current.parameters[i] = params.get(i);

        // հայտարարություններ
		locals = new ArrayList<>();
		for( AlgorithmicParser.DeclarationContext dc : ctx.declaration() )
			visitDeclaration(dc);
		current.locals = new Symbol[locals.size()];
		for( int i = 0; i < locals.size(); ++i )
			current.locals[i] = locals.get(i);

		// մարմնի հրամաններ
		visitSequence(ctx.sequence());

        return current;
    }

    @Override
    public Node visitScalar(AlgorithmicParser.ScalarContext ctx)
    {
		P("visitScalar()");
		
        return new Scalar(ctx.nid);
    }

    @Override
    public Node visitParameter(AlgorithmicParser.ParameterContext ctx)
    {
		P("visitParameter()");

		Scalar sc = (Scalar)visitScalar(ctx.scalar());
        for( AlgorithmicParser.ParamNameContext pn : ctx.paramName() ) {
			Symbol param = null;
			if( pn instanceof AlgorithmicParser.SimpleParamContext) {
				param = (Symbol)visitSimpleParam((AlgorithmicParser.SimpleParamContext)pn);
				param.type = sc;
			}
			else if( pn instanceof AlgorithmicParser.VectorParamContext ) {
				param = (Symbol)visitVectorParam((AlgorithmicParser.VectorParamContext)pn);
				((Vector)param.type).base = sc;
			}
			else if( pn instanceof AlgorithmicParser.MatrixParamContext ) {
				param = (Symbol)visitMatrixParam((AlgorithmicParser.MatrixParamContext)pn);
				((Matrix)param.type).base = sc;
			}
			params.add(param);
		}
		
		return null;
    }

	@Override
	public Node visitSimpleParam(AlgorithmicParser.SimpleParamContext ctx)
	{
		P("visitSimpleParam()");
		return new Symbol(ctx.IDENT().getText(), null);
	}

	@Override
	public Node visitVectorParam(AlgorithmicParser.VectorParamContext ctx)
	{
		P("visitVectorParam()");
		return new Symbol(ctx.IDENT().getText(), new Vector());
	}
	
	@Override
	public Node visitMatrixParam(AlgorithmicParser.MatrixParamContext ctx)
	{
		P("visitMatrixParam()");
		return new Symbol(ctx.IDENT().getText(), new Matrix());
	}

	
	@Override
	public Node visitDeclaration(AlgorithmicParser.DeclarationContext ctx)
	{
		P("visitDeclaration()");
		
		Scalar sc = (Scalar)visitScalar(ctx.scalar());
		for( AlgorithmicParser.DeclNameContext pn : ctx.declName() ) {
			Symbol vari = null;
			if( pn instanceof AlgorithmicParser.SimpleDeclContext) {
				vari = (Symbol)visitSimpleDecl((AlgorithmicParser.SimpleDeclContext)pn);
				vari.type = sc;
			}
			else if( pn instanceof AlgorithmicParser.VectorDeclContext ) {
				vari = (Symbol)visitVectorDecl((AlgorithmicParser.VectorDeclContext)pn);
				((Vector)vari.type).base = sc;
			}
			else if( pn instanceof AlgorithmicParser.MatrixDeclContext ) {
				vari = (Symbol)visitMatrixDecl((AlgorithmicParser.MatrixDeclContext)pn);
				((Matrix)vari.type).base = sc;
			}
			locals.add(vari);
		}
		
		return null;
	}

	@Override
	public Node visitSimpleDecl(AlgorithmicParser.SimpleDeclContext ctx)
	{
		return new Symbol(ctx.IDENT().getText(), null);
	}

	@Override
	public Node visitVectorDecl(AlgorithmicParser.VectorDeclContext ctx)
	{
		int el = Integer.parseInt(ctx.el.getText());
		int eh = Integer.parseInt(ctx.eh.getText());
		return new Symbol(ctx.IDENT().getText(), new Vector(null, el, eh));
	}

	@Override
	public Node visitMatrixDecl(AlgorithmicParser.MatrixDeclContext ctx)
	{
		int cl = Integer.parseInt(ctx.cl.getText());
		int ch = Integer.parseInt(ctx.ch.getText());
		int rl = Integer.parseInt(ctx.rl.getText());
		int rh = Integer.parseInt(ctx.rh.getText());
		return new Symbol(ctx.IDENT().getText(), new Matrix(null, cl, ch, rl, rh));
	}

	
	@Override
	public Node visitSequence(AlgorithmicParser.SequenceContext ctx)
	{
		P("visitSequence()");
		for( AlgorithmicParser.StatementContext si : ctx.statement() )
			visitStatement(si);

		return null;
	}

	@Override
	public Node visitStatement(AlgorithmicParser.StatementContext ctx)
	{
		P("visitStatement()");
		
		if( ctx.assign() != null )
			visitAssign(ctx.assign());
 
		return null;
	}

	@Override
	public Node visitAssign(AlgorithmicParser.AssignContext ctx)
	{
		P("visitAssign()");

		return null;
	}

	
    @Override
    public Node visitMultiplication(AlgorithmicParser.MultiplicationContext ctx)
    {
        return visitChildren(ctx);
    }

    @Override
    public Node visitVariable(AlgorithmicParser.VariableContext ctx)
    {
        Symbol vs = new Symbol(ctx.IDENT().getText(), null);
        return new Variable(vs);
    }

//    @Override
//    public Node visitLogicalLiteral(AlgorithmicParser.LogicalLiteralContext ctx)
//    {
//        return new Logical(ctx.LOGICAL().getText());
//    }

    @Override
    public Node visitIntegerLiteral(AlgorithmicParser.IntegerLiteralContext ctx)
    {
        return new IntegerLiteral(ctx.INTEGER().getText());
    }

    @Override
    public Node visitRealLiteral(AlgorithmicParser.RealLiteralContext ctx)
    {
        return new RealLiteral(ctx.REAL().getText());
    }

    @Override
    public Node visitTextLiteral(AlgorithmicParser.TextLiteralContext ctx)
    {
        return new TextLiteral(ctx.TEXT().getText());
    }
}
