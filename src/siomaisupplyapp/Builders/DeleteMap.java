/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package siomaisupplyapp.Builders;

/**
 *
 * @author Kenjas
 */
public class DeleteMap extends SQLBuilder implements Conditional<DeleteMap>{
    String table;
    
    public DeleteMap(String table) {
        super("DELETE FROM");
        this.table = table;
    }
    
    @Override
   public String toString(){
       return super.toString() + table + getCondtionString();
   }
    
}
