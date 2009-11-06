/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Server;

import java.io.IOException;
import sun.misc.BASE64Decoder;

/**
 *
 * @author moataz
 */
public class Bdecoder {

BASE64Decoder Base64Decoder = new BASE64Decoder();

public byte [] decodeBuffer(String buffer) throws IOException{


    buffer=ReplaceNewLineCharacters(buffer);
    return Base64Decoder.decodeBuffer(buffer);


}

String ReplaceNewLineCharacters(String buffer){

    buffer = buffer.replace((char)3, (char)13);
    buffer = buffer.replace((char)2, (char)10);
    return buffer;
}

}
