/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package siomaisupplyapp.Builders;

/**
 *
 * @author Josh
 */

public class UnfinishedWhereClause{
    Conditional q;
    String operator;

    public UnfinishedWhereClause(Conditional q, String operator) {
        this.q = q;
        this.operator = operator;
    }
    
    public Conditional where(String clause){
        q.whrs().append(operator).append(" ").append(clause).append(" ");
        return q;
    }
    
    public Conditional whereEqual(String column, Object value){
        String v = (value instanceof String) ? "'" + value + "'" : value.toString();
        return where(column + "=" + v);
    }
    
    public Conditional whereNot(String column, Object value){
        String v = (value instanceof String) ? "'" + value + "'" : value.toString();
        return where(column + "<>" + v);
    }
    
    public Conditional whereLess(String column, int value){
       return where(column + "<" + value);
    }
    
    public Conditional whereLess(String column, double value){
       return where(column + "<" + value);
    }
    
    
    public Conditional whereLessEqual(String column, int value){
       return where(column + "<=" + value);
    }
    
    public Conditional whereLessEqual(String column, double value){
       return where(column + "<=" + value);
    }
    
    
    public Conditional whereGreater(String column, int value){
       return where(column + ">" + value);
    }
    
    public Conditional whereGreater(String column, double value){
       return where(column + ">" + value);
    }
    
    
    public Conditional whereGreaterEqual(String column, int value){
       return where(column + ">=" + value);
    }
    
    public Conditional whereGreaterEqual(String column, double value){
       return where(column + ">=" + value);
    }
}