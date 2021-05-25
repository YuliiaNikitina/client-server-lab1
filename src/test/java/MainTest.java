import lab1.Creator;
import lab1.Cryptor;
import lab1.Receiver;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTest {

    @Test
    void checkWhether_ValidInputMessage() throws BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchPaddingException {
        Cryptor mc = new Cryptor();
        Receiver.decodePacket(Creator.buildResponse(mc),mc);
    }

    @Test
    void checkWhether_InvalidMagicByte() throws NoSuchAlgorithmException, NoSuchPaddingException {
        Cryptor mc = new Cryptor();
        String str = "00A0BF";
        char[] test = str.toCharArray();
        assertThrows(
                IllegalArgumentException.class,
                () -> Receiver.decodePacket(Hex.decodeHex(test),mc)
        );
    }

    @Test
    void check_CipherCode() throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        Cryptor mc = new Cryptor();
        final String message = "my message";
        final String result = new String(mc.decipher(mc.encipher(message)));
        assertEquals(message, result);
    }

    @Test
    void checkWhether_InvalidCrc() throws NoSuchAlgorithmException, NoSuchPaddingException {
        Cryptor mc = new Cryptor();
        final String input = "1300000000000000000a000000300a8b6c0221f35d79ec1715362980276b7c96a5ec7b0f8e40428fff0f7f54652c00dce9ea";

        char[] test = input.toCharArray();
        assertThrows(
                IllegalArgumentException.class,
                () -> Receiver.decodePacket(Hex.decodeHex(test),mc)
        );
    }

}
