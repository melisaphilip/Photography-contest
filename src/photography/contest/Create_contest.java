/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photography.contest;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 *
 * @author Lenovo
 */
public class Create_contest extends javax.swing.JFrame {

    /**
     * Creates new form Create_contest
     */
    public Create_contest() {
        initComponents();
        File file = new File("C:\\Users\\Lenovo\\OneDrive\\Documents\\NetBeansProjects\\Photography contest\\src\\images\\create contest.jpg");
       BufferedImage bim = null;//new BufferedImage(jLabel2.getWidth(),jLabel2.getHeight(),BufferedImage.TYPE_INT_ARGB);
       try{
       bim = ImageIO.read(file);
       }
       catch(IOException e){}
       
       ImageIcon ic = new ImageIcon(bim);
       Image img = ic.getImage();
       Image myimg = img.getScaledInstance(600,460,Image.SCALE_SMOOTH);
       ImageIcon finimg = new ImageIcon(myimg);
       jLabel1.setIcon(finimg);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contestName = new javax.swing.JTextField();
        contestTopic = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        contestName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        contestName.setBorder(null);
        contestName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contestNameActionPerformed(evt);
            }
        });
        getContentPane().add(contestName, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 145, 35));

        contestTopic.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        contestTopic.setBorder(null);
        contestTopic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contestTopicActionPerformed(evt);
            }
        });
        getContentPane().add(contestTopic, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 223, 145, 35));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jButton1.setText("PUBLISH");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 335, 82, 27));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        jButton2.setText("BACK");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jButton2.setMaximumSize(new java.awt.Dimension(97, 27));
        jButton2.setMinimumSize(new java.awt.Dimension(97, 27));
        jButton2.setPreferredSize(new java.awt.Dimension(97, 27));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 335, 82, 27));

        jSpinner1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jSpinner1.setModel(new javax.swing.SpinnerDateModel());
        jSpinner1.setEditor(new javax.swing.JSpinner.DateEditor(jSpinner1, "dd-MM-yyyy"));
        getContentPane().add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 265, 145, 35));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/create contest.jpg"))); // NOI18N
        jLabel1.setText("jLabel6");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 600, 470));

        setSize(new java.awt.Dimension(600, 450));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        java.util.Date dateChosen =  (java.util.Date)jSpinner1.getValue();
        //String str = jSpinner1.getValue().toString();

        int date = dateChosen.getDate();
        int month = ( dateChosen.getMonth() ) + 1;
        int year = ( dateChosen.getYear() ) + 1900;
        String sqldate = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(date);
        java.sql.Date dt = java.sql.Date.valueOf(sqldate);
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/public_photography","root","Melisa@28");
            String sql="INSERT INTO contest_details(contest_name,contest_topic,last_date) VALUES (?, ?, ?)";
            
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setString(1,contestName.getText());
            pstmt.setString(2,contestTopic.getText());
            pstmt.setDate(3,dt);
            
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "insertion succesful");
            Admin_login frame = new Admin_login();
            frame.setVisible(true);
            this.dispose();
            con.close();
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void contestNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contestNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contestNameActionPerformed

    private void contestTopicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contestTopicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contestTopicActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Admin_login frame = new Admin_login();
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Create_contest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Create_contest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Create_contest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Create_contest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Create_contest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField contestName;
    private javax.swing.JTextField contestTopic;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSpinner jSpinner1;
    // End of variables declaration//GEN-END:variables
}
