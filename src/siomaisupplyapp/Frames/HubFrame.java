/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package siomaisupplyapp.Frames;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import siomaisupplyapp.Entities.Kitten;
import siomaisupplyapp.Entities.KittenList;
import siomaisupplyapp.SiomaiSupplyApp;

/**
 *
 * @author Josh
 */
public final class HubFrame extends javax.swing.JFrame {
    
    int kp_width, kp_height, limit = 15, max_rows = 3, max_cols = 5, page = 1;
    public String username;
    public KittenList active_kittens, all, showing_kittens;
    GridLayout gl;
    
    /**
     * Creates new form HubFrame
     */
    public HubFrame(String username) {
        initComponents();
        this.username = username;
        
        kp_width = pnlKittens.getWidth();
        kp_height = pnlKittens.getHeight();
        gl = (GridLayout) pnlKittens.getLayout();
        
        all = SiomaiSupplyApp.c.queryKittens(0, 0);
        active_kittens = new KittenList();
        active_kittens.add(all);
        
        calcDimensions();
        displayKittens(active_kittens.page(page - 1, limit));
        
        updateNavButtons();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel2 = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        btnSupport = new javax.swing.JButton();
        btnCarrier = new javax.swing.JButton();
        btnAccount = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cmbSort = new javax.swing.JComboBox<>();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        lblPage = new javax.swing.JLabel();
        inpSearch = new javax.swing.JTextField();
        btnClearSearch = new javax.swing.JButton();
        btnToggleSortOrder = new javax.swing.JToggleButton();
        pnlKittens = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(750, 500));
        setPreferredSize(new java.awt.Dimension(750, 500));
        setSize(new java.awt.Dimension(750, 500));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        pnlHeader.setBackground(new java.awt.Color(0, 51, 51));
        pnlHeader.setPreferredSize(new java.awt.Dimension(750, 50));

        btnSupport.setText("Customer Support");
        btnSupport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupportActionPerformed(evt);
            }
        });

        btnCarrier.setText("Carrier");
        btnCarrier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarrierActionPerformed(evt);
            }
        });

        btnAccount.setText("Account");
        btnAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccountActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PURRFECT MATCH");

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSupport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAccount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCarrier)
                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btnSupport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCarrier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(pnlHeader, java.awt.BorderLayout.PAGE_START);

        pnlBody.setBackground(new java.awt.Color(0, 102, 102));
        pnlBody.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("Sort By ");

        cmbSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Age", "Name", "Breed"}));
        cmbSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSortActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.setEnabled(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrev.setText("<");
        btnPrev.setEnabled(false);
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        lblPage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPage.setText("1");

        inpSearch.setMinimumSize(new java.awt.Dimension(71, 22));
        inpSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inpSearchKeyReleased(evt);
            }
        });

        btnClearSearch.setText("Clear");
        btnClearSearch.setMinimumSize(new java.awt.Dimension(71, 22));
        btnClearSearch.setPreferredSize(new java.awt.Dimension(71, 22));
        btnClearSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSearchActionPerformed(evt);
            }
        });

        btnToggleSortOrder.setText("🔽");
        btnToggleSortOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToggleSortOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inpSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClearSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnToggleSortOrder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 301, Short.MAX_VALUE)
                .addComponent(btnPrev)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPage, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNext)
                            .addComponent(btnPrev)
                            .addComponent(lblPage))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnClearSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(inpSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnToggleSortOrder)))
                .addContainerGap())
        );

        pnlBody.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pnlKittens.setBackground(new java.awt.Color(153, 153, 153));
        pnlKittens.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlKittens.setLayout(new java.awt.GridLayout(3, 5, 10, 10));
        pnlBody.add(pnlKittens, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlBody, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static boolean openWebpage(URI uri) {
    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
        try {
            desktop.browse(uri);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return false;
}

public static boolean openWebpage(URL url) {
    try {
        return openWebpage(url.toURI());
    } catch (URISyntaxException e) {
        e.printStackTrace();
    }
    return false;
}
    
    private void btnSupportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupportActionPerformed
        try {
            // TODO add your handling code here:
            URL link = new URL("https://www.facebook.com/catsofcebuitpark");
            openWebpage(link);
        } catch (MalformedURLException ex) {
            Logger.getLogger(HubFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSupportActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        prevPage();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void cmbSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSortActionPerformed
        // TODO add your handling code here:
        initiateSort();
    }//GEN-LAST:event_cmbSortActionPerformed

    private void initiateSort(){
        String selected = String.valueOf(cmbSort.getSelectedItem()),
               sortTo = null;
        
        switch(selected.toLowerCase()){
            case "age":
                sortTo = "birthdate";
                break;
            default:
                sortTo = selected.toLowerCase();
        }
        
        all = SiomaiSupplyApp.c.queryKittens(0, 0, sortTo, btnToggleSortOrder.isSelected());
        active_kittens = new KittenList();
        active_kittens.add(all);
        
        updateShownList();
    }
    
    private void calcDimensions(){
        int[] dims = new int[3];
        
        kp_width = pnlKittens.getWidth();
        kp_height = pnlKittens.getHeight();
        
        max_cols = kp_width / Kitten.pWidth;
        max_rows = kp_height / Kitten.pHeight;
        limit = max_cols * max_rows;
    }
    
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        calcDimensions();
        
        // TODO: RE-ADjust Margins
        gl.setRows((max_rows > max_cols) ? max_rows : 0);
        gl.setColumns(max_cols);
        
        updateShownList();
        System.out.println(max_cols + " " + max_rows + " " + limit + " Total: " + active_kittens.size());
    }//GEN-LAST:event_formComponentResized

    public void updateNavButtons(){
        boolean next = page - 1 >= active_kittens.size() / limit,
                prev = page <= 1;
        
        btnPrev.setEnabled(!prev);
        btnNext.setEnabled(!next);
        lblPage.setText(Integer.toString(page));
    }
    
    public void updateShownList(){
        int offset = (page - 1) * limit;
        if(offset > active_kittens.size()){
            // calculate nearest offset
            page = (active_kittens.size() / limit) + 1;
        }
        updateNavButtons();
        
        displayKittens(active_kittens.page(page - 1, limit));
    }
    
    public void prevPage(){
        page--;
        updateShownList();
    }
    
    public void nextPage(){
        page++;
        updateShownList();
    }
    
    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        nextPage();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccountActionPerformed
        // TODO add your handling code here:
        AccountFrame f = new AccountFrame(username);
        f.setVisible(true);
        f.setLocationRelativeTo(this);
        this.dispose();
    }//GEN-LAST:event_btnAccountActionPerformed

    private void btnClearSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSearchActionPerformed
        // TODO add your handling code here:
        inpSearch.setText("");
        active_kittens.clear();
        active_kittens.add(all);
        page = 1;
        updateShownList();
    }//GEN-LAST:event_btnClearSearchActionPerformed

    private void inpSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpSearchKeyReleased
        // TODO add your handling code here:
        
        String input = inpSearch.getText();
        
        active_kittens.clear();
            
        if(input.isEmpty()) active_kittens.add(all);
        
        // Filter the active kitten search
        active_kittens = all.filter(input, "name");
        active_kittens.add(all.filter(input, "breed"));
        
        updateShownList();
    }//GEN-LAST:event_inpSearchKeyReleased

    private void btnToggleSortOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToggleSortOrderActionPerformed
        // TODO add your handling code here:
        boolean isAsc = btnToggleSortOrder.isSelected();
        btnToggleSortOrder.setText(isAsc ? "🔼" : "🔽");
        initiateSort();
    }//GEN-LAST:event_btnToggleSortOrderActionPerformed

    private void btnCarrierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarrierActionPerformed
        // TODO add your handling code here:
        CarrierFrame f = new CarrierFrame(username);
        f.setVisible(true);
        f.setLocationRelativeTo(this);
        this.dispose();
    }//GEN-LAST:event_btnCarrierActionPerformed

    public void displayKittens(KittenList list){
        int count = 0, cols = max_cols, width = Kitten.pWidth, height = Kitten.pHeight;
        
        pnlKittens.removeAll();
        showing_kittens = list;
        for(Kitten k : list.kittens){
            JPanel p = k.getPanelComponent(this);
            
            pnlKittens.add(p);
            count++;
        }
        pnlKittens.revalidate();
        pnlKittens.repaint();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HubFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HubFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HubFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HubFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HubFrame("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccount;
    private javax.swing.JButton btnCarrier;
    private javax.swing.JButton btnClearSearch;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSupport;
    private javax.swing.JToggleButton btnToggleSortOrder;
    private javax.swing.JComboBox<String> cmbSort;
    private javax.swing.JTextField inpSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JLabel lblPage;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlKittens;
    // End of variables declaration//GEN-END:variables
}
