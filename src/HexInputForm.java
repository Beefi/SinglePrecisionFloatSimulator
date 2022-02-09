import org.firebirdsql.decimal.Decimal32;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

                Decimal32 Output = controller.ComputeHex(hexInput);
                String strFormat = String.format("%."+fixedPoint+"f", Output);
                outputField.setText(strFormat);

                if (printBox.isSelected()) {
                    StringBuilder sb = new StringBuilder("0x" + hexInput + " : " + strFormat);
                    controller.PrintToText(sb);
                }
            }
        });
        floatingPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller controller = new Controller();

                String hexInput = hexTextField.getText();
                Decimal32 Output = controller.ComputeHex(hexInput);

                outputField.setText(String.valueOf(Output));

                if (printBox.isSelected()) {
                    StringBuilder sb = new StringBuilder("0x" + hexInput + " : " + Output);
                    controller.PrintToText(sb);
                }
            }
        });
    }
}
