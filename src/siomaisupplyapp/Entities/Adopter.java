/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package siomaisupplyapp.Entities;

/**
 *
 * @author Josh
 */
public class Adopter extends User{
    String address, contact;

    public Adopter(String username, String password, String email, String address, String contact) {
        super(username, password, email);
        this.address = address;
        this.contact = contact;
    }

    public Adopter(String username, String password, String email) {
        super(username, password, email);
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
    
    
}
