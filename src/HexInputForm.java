import org.firebirdsql.decimal.Decimal32;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class HexInputForm extends JFrame {
    private JTextField hexTextField;
    private JButton computeFixedButton;
    private JButton floatingPointButton;
    private JPanel inputPanel;
    private JTextField outputField;
    private JSpinner fixedSpinner;
    private JCheckBox printBox;

    public HexInputForm() {
        setTitle("Hexadecimal Input");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        add(inputPanel);

        hexTextField.setDocument(new JTextFieldLimit(8));

        computeFixedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller controller = new Controller();

                String hexInput = hexTextField.getText();
                String fixedPoint = fixedSpinner.getValue().toString();

                Long intBits = Long.parseLong(hexInput, 16);
                float floatOutput = Float.intBitsToFloat(intBits.intValue());

                if (!Double.isFinite(floatOutput)) {
                    outputField.setText(String.valueOf(floatOutput));

                    if (printBox.isSelected()) {
                        StringBuilder sb = new StringBuilder(hexInput + " : " + floatOutput);
                        controller.PrintToText(sb);
                    }
                }

                else {
                    float Output = controller.ComputeHex(hexInput);
                    BigDecimal bd = BigDecimal.valueOf(Output);

                    bd = bd.movePointLeft(Integer.parseInt(fixedPoint));
                    //bd = bd.scaleByPowerOfTen(Integer.parseInt(fixedPoint));
                    String strFormat = bd.toString();

                    outputField.setText(strFormat);

                    if (printBox.isSelected()) {
                        StringBuilder sb = new StringBuilder("0x" + hexInput + " : " + strFormat);
                        controller.PrintToText(sb);
                    }
                }
            }
        });
        floatingPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller controller = new Controller();

                String hexInput = hexTextField.getText();
                float Output = controller.ComputeHex(hexInput);

                String finalFormat = String.valueOf(Output);

                outputField.setText(finalFormat);

                if (printBox.isSelected()) {
                    StringBuilder sb = new StringBuilder("0x" + hexInput + " : " + finalFormat);
                    controller.PrintToText(sb);
                }
            }
        });
    }
}
