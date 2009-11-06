/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author moataz
 *The Connection Class is for handling clients sockets...
 * simply displays "Hello World!" to the standard output.
 */
public class Connection extends Thread {

    public static final char delimiter = 1;
    public static ArrayList connectionList = new ArrayList();

    public static Hashtable connectionsNamesHash = new Hashtable();
    public static ArrayList nameList = new ArrayList();

    private Server serverForm;
    private Socket client;
    private BufferedReader scan = null;
    private InputStreamReader sd;
    private volatile boolean running = true;
    private volatile boolean isClosed = false;
    private String name = null;
    private Crypto AES;

    public Crypto getAES() {
        return AES;
    }

    public static String asHex(byte buf[]) {

        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;

        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }

            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }

        return strbuf.toString();
    }

    public Connection(Server serverForm, Socket client) {
        AES = new Crypto();

        this.serverForm = serverForm;
        this.client = client;

        start();
    }

    public void run() {
        try {
            scan = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        while (running) {
            try {

                String lineRead = scan.readLine();  // Reading line from the buffer
                String[] externalCommandSplitted= lineRead.split("" + Connection.delimiter); // Spliting the recieved message to extract the command

                System.out.println(lineRead);

                System.out.println("========================================="); // for debugging

                if (externalCommandSplitted[0].equals("e")) { // if the message is encrypted  'e' letter represents that what is coming after is encrypted message

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    String decryptedInternalCommand = AES.decrypt(externalCommandSplitted[1]);
                    String[] decryptedInternalCommandSplitted = decryptedInternalCommand.split("" + Connection.delimiter);

                    if (decryptedInternalCommandSplitted[0].equals("msg")) {

                        if (name != null) {
                            sendChatMessageToAll(decryptedInternalCommandSplitted[1], name, this);
                        }
                    } else if (decryptedInternalCommandSplitted[0].equals("lgn")) {
                        if (!decryptedInternalCommandSplitted[1].isEmpty()) {
                            if (!nameList.contains(decryptedInternalCommandSplitted[1])) { // check if user(nickname) is not already exist 
                                connectionsNamesHash.put(decryptedInternalCommandSplitted[1], this); // add the new user(nickname) to the hash table associated with the connection

                                connectionList.add(this); // add the connection to the connection list
                                name = decryptedInternalCommandSplitted[1];
                                sendGeneralMessage("ok"); // Connection is established
                                if (nameList.size() > 0) {   // send the name list
                                    String nameListString = "lst";
                                    for (Object singleName : nameList) {
                                       
                                        nameListString += Connection.delimiter + (String) singleName;
                                    }
                                    sendGeneralMessage(nameListString);
                                }
                                sendGeneralMessageToAll("addu" + Connection.delimiter + name, this); // send command to all other users to add this user(nickname) to their list
                                nameList.add(decryptedInternalCommandSplitted[1]); // add the user(nickname) to the name list

                                this.serverForm.getjNameList().setListData(nameList.toArray());
                            } else {  // if the user(nickname) is already exist inform the client that it is already exist
                                sendGeneralMessage("exist"); // send command to the user that the nickname is already exist
                                break;
                            }
                        }
                    } else if (decryptedInternalCommandSplitted[0].equals("pmsgs")) {
                        if (!decryptedInternalCommandSplitted[1].isEmpty()) {

                            Connection con = (Connection) connectionsNamesHash.get(decryptedInternalCommandSplitted[1]);
                            if (name != null) {
                                con.sendGeneralMessage("pmsgr" + Connection.delimiter + name + Connection.delimiter + decryptedInternalCommandSplitted[2]);
                            }

                        }

                    } else {

                        break;
                    }

/*                externalCommandSplitted[0].equals("e")          */
                } 
                
                
                
                
                else if (externalCommandSplitted[0].equals("d")) { // if the message is decrytped ... during the handshaking and authentication
                    ///////////////////////////////////////////////////////////////////////////////////////////

                    if (externalCommandSplitted[1].equals("auth")) {
                        RSA t = new RSA(new BigInteger(externalCommandSplitted[2]), new BigInteger(externalCommandSplitted[3]));
                        sendGeneralMessageDecrypted("Renc" + Connection.delimiter + t.encryptString(AES.getSharedKey()));

                    }

////////////////////////////////////////////////////////////////////////////////////////////////////
                }

            } catch (Exception s) {
                break;

            }

        }

        try {
            closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public synchronized void closeConnection() throws Exception {

        if (!isClosed) {

            sendGeneralMessage("fnsh");
            sendGeneralMessageToAll("rmvu" + Connection.delimiter + name, this);
            scan.close();

            shutDownThread();
            
            if (getUserName() != null) {
                connectionsNamesHash.remove(getUserName());
            }

            nameList.remove(getUserName());
            serverForm.getjNameList().setListData(nameList.toArray());
            client.close();
            connectionList.remove(this);
        }
        isClosed = true;
    }

    public Socket getClient() {
        return client;
    }

    public String getUserName() {

        return this.name;
    }

    public static void sendChatMessageToAll(String s, String name, Connection sender) {

        sendGeneralMessageToAll("msgall" + Connection.delimiter + name + Connection.delimiter + s, sender);
    }

    public static void sendGeneralMessageToAll(String msg, Connection sender) {

        for (Object singleConnection : connectionList) {

            Connection con = (Connection) singleConnection;
            if (con != sender) {
                con.sendGeneralMessage(msg);
            }

        }

    }

    public void sendGeneralMessage(String msg) {
        try {

            msg = AES.encrypt(msg);
            msg = "e" + Connection.delimiter + msg;
            PrintStream writer = null;
            writer = new PrintStream(this.client.getOutputStream());
            writer.println(msg);
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }

    public void sendGeneralMessageDecrypted(String msg) {
        try {

            msg = "d" + Connection.delimiter + msg;
            PrintStream writer = null;
            writer = new PrintStream(this.client.getOutputStream());
            writer.println(msg);
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }

    public void shutDownThread() {

        this.running = false;
    }

}
