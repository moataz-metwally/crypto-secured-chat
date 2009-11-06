/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * PrivateChatWindow.java
 *
 * Created on Jan 28, 2011, 10:27:12 PM
 */
package clientchat;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author moataz
 */
public class PrivateChatWindow extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form PrivateChatWindow
     */
    private String name;

    public PrivateChatWindow(String name) {

        initComponents();
        this.name = name;
        setTitle("You chat with :" + name);

        doc = ConversationTextPanel.getDocument();

    }

    public synchronized void sh() {

        shack();
        toFront();
        requestFocus();
        sendTextBox.requestFocus();
    }

    Document doc;

    public synchronized void appendText(String text, SimpleAttributeSet x) {
        try {

            ConversationTextPanel.setCaretPosition(doc.getLength());
            doc.insertString(doc.getLength(), text, x);
            ConversationTextPanel.setCaretPosition(ConversationTextPanel.getText().length());
            ConversationTextPanel.setCaretPosition(ConversationTextPanel.getText().length());

        } catch (Exception ex) {

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sendButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        sendTextBox = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        ConversationTextPanel = new javax.swing.JTextPane();
        buzzButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        sendButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        sendTextBox.setColumns(20);
        sendTextBox.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        sendTextBox.setLineWrap(true);
        sendTextBox.setRows(5);
        sendTextBox.setMargin(new java.awt.Insets(10, 10, 10, 10));
        sendTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sendTextBoxKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(sendTextBox);

        ConversationTextPanel.setEditable(false);
        jScrollPane1.setViewportView(ConversationTextPanel);

        buzzButton.setBackground(new java.awt.Color(204, 204, 255));
        buzzButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buzzButton.setForeground(new java.awt.Color(204, 0, 0));
        buzzButton.setText("Buzz");
        buzzButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 51)));
        buzzButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buzzButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                        .addGap(77, 77, 77))
                    .addComponent(buzzButton, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buzzButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendTextBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sendTextBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sendButtonActionPerformed(null);
        }

    }

    public JTextArea getjTextArea2() {
        return sendTextBox;
    }//GEN-LAST:event_sendTextBoxKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        Login.windowhash.removeWindow(name);
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        String q = sendTextBox.getText();
        q = q.trim();
        if (q.length() > 0) {

            sendTextBox.setEnabled(false);

            Login.SendGeneralMessage("pmsgs" + (char) 1 + name + (char) 1 + q);

//        jTextArea1.append(name+":\r\n"+q+"\r\n");
            appendText(Chat.chatUserName + ":\r\n", Chat.ScreenName);
            appendText(q + "\r\n", Chat.text);
            sendTextBox.setText("");
            sendTextBox.setEnabled(true);
            sendTextBox.requestFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_sendButtonActionPerformed

    public synchronized void disableT() {

        sendTextBox.setEnabled(false);
        sendButton.setEnabled(false);
        buzzButton.setEnabled(false);
    }

    public synchronized void enableT() {

        sendTextBox.setEnabled(true);
        sendButton.setEnabled(true);
        buzzButton.setEnabled(true);
    }
    private void buzzButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buzzButtonActionPerformed

        Login.SendGeneralMessage("pmsgs" + (char) 1 + name + (char) 1 + "buzz");
        appendText("BUZZ\r\n", Chat.buzz);
        // TODO add your handling code here:
    }//GEN-LAST:event_buzzButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane ConversationTextPanel;
    private javax.swing.JButton buzzButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextArea sendTextBox;
    // End of variables declaration//GEN-END:variables

    public boolean equals(Object obj) {

        return this == obj;
    }

    public void shack() {

        new Thread(PrivateChatWindow.this).start();
    }
    private static final int SHAKES = 25;
    private static final int SHAKE_FORCE = 5;
    private static final Random RND = new Random();

    @Override
    public void run() {
        final Rectangle origRect = PrivateChatWindow.this.getBounds();
        for (int i = 0; i < SHAKES; i++) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    int x;
                    if (RND.nextBoolean()) {
                        x = SHAKE_FORCE;
                    } else {
                        x = -SHAKE_FORCE;
                    }

                    int y;
                    if (RND.nextBoolean()) {
                        y = SHAKE_FORCE;
                    } else {
                        y = -SHAKE_FORCE;
                    }

                    PrivateChatWindow.this.setBounds(origRect.x + x, origRect.y + y, origRect.width, origRect.height);
                }
            });
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }

        PrivateChatWindow.this.setBounds(origRect); // Reset to original

    }

}
