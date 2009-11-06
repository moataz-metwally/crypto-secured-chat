/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clientchat;

import sun.misc.BASE64Encoder;

/**
 *
 * @author moataz
 */
public class Bencoder {
BASE64Encoder Base64Encoder = new BASE64Encoder();
/**
 * This method is used for encoding the buffer to base64 format to be easily parsed on the other side
 * @param buffer Buffer to be encoded 
 * @return string carries Base64 encoded buffer
 */
String encodeBuffer(byte[] buffer){


    return ReplaceNewLineCharacters(Base64Encoder.encode(buffer));
}
/**
 * This method is used for replacing new line ASCII to non character bytes not to intervene the command context which uses the new line to define the commands
 * @param buffer Buffer to be replaced 
 * @return buffer it carries the buffer after replacing the new line characters
 */

String ReplaceNewLineCharacters(String buffer){

    buffer = buffer.replace((char)13,(char)3);
    buffer = buffer.replace((char)10, (char)2);
    return buffer;
}

}
