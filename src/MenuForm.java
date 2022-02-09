import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuForm extends JFrame {
    private JButton hexButton;
    private JButton binaryButton;
    private JPanel menuPanel;

    public MenuForm() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        add(menuPanel);
        hexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HexInputForm hexForm = new HexInputForm();
                hexForm.pack();
                hexForm.setVisible(true);
                dispose();
            }
        });
        binaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BinaryInputForm binaryForm = new BinaryInputForm();
                binaryForm.pack();
                binaryForm.setVisible(true);
                dispose();
            }
        });
    }
}
