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
    
    public Conditional<N> where(String clause){
        q.whrs().append(operator).append(" ").append(clause).append(" ");
        return q;
    }

    public Conditional<N> like(String column, Object value){
        String v = (value instanceof String) ? "'" + value + "'" : value.toString();
        return where(column + " LIKE " + v);
    }
    
    public Conditional<N> whereEqual(String column, Object value){
        String v = (value instanceof String) ? "'" + value + "'" : value.toString();
        return where(column + "=" + v);
    }
    
    public Conditional<N> whereNot(String column, Object value){
        String v = (value instanceof String) ? "'" + value + "'" : value.toString();
        return where(column + "<>" + v);
    }
    
    public Conditional<N> whereLess(String column, int value){
       return where(column + "<" + value);
    }
    
    public Conditional<N> whereLess(String column, double value){
       return where(column + "<" + value);
    }
    
    
    public Conditional<N> whereLessEqual(String column, int value){
       return where(column + "<=" + value);
    }
    
    public Conditional<N> whereLessEqual(String column, double value){
       return where(column + "<=" + value);
    }
    
    
    public Conditional<N> whereGreater(String column, int value){
       return where(column + ">" + value);
    }
    
    public Conditional<N> whereGreater(String column, double value){
       return where(column + ">" + value);
    }
    
    
    public Conditional<N> whereGreaterEqual(String column, int value){
       return where(column + ">=" + value);
    }
    
    public Conditional<N> whereGreaterEqual(String column, double value){
       return where(column + ">=" + value);
    }
}