package lox;

public class AstPrinter implements Expr.Visitor{
    String print(Expr expr){
        return (String) expr.accept(this);
    }

    @Override
    public Object visitAssignExpr(Expr.Assign expr) {
        return null;
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr){
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public Object visitCallExpr(Expr.Call expr) {
        return null;
    }

    @Override
    public Object visitGetExpr(Expr.Get expr) {
        return null;
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr){
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr){
        if(expr.value == null){
            return "nill";
        }
        return expr.value.toString();
    }

    @Override
    public Object visitLogicalExpr(Expr.Logical expr) {
        return null;
    }

    @Override
    public Object visitSetExpr(Expr.Set expr) {
        return null;
    }

    @Override
    public Object visitSuperExpr(Expr.Super expr) {
        return null;
    }

    @Override
    public Object visitThisExpr(Expr.This expr) {
        return null;
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr){
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    @Override
    public Object visitVariableExpr(Expr.Variable expr) {
        return null;
    }

    private String parenthesize(String name, Expr ... exprs){
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for(Expr expr : exprs){
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }
}
