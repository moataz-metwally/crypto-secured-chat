/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clientchat;

import java.io.FileInputStream;


import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Moataz Metwally
 */
public class Player {

    private String filename;
    InputStream inStream;
    AudioStream audioStream;

    public Player(String filename) {
        try {
            this.filename = filename;
            inStream = new FileInputStream(filename);
            audioStream = new AudioStream(inStream);
        } catch (Exception ex) {

        }
    }

    public synchronized void play() {
        try {

            AudioPlayer.player.start(audioStream);

        } catch (Exception e) {

        }
    }
}
