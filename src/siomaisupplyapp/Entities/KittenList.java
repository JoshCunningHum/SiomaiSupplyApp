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
    
    
    public void add(KittenList list){
        for(Kitten k : list.kittens){
            if(kittens.contains(k)) continue;
            kittens.add(k);
        }
    }
    
    public void add(Kitten k){
        if(kittens.contains(k)) return;
        kittens.add(k);
    }
    
    public void clear(){
        kittens.clear();
    }
    
    public void remove(Kitten k){
        kittens.remove(k);
    }
    
    public void remove(int index){
        kittens.remove(index);
    }
    
    public void displayAll(){
        System.out.println("---- Printing Kittens --------");
        for(Kitten k : kittens){
            System.out.println(k.toString());
        }
    }
    
    public KittenList filter(String value, String category){
        value = value.toLowerCase();
        KittenList res = new KittenList();
        for(Kitten k : kittens){
            String toCompareTo = category.equals("name") ? k.getName() : k.getBreed();
            toCompareTo = toCompareTo.toLowerCase();
            if(toCompareTo.contains(value)) res.add(k);
        }
        return res;
    }
}
