/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import static core.RestManApp.getCustomer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author mert_
 */
public class AddOrderofBooking extends javax.swing.JFrame {

    /**
     * Creates new form AddOrderofBooking
     */
    public AddOrderofBooking() {
        initComponents();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE ); //do nothing if close
  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        SsnText = new javax.swing.JTextField();
        BookingDateText = new javax.swing.JTextField();
        OrderDetailsText = new javax.swing.JTextField();
        ExtraNotesText = new javax.swing.JTextField();
        AddOrderButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("SSN:");

        jLabel2.setText("Booking Date(dd/mm/yy):");

        jLabel3.setText("Order Details:");

        jLabel4.setText("Extra Notes:");

        AddOrderButton.setText("ADD ORDER");
        AddOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddOrderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SsnText)
                            .addComponent(BookingDateText)
                            .addComponent(OrderDetailsText)
                            .addComponent(ExtraNotesText, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(AddOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(SsnText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(BookingDateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(OrderDetailsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ExtraNotesText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(AddOrderButton)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddOrderButtonActionPerformed
        // TODO add your handling code here:
        int ssn=Integer.parseInt(SsnText.getText());
        ArrayList<inRestOrder> order;
        Customer custom;
        ArrayList<Booking> bookings = null; 
        custom=getCustomer(ssn);
        if(custom==null){
               JOptionPane.showMessageDialog(this,"UNDEFINED SSN NUMBER!");
        }
        else{
            bookings=custom.getBookings();
            for(Booking a:bookings){
                Date date1 = a.getBookingDate();
                DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
                
                String strDate = dateFormat.format(date1);
                System.out.println(strDate);
                if(strDate.equals(BookingDateText.getText())){
                  //  order=
                    JOptionPane.showMessageDialog(this,"Transaction is Succesful!");
                    order=a.getOrders();
                    order.add (new inRestOrder(OrderDetailsText.getText(),ExtraNotesText.getText()));
                    a.setOrders(order);
                }
                else{
                    JOptionPane.showMessageDialog(this,"UNDEFINED BOOKING DATE!");

                }
            }
        }
    }//GEN-LAST:event_AddOrderButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddOrderButton;
    private javax.swing.JTextField BookingDateText;
    private javax.swing.JTextField ExtraNotesText;
    private javax.swing.JTextField OrderDetailsText;
    private javax.swing.JTextField SsnText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
