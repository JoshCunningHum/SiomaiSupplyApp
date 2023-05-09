/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package siomaisupplyapp.Builders;

import java.util.Map;
import java.util.WeakHashMap;

/**
 *
 * @author Josh
 * @param <N> SQLBuilder subclasses that needs the where conditional statement
 */
public interface Conditional<N extends Conditional<N>>{

    class Storage{
        private static final Map<Conditional, StringBuilder> whrs = new WeakHashMap<>();
    }
    
    default StringBuilder whrs(){
        StringBuilder res = Storage.whrs.get(this);
        if(res == null) Storage.whrs.put(this, new StringBuilder());
        return Storage.whrs.get(this);
    }
    
    default N where(String clause){
        whrs().append(clause).append(" ");
        return (N) this;
    }
    
    default UnfinishedWhereClause<N> and(){
        return new UnfinishedWhereClause<N>(this, "and");
    }
    
    default UnfinishedWhereClause<N> or(){
        return new UnfinishedWhereClause<N>(this, "or");
    }

    default N like(String column, Object value){
        String v = (value instanceof String) ? "'" + value + "'" : value.toString();
        return where(column + " LIKE " + v);
    }
    
    default N whereEqual(String column, Object value){
        String v = (value instanceof String) ? "'" + value + "'" : value.toString();
        return where(column + "=" + v);
    }
    
    default N whereNot(String column, Object value){
        String v = (value instanceof String) ? "'" + value + "'" : value.toString();
        return where(column + "<>" + v);
    }
    
    default N whereLess(String column, int value){
       return where(column + "<" + value);
    }
    
    default N whereLess(String column, double value){
       return where(column + "<" + value);
    }
    
    
    default N whereLessEqual(String column, int value){
       return where(column + "<=" + value);
    }
    
    default N whereLessEqual(String column, double value){
       return where(column + "<=" + value);
    }
    
    
    default N whereGreater(String column, int value){
       return where(column + ">" + value);
    }
    
    default N whereGreater(String column, double value){
       return where(column + ">" + value);
    }
    
    
    default N whereGreaterEqual(String column, int value){
       return where(column + ">=" + value);
    }
    
    default N whereGreaterEqual(String column, double value){
       return where(column + ">=" + value);
    }
    
    default boolean isEmptyCondition(){
        return whrs().isEmpty();
    }
    
    default String getCondtionString(){
        if(isEmptyCondition()) return "";
        return " WHERE " + whrs();
    }
}
