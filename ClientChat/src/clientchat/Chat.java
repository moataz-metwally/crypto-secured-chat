/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * Chat.java
 *
 * Created on Jan 28, 2011, 7:18:04 PM
 */
package clientchat;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Chat extends javax.swing.JFrame {

    private DefaultListModel listModel;

    public static String chatUserName;
    /**
     * Creates new form Chat
     */

    static SimpleAttributeSet ScreenName, text, buzz;

    public Chat(String name) {

        Chat.chatUserName = name;
        setTitle("Your Screen name:" + name);
        listModel = new DefaultListModel();
        initComponents();

        ScreenName = new SimpleAttributeSet();

        StyleConstants.setBold(ScreenName, true);
        StyleConstants.setForeground(ScreenName, Color.red);
        StyleConstants.setFontSize(ScreenName, 20);
        StyleConstants.setBackground(ScreenName, Color.yellow);
        text = new SimpleAttributeSet();

        StyleConstants.setBold(text, true);
        StyleConstants.setFontSize(text, 20);
        StyleConstants.setForeground(text, Color.BLUE);

        doc = conversationTextPanel.getDocument();

        buzz = new SimpleAttributeSet();

        StyleConstants.setBold(buzz, true);
        StyleConstants.setFontSize(buzz, 26);
        StyleConstants.setForeground(buzz, Color.RED);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        sendTextBox = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        logOutButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        conversationTextPanel = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        usersList.setBorder(javax.swing.BorderFactory.createTitledBorder("Users"));
        usersList.setModel(listModel);
        usersList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(usersList);

        sendTextBox.setColumns(20);
        sendTextBox.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        sendTextBox.setLineWrap(true);
        sendTextBox.setRows(5);
        sendTextBox.setMargin(new java.awt.Insets(10, 10, 10, 10));
        sendTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sendTextBoxKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(sendTextBox);

        sendButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        logOutButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        logOutButton.setText("LogOut");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        conversationTextPanel.setEditable(false);
        jScrollPane3.setViewportView(conversationTextPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addGap(15, 15, 15)
                        .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                            .addComponent(logOutButton, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        try {
            String userMessage = sendTextBox.getText();
            userMessage = userMessage.trim();
            if (userMessage.length() > 0) {
                Login.SendGeneralMessage("msg" + (char) 1 + userMessage);
                appendText(chatUserName + ":\r\n", ScreenName);
                appendText(userMessage + "\r\n", text);
                scrollDown();

                sendTextBox.setText(null);
                sendTextBox.setCursor(new Cursor(0));
            }
        } catch (Exception ex) {

        }


    }//GEN-LAST:event_sendButtonActionPerformed


    private void sendTextBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sendTextBoxKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sendButtonActionPerformed(null);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_sendTextBoxKeyPressed


    private void usersListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersListMouseClicked

        String selectedName = (String) usersList.getSelectedValue();

        if (!Login.windowhash.checkName(selectedName)) {

            PrivateChatWindow privateChatWindow = new PrivateChatWindow(selectedName);
            Login.windowhash.addWindow(selectedName, privateChatWindow);
            privateChatWindow.show();

        } else {
            Login.windowhash.getWindowByName(selectedName).show();

        }


    }//GEN-LAST:event_usersListMouseClicked

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed

        Login.closeConnection();
        // TODO add your handling code here:
    }//GEN-LAST:event_logOutButtonActionPerformed

    public JButton getjButton1() {

        return sendButton;
    }

    public JList getjList1() {
        return usersList;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public JScrollPane getjScrollPane2() {
        return jScrollPane2;
    }

    public JTextArea getjTextArea1() {
        return sendTextBox;
    }

    public SimpleAttributeSet getScreenName() {
        return ScreenName;
    }

    public SimpleAttributeSet getText() {
        return text;
    }

    public void InsertTOList(String s) {

        listModel.addElement(s);
    }

    public void RemoveTOList(String s) {

        listModel.removeElement(s);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane conversationTextPanel;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton logOutButton;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextArea sendTextBox;
    private javax.swing.JList usersList;
    // End of variables declaration//GEN-END:variables

    Document doc;

    public synchronized void appendText(String text, SimpleAttributeSet textAttribute) {
        try {

            conversationTextPanel.setCaretPosition(doc.getLength());
            doc.insertString(doc.getLength(), text, textAttribute);
            conversationTextPanel.setCaretPosition(conversationTextPanel.getText().length());

        } catch (Exception ex) {

        }

    }

    public synchronized void scrollDown() {

        conversationTextPanel.setCaretPosition(doc.getLength());

    }

    public void remove() {
        try {
            doc.remove(0, doc.getLength());
        } catch (BadLocationException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
