/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * Login.java
 *
 * Created on Jan 28, 2011, 7:16:49 PM
 */
package clientchat;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.io.PrintStream;
import java.io.PrintWriter;

import java.math.BigInteger;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Login extends javax.swing.JFrame implements Runnable {

    private static Socket clientSocket;

    public static RSA rsa;
    private volatile boolean running;
    private Thread clientSockeThread;
    private BufferedReader scan;
    public static PrivateWindowsHash windowhash = new PrivateWindowsHash();
    public static Player wav;
    public static Player buzz;
    public static final String WAVFILE = "message.wav";
    public static final String BUZZFILE = "buzz.wav";
    public static Crypto AES;

    public static Socket getSocket() {
        return clientSocket;
    }
    private Chat chat;

    public Login() {

        initComponents();

        wav = new Player(Login.WAVFILE);
        buzz = new Player(Login.BUZZFILE);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginButton = new javax.swing.JButton();
        nicknameTextBox = new javax.swing.JTextField();
        severAddressTextBox = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Secured Chat.");

        loginButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        severAddressTextBox.setText("localhost");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Server:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Your name:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 51));
        jLabel3.setText("Secured Chat");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(severAddressTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(nicknameTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                .addContainerGap(56, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(severAddressTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nicknameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        if (nicknameTextBox.getText().length() > 0) {

            try {
                clientSocket = new Socket(severAddressTextBox.getText(), 9999, true);

                chat = new Chat(nicknameTextBox.getText());

                clientSockeThread = new Thread(this);
                clientSockeThread.start();
                loginButton.setEnabled(false);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error Done:", JOptionPane.OK_OPTION);
            }
        } else {

            JOptionPane.showMessageDialog(this, "Fill the Name field ...", "Error Done:", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        System.setProperty("file.encoding", "UTF-8");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginButton;
    private javax.swing.JTextField nicknameTextBox;
    private javax.swing.JTextField severAddressTextBox;
    // End of variables declaration//GEN-END:variables

    public String lineReadFromSocket;
    public String[] lineReadFromSocketSplitted;

    public void run() {
        try {

            rsa = new RSA(512, 2048);
            SendGeneralMessageDecrypted("auth" + (char) 1 + rsa.getPublicKey().toString() + (char) 1 + rsa.getModulus());

            running = true;
            chat = new Chat(nicknameTextBox.getText());
            //scan = new Scanner(s.getInputStream());
            scan = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (running) {

                lineReadFromSocket = scan.readLine();

                lineReadFromSocketSplitted = lineReadFromSocket.split("" + (char) 1);
                
                System.out.println(lineReadFromSocket);
                System.out.println("=========================================");

                ///////////////////////////////
                SwingUtilities.invokeAndWait(new Runnable() {
                    public void run() {

                        if (lineReadFromSocketSplitted[0].equals("e")) {

//*************************************************************************************************************//
                            ///////////////////////////// Data Recieved  //////////////////////////////////////////////
                            String decryptedMessage = AES.decrypt(lineReadFromSocketSplitted[1]);
                            String[] decryptedMessageSplittedArguments = decryptedMessage.split("" + (char) 1);
                            String command = decryptedMessageSplittedArguments[0];
                            if (decryptedMessage.equals("fnsh")) {
                                SendGeneralMessage("mm");
                                running = false;
                            } else if (decryptedMessage.equals("ok")) {
                                showChat();
                            } else if (command.equals("lst")) {
                                for (int index = 1; index < decryptedMessageSplittedArguments.length; index++) {
                                    chat.InsertTOList(decryptedMessageSplittedArguments[index]);
                                }
                            } else if (command.equals("addu")) {
                                if (Login.windowhash.checkName(decryptedMessageSplittedArguments[1])) {
                                    PrivateChatWindow t = Login.windowhash.getWindowByName(decryptedMessageSplittedArguments[1]);
                                    t.enableT();

                                }
                                chat.InsertTOList(decryptedMessageSplittedArguments[1]);

                            } else if (command.equals("rmvu")) {
                                if (Login.windowhash.checkName(decryptedMessageSplittedArguments[1])) {

                                    PrivateChatWindow t = Login.windowhash.getWindowByName(decryptedMessageSplittedArguments[1]);
                                    t.disableT();
                                }
                                chat.RemoveTOList(decryptedMessageSplittedArguments[1]);

                            } else if (command.equals("msgall")) {
                                //appendText(name + ":\r\n", ScreenName);
                                // appendText(q + "\r\n", text);
                                // chat.getjTextArea2().append(ar[1]+":\r\n"+enc.decrypt(ar[2])+"\r\n");
                                chat.appendText(decryptedMessageSplittedArguments[1] + ":\r\n", chat.getScreenName());
                                chat.appendText(decryptedMessageSplittedArguments[2] + "\r\n", chat.getText());
                                chat.scrollDown();
                            } else if (command.equals("exist")) {

                                JOptionPane.showMessageDialog(Login.this, "this name already exist!!!!", "Error Done:", JOptionPane.OK_OPTION);
                            } else if (command.equals("pmsgr")) {
//============================================================================

                                if (!Login.windowhash.checkName(decryptedMessageSplittedArguments[1])) {

                                    PrivateChatWindow t = new PrivateChatWindow(decryptedMessageSplittedArguments[1]);
                                    if (decryptedMessageSplittedArguments[2].equals("buzz")) {

                                        t.appendText("BUZZ\r\n", Chat.buzz);
                                        Login.windowhash.addWindow(decryptedMessageSplittedArguments[1], t);
                                        buzz.play();
                                        t.sh();

                                    } else {

                                        t.appendText(decryptedMessageSplittedArguments[1] + ":\r\n", chat.getScreenName());
                                        t.appendText(decryptedMessageSplittedArguments[2] + "\r\n", chat.getText());

                                        //  t.getjTextArea1().append(ar[1]+":\r\n"+enc.decrypt(ar[2])+"\r\n");
                                        Login.windowhash.addWindow(decryptedMessageSplittedArguments[1], t);
                                        if (t.isActive()) {
                                            wav.play();
                                        }
                                        t.show();
                                    }

                                } else {

                                    PrivateChatWindow t = Login.windowhash.getWindowByName(decryptedMessageSplittedArguments[1]);

                                    if (decryptedMessageSplittedArguments[2].equals("buzz")) {

                                        t.appendText("BUZZ\r\n", Chat.buzz);
                                        buzz.play();
                                        t.show();
                                        t.shack();

                                    } else {
                                        // t.getjTextArea1().append(ar[1]+":\r\n"+enc.decrypt(ar[2])+"\r\n");
                                        t.appendText(decryptedMessageSplittedArguments[1] + ":\r\n", chat.getScreenName());
                                        t.appendText(decryptedMessageSplittedArguments[2] + "\r\n", chat.getText());
                                        wav.play();
                                        t.show();
                                    }

                                }

                                //================================================================================
                            }

                            //*************************************************************************************************************//
                        }
                        if (lineReadFromSocketSplitted[0].equals("d")) {

                            if (lineReadFromSocketSplitted[1].equals("Renc")) {
                                try {
                                    AES = new Crypto(rsa.decryptToString(new BigInteger(lineReadFromSocketSplitted[2])));
                                    SendGeneralMessage("lgn" + (char) 1 + nicknameTextBox.getText());
                                } catch (IOException ex) {
                                    System.out.print(ex.getMessage());
                                }

                            }

                            ///////////////////////////// Data Recieved  //////////////////////////////////////////////
                            //*************************************************************************************************************//
                        }

                        //*******************************************************************************************************************//
                    }
                });
            }

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ex) {

            }
            hideChat();

            loginButton.setEnabled(true);
        }

    }

    public void showChat() {

        this.hide();
        chat.show();

    }

    public void hideChat() {
        try {
            this.show();

            chat.dispose();

            for (Object privateChateWindowObject : Login.windowhash.getLst().toArray()) {

                PrivateChatWindow privateChatWindow = (PrivateChatWindow) privateChateWindowObject;
                privateChatWindow.dispose();
            }
        } catch (Exception e) {
        }

    }

    public static void SendGeneralMessage(String msg) {
        try {
            /* PrintStream writer = null;
            writer = new PrintStream(s.getOutputStream());

            writer.println(msg);
        
            writer.flush();
            *
             */
            msg = AES.encrypt(msg);
            msg = "e" + (char) 1 + msg;
            PrintWriter clientPrintWriter = new PrintWriter(clientSocket.getOutputStream());

            clientPrintWriter.println(msg);
            clientPrintWriter.flush();

            /*     byte [] x= msg.getBytes();
          BufferedOutputStream w = new BufferedOutputStream(s.getOutputStream());
          w.write(x, 0, x.length);
          byte [] j ="\r\n\0".getBytes();
          w.write(j, 0, j.length);
          w.flush();


        *
             */
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }

    public static void SendGeneralMessageDecrypted(String msg) {
        try {
            /* PrintStream writer = null;
            writer = new PrintStream(s.getOutputStream());

            writer.println(msg);

            writer.flush();
            *
             */

            msg = "d" + (char) 1 + msg;
            PrintWriter clientPrintWriter = new PrintWriter(clientSocket.getOutputStream());

            clientPrintWriter.println(msg);
            clientPrintWriter.flush();

            /*     byte [] x= msg.getBytes();
          BufferedOutputStream w = new BufferedOutputStream(s.getOutputStream());
          w.write(x, 0, x.length);
          byte [] j ="\r\n\0".getBytes();
          w.write(j, 0, j.length);
          w.flush();


        *
             */
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }

    public static void closeConnection() {
        try {
            clientSocket.close();
        } catch (Exception ex) {

        }

    }

}
