/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package siomaisupplyapp;

import siomaisupplyapp.Builders.Query;
import siomaisupplyapp.Builders.InsertMap;
import siomaisupplyapp.Entities.Staff;
import siomaisupplyapp.Entities.KittenList;
import siomaisupplyapp.Entities.User;
import siomaisupplyapp.Entities.Kitten;
import siomaisupplyapp.Entities.Adopter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import siomaisupplyapp.Builders.DeleteMap;
import siomaisupplyapp.Builders.UpdateMap;


/**
 *
 * @author Josh
 */
public class Connect {
    
    public static enum LOGIN {
        SUCCESSFUL_ADOPTER,
        SUCCESSFUL_STAFF,
        NOT_FOUND,
        WRONG_PASSWORD,
        UNKNOWN_ERROR
    }
    
    Connection c = null;
    
    public Connect(String jdbc){
        try {
            c = DriverManager.getConnection(jdbc, "root", "");
        }catch (SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean insert(String sql){
        
        System.out.println(sql);
        
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            return true;
        }catch(SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    private boolean insert(InsertMap values){
        return insert(values.toString());
    }
    
    private boolean insert(String table, Object ...values){
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ").append(table).append(" values(");
        
        int count = 0;
        for(Object v : values){
            
            if(v instanceof String) sb.append("'");
            sb.append(v.toString());
            if(v instanceof String) sb.append("'");
            
            
            count++;
            if(count < values.length - 1) sb.append(",");
            else sb.append(")");
        }
        
        String sql = sb.toString();
        
        return insert(sql);
    }
    
    private ResultSet query(Query qb){
        return query(qb.toString());
    }
    
    private ResultSet query(String sql){
        Statement s;
        
        try {
            s = c.createStatement();
            return s.executeQuery(sql);
        }catch (SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private boolean update(String sql){
        Statement s;
        
        try {
            s = c.createStatement();
            s.executeUpdate(sql);
            return true;
        }catch (SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    private boolean update(UpdateMap um){
        return update(um.toString());
    }
    
    private boolean update(DeleteMap dm){
        return update(dm.toString());
    }
    
    // SIOMAI SUPPLY APP METHODS
    
    // Query Kittens
    public KittenList queryKittens(Query q){
        KittenList list = new KittenList();
        ResultSet rs = query(q);
        
        System.out.println(q.toString());
        
        try {
            while(rs.next()){
                Kitten k = new Kitten(
                        rs.getInt("kitten_id"),
                        rs.getInt("status"),
                        rs.getString("name"),
                        rs.getString("breed"),
                        rs.getString("description"),
                        rs.getDate("birthdate")
                );
                list.add(k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    // Add Kitten
    public boolean addKitten(Kitten k){
        return insert(new InsertMap("kitten")
        .$("name", k.getName())
        .$("breed", k.getBreed())
        .$("description", k.getDescription())
        .$("status", k.getStatus())
        .$("birthdate", k.getBirthdate().toString())
        );
    }
    // Remove Kitten
    public boolean removeKitten(int kitten_id){
        return update(new DeleteMap("kitten").whereEqual("kitten_id", kitten_id));
    }
    // Update Kitten
    public boolean updateKitten(UpdateMap u){
        // looks very useless tbh
        return update(u);
    }
    // Query With Filter and Sort
    
    // Query All Kittens that fits in the page
    public KittenList queryKittens(int limit, int offset){
        // order by age by default
        return queryKittens(new Query("kitten").limit(limit).offset(offset).sort("birthdate"));
    }
    
    
    // Query Adoptions
    // Create Adoption
    // Remove Adoption
    
    // Register User
    public boolean registerUser(User user){
        boolean isStaff = user instanceof Staff;
        
        // check first if user is existing
        ResultSet rs = query(new Query("user")
                .whereEqual("username", user.getUsername()));
        
        try {
            if(rs.next()) return false;
            
            InsertMap values = new InsertMap("user")
                               .$("username", user.getUsername())
                               .$("password", user.getPassword())
                               .$("type", (isStaff ? 1 : 0));
            
            if(user.getEmail() != null) values.$("email", user.getEmail());
            
            if(!isStaff){
                Adopter a = (Adopter) user;
                values.$("address", a.getAddress()).$("contact", a.getContact());
            }
            
            return insert(values);
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    // Login User
    public LOGIN loginUser(String username, String password){
        ResultSet rs;
        
        // find username if existing
        rs = query(new Query("user").whereEqual("username", username));
        
        try {
            if(!rs.next()) return LOGIN.NOT_FOUND;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            // if existing then check password
            if(!rs.getString("password").equals(password)) return LOGIN.WRONG_PASSWORD;
            else return (rs.getInt("type") == 1 ? LOGIN.SUCCESSFUL_STAFF : LOGIN.SUCCESSFUL_ADOPTER);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return LOGIN.UNKNOWN_ERROR;
    }
}
