package lab1;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.io.IOException;

public class main {

    public static void main(String[] args) throws BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchPaddingException, DecoderException, IOException {
        Cryptor mc = new Cryptor();
        final byte[] respond = Creator.buildResponse(mc);
        System.out.println("Package to send: "+ Arrays.toString(Hex.encodeHex(respond)));
        System.out.println("\nReceived package:");
        Receiver.decodePacket(Creator.buildResponse(mc),mc);
        //Receiver.decodeMessage(Creator.buildResponse(mc),mc);
    }
}
