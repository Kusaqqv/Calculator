import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Calculator {
    private JFrame frame;
    private JPanel panel;
    private JTextField textField;

    private double num1 = 0;
    private String operator = "";

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        textField = new JTextField();

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        JButton button;

        for (int i = 0; i < buttons.length; i++) {
            button = new JButton(buttons[i]);
            panel.add(button);
            button.addActionListener(new ButtonListener());
        }

        frame.getContentPane().add(BorderLayout.NORTH, textField);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String buttonText = ((JButton) e.getSource()).getText();

            if (buttonText.matches("[0-9.]")) {
                textField.setText(textField.getText() + buttonText);
            } else if (buttonText.matches("[/*+-]")) {
                operator = buttonText;
                num1 = Double.parseDouble(textField.getText());
                textField.setText("");
            } else if (buttonText.equals("=")) {
                double num2 = Double.parseDouble(textField.getText());
                double result = 0;

                switch (operator) {
                    case "/":
                        result = num1 / num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "+":
                        result = num1 + num2;
                        break;
                }

                textField.setText(Double.toString(result));
                operator = "";
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}