import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.awt.Color;
import javax.swing.*;
import java.lang.Math;
public class Calculator {
    private JFrame window;
    private JTextField inText;
    private JButton btnC, btnBack, btnAdd, btnDiv, btnMod, btnSub,
                    btn0, btn1, btn2, btn3, bt4, btn5, btn6,btn7, btn8, btn9;
    private boolean addWrite = true;

    public Calculator() {
        window = new JFrame("Calculator");
        window.setSize(400,600);
        window.setLocationRelativeTo(null);

        inText = new JTextField("0");
        inText.setBounds(0,0,350,70);
        inText.setEditable(false);
        inText.setBackground(Color.white);
        inText.setFont(new Font("Arial", Font.PLAIN, 35));
        window.add(inText);

        btnC = initBtn("C", 1,1, event -> {
            repaintFont();
            String str = inText.getText();
            StringBuilder str2 = new StringBuilder();
            for (int i = 0; i < (str.length() - 1); i++) {
                str2.append(str.charAt(i));
            }
            if (str2.toString().equals("")) {
                inText.setText("0");
            } else {
                inText.setText(str2.toString());
            }
        });


        btnBack = initBtn("<-", 1, 1, event -> {
            repaintFont();
            String str = inText.getText();
            StringBuilder str2 = new StringBuilder();
            for (int i = 0; i < (str.length() - 1); i++) {
                str2.append(str.charAt(i));
            }
            if (str2.toString().equals("")) {
                inText.setText("0");
            } else {
                inText.setText(str2.toString());
            }
        });

    }

    private JButton initBtn(String label, int x, int y, ActionListener event) {
        JButton btn = new JButton(label);
        btn.setBounds(x, y, 400, 600);
        btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(event);
        btn.setFocusable(false);
        window.add(btn);

        return btn;
    }

    private void repaintFont() {
        inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
    }
    public static void main(String[] args) {
        new Calculator();
    }
}