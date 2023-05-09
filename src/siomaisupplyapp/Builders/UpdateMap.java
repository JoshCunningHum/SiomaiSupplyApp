/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package siomaisupplyapp.Builders;

/**
 *
 * @author Josh
 */
public class UpdateMap extends SQLBuilder implements Conditional<UpdateMap>{
    private Query q; // For checking
    StringBuilder updates = new StringBuilder();
    String table;

    public UpdateMap(String table){
        super("UPDATE");
        this.table = table;
        q = new Query(table);
    }
    
    // This is for checking if Update 
    public String getQuery(){
        q.whrs().replace(0, q.whrs().length(), getCondtionString());
        return q.toString();
    }
    
    public UpdateMap set(String column, Object value){
        if(!updates.isEmpty()) updates.append(", ");
        updates.append(column).append(" = ").append(parseObject(value)); 
        return this;
    }
    
    public String toString(){
        return super.toString() + table + " SET " + updates + getCondtionString();
    }
}
