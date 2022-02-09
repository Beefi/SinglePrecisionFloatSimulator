import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        MenuForm menuForm = new MenuForm();
        menuForm.pack();
        menuForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuForm.setVisible(true);
    }

}
