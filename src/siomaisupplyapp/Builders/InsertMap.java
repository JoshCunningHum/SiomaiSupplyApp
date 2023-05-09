/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package siomaisupplyapp.Builders;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Josh
 */
public class InsertMap extends SQLBuilder{
    HashMap<String, Object> values = new HashMap<>();
    String table;
    
    public InsertMap(String table){
        super("INSERT INTO");
        this.table = table;
    }
   
    public InsertMap $(String column, Object value){
        values.put(column, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder cols = new StringBuilder(),
                      vals = new StringBuilder();
        
        int count = 0;
        for(Map.Entry<String, Object> e : values.entrySet()){
            cols.append(e.getKey());
            
            Object v = e.getValue();
            
            vals.append(v == null ? " " : parseObject(v));
            
            if(count < values.size() - 1){
                cols.append(",");
                vals.append(",");
            }
            count++;
        }
        
        return "insert into " + table + "(" + cols + ") values(" + vals + ") ";
    }
    
    
}
