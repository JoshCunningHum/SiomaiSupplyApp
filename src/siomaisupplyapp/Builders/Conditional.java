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
 */
public interface Conditional {
    class Storage{
        private static final Map<Conditional, StringBuilder> whrs = new WeakHashMap<>();
    }
    
    default StringBuilder whrs(){
        StringBuilder res = Storage.whrs.get(this);
        if(res == null) Storage.whrs.put(this, new StringBuilder());
        return Storage.whrs.get(this);
    }
    
    default Conditional where(String clause){
        whrs().append(clause).append(" ");
        return this;
    }
    
    default UnfinishedWhereClause and(){
        return new UnfinishedWhereClause(this, "and");
    }
    
    default UnfinishedWhereClause or(){
        return new UnfinishedWhereClause(this, "or");
    }
    
    default Conditional whereEqual(String column, Object value){
        String v = (value instanceof String) ? "'" + value + "'" : value.toString();
        return where(column + "=" + v);
    }
    
    default Conditional whereNot(String column, Object value){
        String v = (value instanceof String) ? "'" + value + "'" : value.toString();
        return where(column + "<>" + v);
    }
    
    default Conditional whereLess(String column, int value){
       return where(column + "<" + value);
    }
    
    default Conditional whereLess(String column, double value){
       return where(column + "<" + value);
    }
    
    
    default Conditional whereLessEqual(String column, int value){
       return where(column + "<=" + value);
    }
    
    default Conditional whereLessEqual(String column, double value){
       return where(column + "<=" + value);
    }
    
    
    default Conditional whereGreater(String column, int value){
       return where(column + ">" + value);
    }
    
    default Conditional whereGreater(String column, double value){
       return where(column + ">" + value);
    }
    
    
    default Conditional whereGreaterEqual(String column, int value){
       return where(column + ">=" + value);
    }
    
    default Conditional whereGreaterEqual(String column, double value){
       return where(column + ">=" + value);
    }
    
    default boolean isEmptyCondition(){
        return whrs().isEmpty();
    }
    
    default String getCondtionString(){
        return " WHERE " + whrs();
    }
}
