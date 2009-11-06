/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientchat;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author moataz
 */
public class PrivateWindowsHash {

    private Hashtable hashTable;
    private Vector lst;

    public void addWindow(String name, PrivateChatWindow privateWindow) {

        hashTable.put(name, privateWindow);
        lst.add(privateWindow);

    }

    public Vector getLst() {
        return lst;
    }

    public PrivateWindowsHash() {
        hashTable = new Hashtable();
        lst = new Vector();

    }

    public void removeWindow(String name) {

        lst.remove(getWindowByName(name));

        hashTable.remove(name);

    }

    public PrivateChatWindow getWindowByName(String name) {

        PrivateChatWindow privateWindow = (PrivateChatWindow) hashTable.get(name);

        return privateWindow;
    }

    public boolean checkName(String name) {

        return hashTable.containsKey(name);
    }

}
