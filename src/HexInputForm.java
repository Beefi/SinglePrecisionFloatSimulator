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

                float floatOutput = controller.ComputeHex(hexInput);
                String strFormat = String.format("%."+fixedPoint+"f", floatOutput);
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
                float floatOutput = controller.ComputeHex(hexInput);

                outputField.setText(String.valueOf(floatOutput));

                if (printBox.isSelected()) {
                    StringBuilder sb = new StringBuilder("0x" + hexInput + " : " + floatOutput);
                    controller.PrintToText(sb);
                }
            }
        });
    }
}
