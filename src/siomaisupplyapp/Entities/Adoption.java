/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package siomaisupplyapp.Entities;

import java.sql.Date;

/**
 *
 * @author Josh
 */
public class Adoption {
    enum STATUS {
        ONGOING,
        SUCCESSFUL,
        CANCELED,
        RETURNED
    }
    
    private int id, status, kitten_id;
    private String username;
    private Date date_expire;

    public Adoption() {
    }

    public Adoption(int id, int status, int kitten_id, String username, Date date_expire) {
        this.id = id;
        this.status = status;
        this.kitten_id = kitten_id;
        this.username = username;
        this.date_expire = date_expire;
    }

    public Adoption(int kitten_id, String username, Date date_expire) {
        this.kitten_id = kitten_id;
        this.username = username;
        this.date_expire = date_expire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getKitten_id() {
        return kitten_id;
    }

    public void setKitten_id(int kitten_id) {
        this.kitten_id = kitten_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate_expire() {
        return date_expire;
    }

    public void setDate_expire(Date date_expire) {
        this.date_expire = date_expire;
    }
   
    
}
