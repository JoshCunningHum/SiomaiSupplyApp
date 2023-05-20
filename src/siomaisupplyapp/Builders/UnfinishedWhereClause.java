/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package siomaisupplyapp.Builders;

/**
 *
 * @author Josh
 */


public class UnfinishedWhereClause<N extends Conditional<N>>{
    Conditional<N> q;
    String operator;

    public UnfinishedWhereClause(Conditional<N> q, String operator) {
        this.q = q;
        this.operator = operator;
    }
    
    public N where(String clause){
        q.whrs().append(operator).append(" ").append(clause).append(" ");
        return (N) q;
    }

    public N like(String column, Object value){
        String v = (value instanceof String) ? "'" + value + "'" : value.toString();
        return where(column + " LIKE " + v);
    }
    
    public N whereEqual(String column, Object value){
        String v = (value instanceof String) ? "'" + value + "'" : value.toString();
        return where(column + "=" + v);
    }
    
    public N whereNot(String column, Object value){
        String v = (value instanceof String) ? "'" + value + "'" : value.toString();
        return where(column + "<>" + v);
    }
    
    public N whereLess(String column, int value){
       return where(column + "<" + value);
    }
    
    public N whereLess(String column, double value){
       return where(column + "<" + value);
    }
    
    
    public N whereLessEqual(String column, int value){
       return where(column + "<=" + value);
    }
    
    public N whereLessEqual(String column, double value){
       return where(column + "<=" + value);
    }
    
    
    public N whereGreater(String column, int value){
       return where(column + ">" + value);
    }
    
    public N whereGreater(String column, double value){
       return where(column + ">" + value);
    }
    
    
    public N whereGreaterEqual(String column, int value){
       return where(column + ">=" + value);
    }
    
    public N whereGreaterEqual(String column, double value){
       return where(column + ">=" + value);
    }
}