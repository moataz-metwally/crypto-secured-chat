/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Server;

import sun.misc.BASE64Encoder;

/**
 *
 * @author moataz
 */
public class Bencoder {
BASE64Encoder Base64Encoder = new BASE64Encoder();

String encodeBuffer(byte[] buffer){


    return ReplaceNewLineCharacters(Base64Encoder.encode(buffer));
}


String ReplaceNewLineCharacters(String buffer){

    buffer = buffer.replace((char)13,(char)3);
    buffer = buffer.replace((char)10, (char)2);
    return buffer;
}

}
