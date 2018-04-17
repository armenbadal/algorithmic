
package parser;

import ast.*;

import java.util.List;

public class AstBuilder extends AlgorithmicBaseVisitor<Node> {

    //
    private Algorithm current = null;

    @Override
    public Node visitProgram(AlgorithmicParser.ProgramContext ctx)
    {
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
        String name = ctx.IDENT().getText();
        Scalar rty = (Scalar)visitScalarType(ctx.scalarType());

        List<AlgorithmicParser.ParameterContext> prs = ctx.parameterList().parameter();

        current = new Algorithm(name, rty);

        return current;
    }

    @Override
    public Node visitScalarType(AlgorithmicParser.ScalarTypeContext ctx)
    {
        Scalar sty = new Scalar('V');

        if( ctx.KW_TRAM() != null )
            sty.id = 'L';
        else if( ctx.KW_AMB() != null )
            sty.id = 'I';
        else if( ctx.KW_IRK() != null )
            sty.id = 'R';
        else if( ctx.KW_TEQST() != null )
            sty.id = 'T';

        return sty;
    }

    @Override
    public Node visitParameter(AlgorithmicParser.ParameterContext ctx)
    {
        String nm = ctx.paramName()
        return visitChildren(ctx);
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
