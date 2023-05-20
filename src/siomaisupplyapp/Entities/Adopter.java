/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package siomaisupplyapp.Entities;

import siomaisupplyapp.Builders.Updatable;
import siomaisupplyapp.Builders.UpdateMap;

/**
 *
 * @author Josh
 */
public class Adopter extends User implements Updatable{
    String address, contact;
    
    public static boolean validateNumber(String mobNumber) {  
        return mobNumber.matches("\\d{11}") || 
                mobNumber.matches("\\+\\d{12}") || 
                 mobNumber.matches("\\(?\\+\\d{2}\\)?( |-)?\\d{10}") || 
                mobNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}") ||
                mobNumber.matches("\\d{4}[-\\.\\s]\\d{3}[-\\.\\s]\\d{3}") ||
                mobNumber.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}") ||
                mobNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}") ||
                mobNumber.matches("\\(\\d{5}\\)-\\d{3}-\\d{3}") ||
                mobNumber.matches("\\(\\d{4}\\)-\\d{3}-\\d{3}");
    }

    public Adopter(String username, String password, String email, String address, String contact) {
        super(username, password, email);
        this.address = address;
        this.contact = contact;
    }

    public Adopter(String username, String password, String email) {
        super(username, password, email);
    }
    
    public Adopter(Adopter copy){
        super(copy.username, copy.password, copy.email);
        address = copy.address;
        contact = copy.contact;
    }
    

    public Adopter() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public UpdateMap genUpdateMap(Object updatedValue) {
        if(!(updatedValue instanceof Adopter)) return null;
        UpdateMap res = new UpdateMap("user");
        
        Adopter other = (Adopter) updatedValue;
        
        if(other.password != null && !other.password.equals(password)) res.set("password", other.password);
        if(other.email != null && !other.email.equals(email)) res.set("email", other.email);
        if(other.address != null && !other.address.equals(address)) res.set("address", other.address);
        if(other.contact != null && !other.contact.equals(contact)) res.set("contact", other.contact);
        
        res.whereEqual("username", username);
        
        return res;
    }
    
    
}
