import com.sun.source.tree.UsesTree;

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
    int[] x = {20, 20 + 90, 200, 290, 380};
    int[] y = {60, 60 + 100, 60 + 180, 60 + 260, 60 + 340, 60 + 420};
    private JFrame window;
    private JTextField inText;
    private JButton btnC, btnBack, btnAdd, btnDiv, btnMult, btnSub, btnEqual, btnDot, btnMod,
                    btn0, btn1, btn2, btn3, btn4, btn5, btn6,btn7, btn8, btn9;
    private boolean addWrite = true;
    private boolean go = true;
    private double val = 0;
    private char opt = ' ';

    public Calculator() {
        window = new JFrame("Calculator");
        window.setSize(400,600);
        window.setLocationRelativeTo(null);

        inText = new JTextField("0");
        inText.setBounds(x[0], y[0],350,70);
        inText.setHorizontalAlignment(SwingConstants.RIGHT);
        inText.setEditable(false);
        inText.setBackground(Color.white);
        inText.setFont(new Font("Arial", Font.PLAIN, 35));
        window.add(inText);

        btnC = initBtn("C", x[0], y[1], event -> {
            repaintFont();
            inText.setText("0");
            opt = ' ';
            val = 0;
        });

        btnBack = initBtn("Del", x[1], y[1], event -> {
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

        btnMult = initBtn("*", x[2], y[1], event -> {
            repaintFont();
            if(Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '*';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '*';
                }
        });

        btnMod = initBtn("%", x[3], y[2], event -> {
            repaintFont();
            if(Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '%';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '%';
                }
        });

        btnAdd = initBtn("+",  x[3], y[4], event -> {
           repaintFont();
           if(Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
               if (go) {
                   val = calc(val, inText.getText(), opt);
                   if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                       inText.setText(String.valueOf((int) val));
                   } else {
                       inText.setText(String.valueOf(val));
                   }
                   opt = '+';
                   go = false;
                   addWrite = false;
               } else {
                   opt = '+';
               }
        });

        btnEqual = initBtn("=", x[2], y[5], event -> {
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '=';
                    addWrite = false;
                }
        });
        btnEqual.setSize(2 * 90, 70);

        btnSub = initBtn("-",  x[3], y[3], event -> {
            repaintFont();
            if(Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '-';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '-';
                }
        });

        btnDiv = initBtn("/", x[3], y[1], event -> {
            repaintFont();
            if(Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
                if (go) {
                    val = calc(val, inText.getText(), opt);
                    if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                        inText.setText(String.valueOf((int) val));
                    } else {
                        inText.setText(String.valueOf(val));
                    }
                    opt = '/';
                    go = false;
                    addWrite = false;
                } else {
                    opt = '/';
                }
        });

        btn7 = initBtn("7", x[0],y[2], event -> {
           repaintFont();
           if (addWrite) {
               if(Pattern.matches("[0]*", inText.getText())) {
                   inText.setText("7");
               } else {
                   inText.setText(inText.getText() + "7");
               }
           } else {
               inText.setText("7");
               addWrite = true;
           }
           go = true;
        });

        btn4 = initBtn("4", x[0],y[3], event -> {
            repaintFont();
            if (addWrite) {
                if(Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("4");
                } else {
                    inText.setText(inText.getText() + "4");
                }
            } else {
                inText.setText("4");
                addWrite = true;
            }
            go = true;
        });

        btn5 = initBtn("5", x[1],y[3], event -> {
            repaintFont();
            if (addWrite) {
                if(Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("5");
                } else {
                    inText.setText(inText.getText() + "5");
                }
            } else {
                inText.setText("5");
                addWrite = true;
            }
            go = true;
        });

        btn6 = initBtn("6", x[2],y[3], event -> {
            repaintFont();
            if (addWrite) {
                if(Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("6");
                } else {
                    inText.setText(inText.getText() + "6");
                }
            } else {
                inText.setText("6");
                addWrite = true;
            }
            go = true;
        });

        btn3 = initBtn("3", x[2],y[4], event -> {
            repaintFont();
            if (addWrite) {
                if(Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("3");
                } else {
                    inText.setText(inText.getText() + "3");
                }
            } else {
                inText.setText("3");
                addWrite = true;
            }
            go = true;
        });

        btn2 = initBtn("2", x[1],y[4], event -> {
            repaintFont();
            if (addWrite) {
                if(Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("2");
                } else {
                    inText.setText(inText.getText() + "2");
                }
            } else {
                inText.setText("2");
                addWrite = true;
            }
            go = true;
        });

        btn1 = initBtn("1", x[0],y[4], event -> {
            repaintFont();
            if (addWrite) {
                if(Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("1");
                } else {
                    inText.setText(inText.getText() + "1");
                }
            } else {
                inText.setText("1");
                addWrite = true;
            }
            go = true;
        });

        btn0 = initBtn("0", x[0],y[5], event -> {
            repaintFont();
            if (addWrite) {
                if(Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("0");
                } else {
                    inText.setText(inText.getText() + "0");
                }
            } else {
                inText.setText("0");
                addWrite = true;
            }
            go = true;
        });

        btnDot = initBtn(".", x[1],y[5], event -> {
            repaintFont();
            if (addWrite) {
                if(Pattern.matches("[0]*", inText.getText())) {
                    inText.setText(".");
                } else {
                    inText.setText(inText.getText() + ".");
                }
            } else {
                inText.setText(".");
                addWrite = true;
            }
            go = true;
        });

        btn8 = initBtn("8", x[1], y[2], event -> {
           repaintFont();
           if(addWrite) {
               if(Pattern.matches("[0]*", inText.getText())) {
                   inText.setText("8");
               } else {
                   inText.setText(inText.getText() + "8");
               }
           } else {
               inText.setText("8");
               addWrite = true;
           }
           go = true;
        });

        btn9 = initBtn("9", x[2], y[2], event -> {
            repaintFont();
            if(addWrite)
                if(Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("9");
                } else {
                    inText.setText(inText.getText() + "9");
                }
            else {
                inText.setText("7");
                addWrite = true;
            }
            go = true;
        });

        window.setLayout(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close button clicked? = End The process
        window.setVisible(true);
    }

    private JButton initBtn(String label, int x, int y, ActionListener event) {
        JButton btn = new JButton(label);
        btn.setBounds(x, y, 80, 70);
        btn.setFont(new Font("Arial", Font.PLAIN, 28));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(event);
        btn.setFocusable(false);
        window.add(btn);

        return btn;
    }

    private void repaintFont() {
        inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
    }

    public double calc(double x, String input, char opt){
        inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
        double y = Double.parseDouble(input);
        switch (opt) {
            case '+':
                return x+y;
            case '-':
                return x-y;
            case '*':
                return x*y;
            case '/':
                return x/y;
            case '^':
                return Math.pow(x,y);
            case '%':
                return x%y;
            default:
                inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
                return y;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}