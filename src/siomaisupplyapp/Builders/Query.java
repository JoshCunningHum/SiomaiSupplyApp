/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package siomaisupplyapp.Builders;


/**
 *
 * @author Josh
 */
public class Query implements Conditional{
    StringBuilder cols = new StringBuilder(),
                  ordr = new StringBuilder();
    
    // TODO: Add LIKE 
    
    String table, sortTo, group;
    
    int limit = 0, offset = -1; 
                  
    public Query(String table){
        this.table = table;
    }
    
    public Query column(String ...cols){
        this.cols.setLength(0);
        for(String c : cols) this.cols.append(c).append(",");
        return this;
    }

    @Override
    public Query whereGreaterEqual(String column, double value) {
        return (Query) Conditional.super.whereGreaterEqual(column, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Query whereGreaterEqual(String column, int value) {
        return (Query) Conditional.super.whereGreaterEqual(column, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Query whereGreater(String column, double value) {
        return (Query) Conditional.super.whereGreater(column, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Query whereGreater(String column, int value) {
        return (Query) Conditional.super.whereGreater(column, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Query whereLessEqual(String column, double value) {
        return (Query) Conditional.super.whereLessEqual(column, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Query whereLessEqual(String column, int value) {
        return (Query) Conditional.super.whereLessEqual(column, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Query whereLess(String column, double value) {
        return (Query) Conditional.super.whereLess(column, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Query whereLess(String column, int value) {
        return (Query) Conditional.super.whereLess(column, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Query whereNot(String column, Object value) {
        return (Query) Conditional.super.whereNot(column, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Query whereEqual(String column, Object value) {
        return (Query) Conditional.super.whereEqual(column, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Query where(String clause) {
        return (Query) Conditional.super.where(clause); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    public Query sort(String column){
        return sort(column, true);
    }
    
    public Query sortDesc(String column){
        return sort(column, false);
    }
    
    public Query sort(String column, boolean isAscending){
        if(!ordr.isEmpty()) ordr.append(", ");
        ordr.append(column).append(" ").append(isAscending ? "ASC" : "DESC");
        return this;
    }
    
    public Query limit(int l){
        limit = l;
        return this;
    }
    
    public Query offset(int o){
        offset = o;
        return this;
    }
    
    @Override            
    public String toString() {
        String c = (cols.isEmpty() ? "*" : cols.toString());
        StringBuilder res = new StringBuilder();
        
        res.append("select ").append(c).append(" from ").append(table);
        if(!isEmptyCondition()) res.append(getCondtionString());
        if(!ordr.isEmpty()) res.append(" ORDER BY ").append(ordr);
        if(limit > 0) res.append(" LIMIT ").append(limit);
        if(offset > 0) res.append(" OFFSET ").append(offset);
        
        return res + ";";
    }
}
