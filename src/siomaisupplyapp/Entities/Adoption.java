/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package siomaisupplyapp.Entities;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import siomaisupplyapp.SiomaiSupplyApp;

/**
 *
 * @author Josh
 */
public class Adoption {
    public enum STATUS {
        ONGOING,
        SUCCESSFUL,
        CANCELED,
        RETURNED;
        
        public static String parse(int id){
            return STATUS.values()[id].name();
        }
    }
    
    public static java.awt.Color[] statusColors = new java.awt.Color[]{
                new java.awt.Color(255, 102, 0),
                new java.awt.Color(0, 153, 0),
                new java.awt.Color(255, 0, 51),
                new java.awt.Color(0, 0, 255)
            };
    
    public java.awt.Color getStatusColor(){
        return statusColors[status];
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

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Adoption)) return false;
        
        Adoption a = (Adoption) obj;
        return (id == 0 || id == a.id) && (status == a.status) &&
               (kitten_id == 0 || kitten_id == a.kitten_id) && (username == null || username.equals(a.username)) &&
               (date_expire == null || date_expire.equals(a.date_expire));
    }
    
    
   
    public JPanel getPanelComponent(String user){
        JPanel pnlAdopt = new javax.swing.JPanel();
        JLabel lblName = new javax.swing.JLabel();
        JProgressBar prgTimeLeft = new javax.swing.JProgressBar();
        JLabel lblTimeLeft = new javax.swing.JLabel();
        JLabel lblBreed = new javax.swing.JLabel();
        JLabel lblStatus = new javax.swing.JLabel();
        JButton btnReAdopt = new javax.swing.JButton();
        
        Kitten k = SiomaiSupplyApp.c.queryKitten(kitten_id);
        
        lblName.setText(k.getName());
        lblBreed.setText(k.seperatingBreeds());
        
        lblStatus.setText(STATUS.parse(status));
        lblStatus.setForeground(getStatusColor());
        
        Duration tl = Duration.between((new java.util.Date().toInstant()), new java.util.Date(date_expire.getTime()).toInstant());
        
        long days = tl.toDaysPart(),
             hrs = tl.toHoursPart(),
             mins = tl.toMinutesPart();
        
        java.awt.Color progColor = null;
        
        if(days >= 4) progColor = Adoption.statusColors[1]; // Green
        else if(days < 4) progColor = Adoption.statusColors[0]; // Orange
        if(days < 1) progColor = Adoption.statusColors[2]; // Red
        if(status == STATUS.CANCELED.ordinal()) progColor = Adoption.statusColors[2];
        if(status == STATUS.SUCCESSFUL.ordinal()) progColor = Adoption.statusColors[3];
        if(status == STATUS.RETURNED.ordinal()) progColor = Adoption.statusColors[3];
        
        StringBuilder sb = new StringBuilder();
        
        if(days > 0) sb.append(days).append(" day").append((days > 1 ? "s " : " "));
        if(days > 0 || hrs > 0) sb.append(hrs).append(" hr").append((hrs > 1 ? "s " : " "));
        sb.append(mins).append(" min").append((mins > 1 ? "s " : " "));
        
        lblTimeLeft.setText((status == STATUS.ONGOING.ordinal()) ? sb.toString() : (status == STATUS.SUCCESSFUL.ordinal()) ? "Recieved" :  "Failed Adoption");
        prgTimeLeft.setMinimum(0);
        prgTimeLeft.setMaximum(100);
        
        double ratio = (((double) tl.getSeconds()) / 604800f) * 100f;
        
        int timeLeftInt = (int) (100f - ratio);
        
        prgTimeLeft.setValue((int) ((days > 7 || status != STATUS.ONGOING.ordinal()) ? 100 : timeLeftInt));
        prgTimeLeft.setForeground(progColor);
        
        btnReAdopt.setVisible(status == STATUS.CANCELED.ordinal());
        btnReAdopt.setText("Re-Adopt");
        
        pnlAdopt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlAdopt.setMaximumSize(new java.awt.Dimension(32767, 75));

        lblName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N


        lblTimeLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);


        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);


        javax.swing.GroupLayout pnlAdoptLayout = new javax.swing.GroupLayout(pnlAdopt);
        pnlAdopt.setLayout(pnlAdoptLayout);
        pnlAdoptLayout.setHorizontalGroup(
            pnlAdoptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAdoptLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlAdoptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(lblBreed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                .addComponent(btnReAdopt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAdoptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(prgTimeLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(lblTimeLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlAdoptLayout.setVerticalGroup(
            pnlAdoptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAdoptLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAdoptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAdoptLayout.createSequentialGroup()
                        .addComponent(lblName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBreed))
                    .addGroup(pnlAdoptLayout.createSequentialGroup()
                        .addGroup(pnlAdoptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(prgTimeLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlAdoptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnReAdopt)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTimeLeft)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        
        return pnlAdopt;
    }
}
