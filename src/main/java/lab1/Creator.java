package lab1;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.nio.ByteBuffer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Creator {
    public static byte[] buildResponse(Cryptor mc) throws BadPaddingException, IllegalBlockSizeException {
       /* BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("Write the message you want to send: ");*/
        final String message = "Very secret info";
        final byte[] my_message = mc.encipher(message);

        final byte[] header = new byte[]{
                0x13,
                0,
                0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0A,
                0x0, 0x0, 0x0, (byte) my_message.length
        };

        return ByteBuffer.allocate(14 + 2 + my_message.length + 2)
                .put(header)
                .putShort(CRC16.crc16(header, 0, header.length))
                .put(my_message)
                .putShort(CRC16.crc16(my_message,0, my_message.length))
                .array();
    }
}
