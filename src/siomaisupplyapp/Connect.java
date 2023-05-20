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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import siomaisupplyapp.Builders.DeleteMap;
import siomaisupplyapp.Builders.UpdateMap;
import siomaisupplyapp.Entities.Adoption;


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
        System.out.println(qb.toString() + "| HERE");
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
    
    
    // Check for Expirees
    public void checkExpirees(){
        Date now = new Date((new java.util.Date()).getTime());
        
        // Loop through all ongoing adoptions
        ResultSet ongoing = query(new Query("adoption").whereEqual("status", Adoption.STATUS.ONGOING.ordinal()));
        
        try {
            while(ongoing.next()){
                Date d = ongoing.getDate("date_expire");
                
                Adoption a = new Adoption(
                        ongoing.getInt("adoption_id"),
                        ongoing.getInt("status"),
                        ongoing.getInt("kitten_id"),
                        ongoing.getString("username"),
                        d
                );
                
                if(now.after(d)){
                    // The adoption is already expireed, cancel it
                    cancelAdoption(a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Query Kittens
    public KittenList queryKittens(Query q){
        // Before querying, update all expired adoptions first
        checkExpirees();
        
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
    public KittenList queryKittens(int limit, int offset, String sort, boolean ascending){
        return queryKittens(new Query("kitten")
                .limit(limit).offset(offset)
                .sort(sort, ascending)
                .whereEqual("status", Kitten.STATUS.AVAILABLE.ordinal()));
    }
    // Query Kittens with limit and offset
    public KittenList queryKittens(int limit, int offset){
        // order by age by default
        return queryKittens(limit, offset, "birthdate", false);
    }
    // Query All Kittens (For admin)
    public KittenList queryKittensAll(){
        return queryKittens(new Query("kitten"));
    }
    // Query a kitten
    public Kitten queryKitten(int id){
        ResultSet rs = query(new Query("kitten").whereEqual("kitten_id", id));
        Kitten k = null;
        
        try {
            if(rs.next()){
                k = new Kitten(
                        id, 
                        rs.getInt("status"),
                        rs.getString("name"), 
                        rs.getString("breed"), 
                        rs.getString("description"), 
                        rs.getDate("birthdate")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return k;
    }
    
    
    
    // Query All Adoptions
    public ArrayList<Adoption> queryAdoptions(){
        ArrayList<Adoption> res = new ArrayList<>();
        
        ResultSet rs = query(new Query("adoption"));
        
        try {
            while(rs.next()){
                res.add(new Adoption(
                        rs.getInt("adoption_id"),
                        rs.getInt("status"),
                        rs.getInt("kitten_id"),
                        rs.getString("username"),
                        rs.getDate("date_expire")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    // Query Adoption from a user
    public ArrayList<Adoption> getAdoptions(String username){
        ArrayList<Adoption> res = new ArrayList<>();
        
        ResultSet rs = query(new Query("adoption").whereEqual("username", username));
        
        try {
            while(rs.next()){
                res.add(new Adoption(
                        rs.getInt("adoption_id"),
                        rs.getInt("status"),
                        rs.getInt("kitten_id"),
                        username,
                        rs.getDate("date_expire")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    // Create Adoption
    public boolean createAdoption(String username, int kitten_id, Date expire){
        // Make sure first that kitten have available status
        ResultSet rs = query(new Query("kitten")
                .whereEqual("kitten_id", kitten_id).and()
                .whereEqual("status", Kitten.STATUS.AVAILABLE.ordinal()));
           
        try {
            // If kitten mentioned is not available
            if(!rs.next()) return false;
               
            // Set kitten status to processing
            update(new UpdateMap("kitten").set("status", Kitten.STATUS.PROCESSING.ordinal()).whereEqual("kitten_id", kitten_id));
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Then create adoption
        return insert(new InsertMap("adoption")
                .$("username", username)
                .$("kitten_id", kitten_id)
                .$("date_expire", expire.toString())
        );
    }
    // Update Adoption
    public boolean updateAdoption(Adoption before, Adoption after){
        // Reset first the status of the before kitten to available
        update(new UpdateMap("kitten")
                .set("status", Kitten.STATUS.AVAILABLE.ordinal())
                .whereEqual("kitten_id", before.getKitten_id())
        );
        
        // Then change the status of the after kitten to processing
        update(new UpdateMap("kitten")
                .set("status", Kitten.STATUS.PROCESSING.ordinal())
                .whereEqual("kitten_id", after.getKitten_id())
        );
        
        // Then finally, change the satatus, kitten id and username of the said transaction (uses the id of the before object)
        return update(new UpdateMap("adoption")
                .set("kitten_id", after.getKitten_id())
                .set("username", after.getUsername())
                .whereEqual("adoption_id", before.getId())
        );
    }
    // Verify Adoption
    public boolean verifyAdoption(Adoption a){
        // Set kitten status to unavailable
        update(new UpdateMap("kitten")
                .set("status", Kitten.STATUS.UNAVAILABLE.ordinal())
                .whereEqual("kitten_id", a.getKitten_id())
        );
        
        // can only verify ongoing adoptions
        if(a.getStatus() != Adoption.STATUS.ONGOING.ordinal()) return false;
        
        // Then update adoption status to successful
        return update(new UpdateMap("adoption")
                .set("status", Adoption.STATUS.SUCCESSFUL.ordinal())
                .whereEqual("adoption_id", a.getId())
        );
    }
    
    // Cancel Adoption
    public boolean cancelAdoption(Adoption a){
        // Set kitten status to available
        update(new UpdateMap("kitten")
                .set("status", Kitten.STATUS.AVAILABLE.ordinal())
                .whereEqual("kitten_id", a.getKitten_id())
        );
        
        // can only cancel ongoing adoptions
        if(a.getStatus() != Adoption.STATUS.ONGOING.ordinal()) return false;
        
        // Then update adoption status to cancelled
        return update(new UpdateMap("adoption")
                .set("status", Adoption.STATUS.CANCELED.ordinal())
                .whereEqual("adoption_id", a.getId())
        );
    }
    // Return Adoption
    public boolean returnAdoption(Adoption a){
        // Set kitten status back to available
        update(new UpdateMap("kitten")
                .set("status", Kitten.STATUS.AVAILABLE.ordinal())
                .whereEqual("kitten_id", a.getKitten_id())
        );
        
        // can only return successful adoptions
        if(a.getStatus() != Adoption.STATUS.SUCCESSFUL.ordinal()) return false;
        
        return update(new UpdateMap("adoption")
                .set("status", Adoption.STATUS.RETURNED.ordinal())
                .whereEqual("adoption_id", a.getId())
        );
    }
    // Extend Adoption
    public boolean extendAdoption(Adoption a, int numOfDays){
        long extension = numOfDays * 86400000;
        
        Date d = a.getDate_expire();
        d.setTime(d.getTime() + extension);
        
        return update(new UpdateMap("adoption")
                .set("date_expire", d.toString())
                .whereEqual("adoption_id", a.getId())
        );
    }
    
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
    // Get Adopter Details
    public Adopter getAdopterDetails(String username){
        ResultSet rs = query(new Query("user").whereEqual("username", username));
        Adopter u = null;
        
        try {
            if(rs.next()){
                u = new Adopter(rs.getString("username"), rs.getString("password"), rs.getString("email"));
                u.setAddress(rs.getString("address"));
                u.setContact(rs.getString("contact"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return u;
    }
    // Get All Adopter Names
    public ArrayList<String> getAllAdopter(){
        ArrayList<String> res = new ArrayList<>();
        ResultSet rs = query(new Query("user").whereEqual("type", 0));
        
        try {
            while(rs.next()){
                res.add(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    // Update Adopter
    public boolean updateAdopter(UpdateMap um){
        System.out.println(um.toString());
        return update(um);
    }
}
