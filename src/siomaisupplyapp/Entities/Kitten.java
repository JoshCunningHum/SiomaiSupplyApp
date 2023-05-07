/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package siomaisupplyapp.Entities;

import java.awt.Color;
import java.sql.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Josh
 */
public class Kitten {
    public enum STATUS {
        AVAILABLE ,
        PROCESSING,
        UNAVAILABLE
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

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Kitten)) return false;
        
        Kitten other = (Kitten) o;
        return other.getId() == id ||
              (other.getName().equals(name) &&
               other.getBreed().equals(breed) &&
               other.getBirthdate().equals(birthdate));
    }
    
    
    
    public JPanel getPanelComponent(){
        int padding = 10;
        
        JPanel p = new JPanel();
        p.setBackground(Color.LIGHT_GRAY);
        
        JButton view = new JButton("View More");
        view.setVerticalAlignment(JButton.BOTTOM);
        
        JPanel desc = new JPanel();
        desc.setLayout(new BoxLayout(desc, BoxLayout.Y_AXIS));
        
        JLabel jlName = new JLabel(name),
               jlBreed = new JLabel(breed),
               jlDate = new JLabel(birthdate.toString());
        
        
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
        
        return p;
    }
}
