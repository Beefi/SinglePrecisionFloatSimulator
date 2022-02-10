import org.firebirdsql.decimal.Decimal32;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    public Controller() {

    }

    public Decimal32 ComputeBinary(String signBit, String second, String third, String fourth, String fifth, String sixth, String seventh, String eighth, String ninth) {
        boolean sign = true;
        String wholeString = null;

        switch (signBit) {
            case "0":
                wholeString = signBit + second + third + fourth + fifth + sixth + seventh + eighth + ninth;
                sign = true;
                break;
            case "1":
                wholeString = second + third + fourth + fifth + sixth + seventh + eighth + ninth;
                sign = false;
                break;
            default:
                break;
        }

        byte[] array = new BigInteger(wholeString, 2).toByteArray();

        Decimal32 decimal32 = Decimal32.parseBytes(array);

        if (sign) {
            decimal32 = Decimal32.valueOf(decimal32.toBigDecimal());
        } else {
            decimal32 = Decimal32.valueOf(decimal32.toBigDecimal().multiply(new BigDecimal(-1)));
        }

        return decimal32;
    }

    public float ComputeHex(String hex) {
        Long intBits = Long.parseLong(hex, 16);
        float floatOutput = Float.intBitsToFloat(intBits.intValue());

        return floatOutput;
//        String wholeString = Integer.toBinaryString(Float.floatToIntBits(floatOutput));
//        String signBit = String.valueOf(wholeString.charAt(0));
//
//        Controller controller = new Controller();
//
//        byte[] array = new BigInteger(wholeString, 2).toByteArray();
//
//        int sign = array[0];
//
//        List<Byte> byteList = new ArrayList<Byte>();
//
//        for (byte b : array) {
//            byteList.add(b);
//        }
//
//        byteList.remove(0);
//
//        Byte[] shifted = byteList.toArray(new Byte[byteList.size()]);
//
//        array = new byte[array.length-1];
//        for (int i = 0; i < shifted.length; i++) {
//            array[i] = shifted[i].byteValue();
//        }
//
//        Decimal32 decimal32 = Decimal32.parseBytes(array);
//
//        if (sign == 0) {
//            decimal32 = Decimal32.valueOf(decimal32.toBigDecimal());
//        } else {
//            decimal32 = Decimal32.valueOf(decimal32.toBigDecimal().multiply(new BigDecimal(-1)));
//        }
//
//        return decimal32;
    }

    void PrintToText(StringBuilder sb) {
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            BufferedWriter bw = new BufferedWriter(myWriter);

            bw.append(sb);
            bw.close();
            myWriter.close();
            ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "output.txt");
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
