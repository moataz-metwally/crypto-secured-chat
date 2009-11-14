/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * Serv.java
 *
 * Created on Jan 6, 2011, 12:39:16 PM
 */
package Server;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.swing.ImageIcon;
import javax.swing.JList;

/**
 *
 * @author moataz
 */
public class Server extends javax.swing.JFrame implements Runnable {

    private ServerSocket socketServer;
    private PrintStream Writer;
    private Scanner reader;
    private Thread main, connection;
    private Socket temp = null;
    private static volatile boolean running = true;
    Image devilImage;

    public Server() {
        ImageIcon devilImageIcon = new ImageIcon("Devil.png");
        devilImage = devilImageIcon.getImage();
        setIconImage(devilImage);
        initComponents();
        setSize(300, 360);
        stopButton.setEnabled(false);
        main = new Thread(this);

    }

    public JList getjNameList() {
        return jNameList;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jNameList = new javax.swing.JList();
        stopButton = new javax.swing.JButton();
        labelServerStatus = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        portTextBox = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Secured Server");
        getContentPane().setLayout(null);

        jScrollPane2.setViewportView(jNameList);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(200, 60, 81, 235);

        stopButton.setText("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });
        getContentPane().add(stopButton);
        stopButton.setBounds(40, 230, 129, 43);

        labelServerStatus.setForeground(new java.awt.Color(0, 0, 204));
        labelServerStatus.setText("Server is oFF");
        getContentPane().add(labelServerStatus);
        labelServerStatus.setBounds(50, 280, 110, 10);

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        getContentPane().add(startButton);
        startButton.setBounds(40, 180, 129, 41);

        portTextBox.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        portTextBox.setText("9999");
        getContentPane().add(portTextBox);
        portTextBox.setBounds(70, 140, 76, 27);

        jLabel2.setText("Port");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 140, 30, 20);

        getAccessibleContext().setAccessibleName("Server..Crypto");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphic2D = (Graphics2D) g;
        graphic2D.drawImage(devilImage, 30, 30, devilImage.getWidth(rootPane), devilImage.getHeight(null), null);

    }


    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed

        running = true;
        main = new Thread(this);

        main.start();


}//GEN-LAST:event_startButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        try {

            running = false;
            socketServer.close();
            Object[] conn = Connection.connectionList.toArray();
            for (int index = 0; index < conn.length; index++) {
                Connection conn_temp = (Connection) conn[index];
                conn_temp.closeConnection();

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        startButton.setEnabled(true);
        stopButton.setEnabled(false);

        // TODO add your handling code here:
    }//GEN-LAST:event_stopButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jNameList;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelServerStatus;
    private javax.swing.JTextField portTextBox;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        try {
            socketServer = new ServerSocket(Integer.parseInt(portTextBox.getText()));
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        stopButton.setEnabled(true);
        startButton.setEnabled(false);
        labelServerStatus.setText("Server is On");
        while (running) {
            try {
                if (socketServer.isClosed()) {
                    break;
                }
                this.temp = socketServer.accept();
                Connection conn = new Connection(this, temp);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                try {

                    socketServer.close();

                } catch (IOException ex1) {
                    System.out.println(ex1.getMessage());
                }
                break;
            }

        }

        stopButton.setEnabled(false);
        startButton.enable(true);
        labelServerStatus.setText("Server is oFF");
    }

}
