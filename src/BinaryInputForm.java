import org.firebirdsql.decimal.Decimal32;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class BinaryInputForm extends JFrame {

    private JTextField secondPartField;
    private JTextField thirdPartField;
    private JTextField fourthPartField;
    private JTextField fifthPartField;
    private JTextField sixthPartField;
    private JTextField seventhPartField;
    private JTextField eighthPartField;
    private JButton fixedButton;
    private JButton floatingPointButton;
    private JPanel inputPanel;
    private JSpinner signSpinner;
    private JSpinner fixedSpinner;
    private JTextField outputField;
    private JTextField ninthPartField;
    private JCheckBox printBox;

    public BinaryInputForm() {
        setTitle("Binary Input");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        add(inputPanel);

        int initValue = 0;
        int min = 0;
        int max = 1;
        int step = 1;
        SpinnerModel model = new SpinnerNumberModel(initValue, min, max, step);
        signSpinner.setModel(model);

        secondPartField.setDocument(new JTextFieldLimit(4));
        thirdPartField.setDocument(new JTextFieldLimit(4));
        fourthPartField.setDocument(new JTextFieldLimit(3));
        fifthPartField.setDocument(new JTextFieldLimit(4));
        sixthPartField.setDocument(new JTextFieldLimit(4));
        seventhPartField.setDocument(new JTextFieldLimit(4));
        eighthPartField.setDocument(new JTextFieldLimit(4));
        ninthPartField.setDocument(new JTextFieldLimit(4));

        fixedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller controller = new Controller();
                String signBit = signSpinner.getValue().toString();
                String secondPart = secondPartField.getText();
                String thirdPart = thirdPartField.getText();
                String fourthPart = fourthPartField.getText();
                String fifthPart = fifthPartField.getText();
                String sixthPart = sixthPartField.getText();
                String seventhPart = seventhPartField.getText();
                String eighthPart = eighthPartField.getText();
                String ninthPart = ninthPartField.getText();
                String fixedPoint = fixedSpinner.getValue().toString();

                String wholeString = signBit + secondPart + thirdPart + fourthPart + fifthPart + sixthPart + seventhPart + eighthPart + ninthPart;
                Long intBits = Long.parseLong(wholeString, 2);
                float floatOutput = Float.intBitsToFloat(intBits.intValue());

                if (!Double.isFinite(floatOutput)) {
                    outputField.setText(String.valueOf(floatOutput));

                    if (printBox.isSelected()) {
                        StringBuilder sb = new StringBuilder(wholeString + " : " + floatOutput);
                        controller.PrintToText(sb);
                    }
                }
                else {
                    Decimal32 Output = controller.ComputeBinary(signBit, secondPart, thirdPart, fourthPart, fifthPart, sixthPart, seventhPart, eighthPart, ninthPart);
                    BigDecimal bd = Output.toBigDecimal();

                    bd = bd.movePointLeft(Integer.parseInt(fixedPoint));
                    //bd = bd.scaleByPowerOfTen(Integer.parseInt(fixedPoint));
                    String finalFormat = bd.toString();

                    outputField.setText(finalFormat);

                    if (printBox.isSelected()) {
                        StringBuilder sb = new StringBuilder(wholeString + " : " + finalFormat);
                        controller.PrintToText(sb);
                    }
                }
            }
        });
        floatingPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller controller = new Controller();
                String signBit = signSpinner.getValue().toString();
                String secondPart = secondPartField.getText();
                String thirdPart = thirdPartField.getText();
                String fourthPart = fourthPartField.getText();
                String fifthPart = fifthPartField.getText();
                String sixthPart = sixthPartField.getText();
                String seventhPart = seventhPartField.getText();
                String eighthPart = eighthPartField.getText();
                String ninthPart = ninthPartField.getText();

                String wholeString = signBit + secondPart + thirdPart + fourthPart + fifthPart + sixthPart + seventhPart + eighthPart + ninthPart;
                Long intBits = Long.parseLong(wholeString, 2);
                float floatOutput = Float.intBitsToFloat(intBits.intValue());

                if (!Double.isFinite(floatOutput)) {
                    outputField.setText(String.valueOf(floatOutput));

                    if (printBox.isSelected()) {
                        StringBuilder sb = new StringBuilder(wholeString + " : " + floatOutput);
                        controller.PrintToText(sb);
                    }
                }
                else {
                    Decimal32 Output = controller.ComputeBinary(signBit, secondPart, thirdPart, fourthPart, fifthPart, sixthPart, seventhPart, eighthPart, ninthPart);

                    String finalFormat =  Output.toString();

                    outputField.setText(finalFormat);

                    if (printBox.isSelected()) {
                        StringBuilder sb = new StringBuilder(wholeString + " : "  + finalFormat);
                        controller.PrintToText(sb);
                    }
                }
            }
        });
    }
}
