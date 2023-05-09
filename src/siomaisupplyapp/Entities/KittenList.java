/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package siomaisupplyapp.Entities;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Josh
 */
public class KittenList {
    public ArrayList<Kitten> kittens = new ArrayList<>();

    public KittenList(ArrayList<Kitten> kittens) {
        this.kittens.addAll(kittens);
    }
    
    public Kitten get(int id){
        for(Kitten k : kittens){
            if(k.getId() == id) return k;
        }
        return null;
    }
    
    public KittenList(Kitten ...kittens) {
        this.kittens.addAll(Arrays.asList(kittens));
    }
    
    public void add(Kitten k){
        kittens.add(k);
    }
    
    public void clear(){
        kittens.clear();
    }
    
    public void displayAll(){
        System.out.println("---- Printing Kittens --------");
        for(Kitten k : kittens){
            System.out.println(k.toString());
        }
    }
}
