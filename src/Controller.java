import java.io.*;

public class Controller {

    public Controller() {

    }

    public float ComputeBinary(String signBit, String second, String third, String fourth, String fifth, String sixth, String seventh, String eighth, String ninth) {
        String wholeString = signBit + second + third + fourth + fifth + sixth + seventh + eighth + ninth;
        int intBits = Integer.parseInt(wholeString, 2);
        float floatOutput = Float.intBitsToFloat(intBits);

        return floatOutput;
    }

    public float ComputeHex(String hex) {
        Long intBits = Long.parseLong(hex, 16);
        float floatOutput = Float.intBitsToFloat(intBits.intValue());

        return floatOutput;
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
