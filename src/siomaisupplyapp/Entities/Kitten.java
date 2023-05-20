/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package siomaisupplyapp.Entities;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import siomaisupplyapp.Builders.Updatable;
import siomaisupplyapp.Builders.UpdateMap;
import siomaisupplyapp.Frames.HubFrame;
import siomaisupplyapp.Frames.KittenProfile;
/**
 *
 * @author Josh
 */
public class Kitten implements Updatable{
    public static final int pWidth = 150, pHeight = 150;

    @Override
    public UpdateMap genUpdateMap(Object updatedValue) {
        if(!(updatedValue instanceof Kitten)) return null;
        
        Kitten o = (Kitten) updatedValue;
        UpdateMap um = new UpdateMap("kitten");
        
        if(id != o.id) System.out.println(String.format("Kitten: %s:%d is updated with the value of %s:%d", name, id, o.name, o.id));
        
        if(status != o.status) um.set("status", o.status);
        else um.set("status", 0);
        
        if(!name.equals(o.name)) um.set("name", o.name);
        if(!breed.equals(o.breed)) um.set("breed", o.breed);
        if(!description.equals(o.description)) um.set("description", o.description);
        if(!birthdate.equals(o.birthdate)) um.set("birthdate", o.birthdate.toString());
        
        um.whereEqual("kitten_id", id);
        
        return um;
    }
    
    public enum STATUS {
        AVAILABLE,
        PROCESSING,
        UNAVAILABLE,
    }
    
    private int id, status;
    private String name, breed, description;
    Date birthdate;

    public Kitten() {
    }

    public Kitten(int id, int status, String name, String breed, String description, Date birthdate) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.breed = breed;
        this.description = description;
        this.birthdate = birthdate;
    }

    public Kitten(int status, String name) {
        this.status = status;
        this.name = name;
    }
    
    public void copy(Kitten other){
        id = other.id;
        status = other.status;
        name = other.name;
        breed = other.breed;
        description = other.description;
        birthdate = other.birthdate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(status).append("] ").append(name).append(" - ").append(id).append("\n");
        sb.append(breed).append("\n");
        sb.append(birthdate.toString()).append("\n");
        sb.append(description).append("\n");
        
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Kitten)) return false;
        
        Kitten other = (Kitten) o;
        return other.getId() == id ||
              (other.getName().equals(name) &&
               other.getBreed().equals(breed) &&
               other.getBirthdate().equals(birthdate));
    }
    
    public String multiLineDesc(){
        StringBuilder sb = new StringBuilder("<html>");
        
        String[] linebreaks = description.split("\n");
        
        for (String line : linebreaks) {
            if(!sb.isEmpty()) sb.append("<br>");
            sb.append(line);
        }
        
        return sb.toString();
    }
    
    public String seperatingBreeds(){
        StringBuilder sb = new StringBuilder("<html>");
        
        String[] breeds = breed.split(",");
        for (String b : breeds) {
            sb.append("<u>").append(b).append("</u>\t");
        }
        
        return sb.toString();
    }
    
    public String formattedDate(){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM dd, yyyy");
        return dateFormatter.format(birthdate);
    }
    
    public JPanel getPanelComponent(HubFrame last){
        JPanel p = new JPanel();
        p.setBackground(Color.LIGHT_GRAY);
        
        JButton view = new JButton("View More");
        view.setVerticalAlignment(JButton.BOTTOM);
        
        
        JLabel jlName = new JLabel(String.format("[%d] %s", id, name)),
               jlBreed = new JLabel(seperatingBreeds()),
               jlDate = new JLabel(formattedDate());
        
        
        javax.swing.GroupLayout pLayout = new javax.swing.GroupLayout(p);
        p.setLayout(pLayout);
        pLayout.setHorizontalGroup(
            pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pLayout.createSequentialGroup()
                        .addGap(0, 652, Short.MAX_VALUE)
                        .addComponent(view))
                    .addGroup(pLayout.createSequentialGroup()
                        .addGroup(pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlName)
                            .addComponent(jlBreed)
                            .addComponent(jlDate))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pLayout.setVerticalGroup(
            pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlBreed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(view)
                .addContainerGap())
        );
        
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showKittenProfile(last.username, last);
            }
        });
        
        return p;
    }
    
    private void showKittenProfile(String username, JFrame last){
        KittenProfile f = new KittenProfile(username, this);
        f.setVisible(true);
        f.setLocationRelativeTo(last);
        last.dispose();
    }
}
