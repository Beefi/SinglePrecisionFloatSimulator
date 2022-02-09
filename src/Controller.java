import org.firebirdsql.decimal.Decimal32;
import org.firebirdsql.decimal.Decimal64;
import org.firebirdsql.decimal.DenselyPackedDecimalCodec;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;

public class Controller {

    public Controller() {

    }

    public Decimal32 ComputeBinary(String signBit, String second, String third, String fourth, String fifth, String sixth, String seventh, String eighth, String ninth) {
        String wholeString = second + third + fourth + fifth + sixth + seventh + eighth + ninth;

        byte[] array = new BigInteger(wholeString, 2).toByteArray();

        Decimal32 decimal32 = Decimal32.parseBytes(array);

        return Decimal32.valueOf(decimal32.toBigDecimal());
    }

    public Decimal32 ComputeHex(String hex) {
        byte[] array = new BigInteger(hex, 16).toByteArray();

        Decimal32 decimal32 = Decimal32.parseBytes(array);

        return Decimal32.valueOf(decimal32.toBigDecimal());
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
