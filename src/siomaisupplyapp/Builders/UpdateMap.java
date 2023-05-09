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
    
    String table;

    public UpdateMap(String table){
        super("UPDATE");
        this.table = table;
    }

    public UpdateMap set(String column, Object value){

        return this;
    }
    
}
