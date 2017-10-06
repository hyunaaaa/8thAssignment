
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Calculator extends JFrame implements ActionListener {
	
   JPanel Button, Result;
   JLabel label1, label2;
   JButton[] button = null;
   
   String s1 = "";
   String s2 = "";
   String s3 = "";
   String number[] = { " ", " ", " " };
   String[] Str = { "Del", "AC", "C", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "0", "00", ".",
         "=" };

   public Calculator() {
      super("Calculator");
      Result = new JPanel(new GridLayout(2, 1));
      Result.setBackground(Color.WHITE);
      label1 = new JLabel("", JLabel.RIGHT);
      label2 = new JLabel("0", JLabel.RIGHT);

      Result.add(label1);
      Result.add(label2);

      Button = new JPanel(new GridLayout(5, 4, 2, 2));
      Button.setBackground(Color.WHITE);
      button = new JButton[Str.length];

      for (int i = 0; i < Str.length; i++) {
         button[i] = new JButton(Str[i]);
         Button.add(button[i]);
         button[i].addActionListener(this);
      }

      getContentPane().add("North", Result);
      getContentPane().add("Center", Button);
      setSize(300, 400);
      setResizable(false);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      String tButton = e.getActionCommand();

      if (tButton.equals("1") || tButton.equals("2") || tButton.equals("3") || tButton.equals("4")
            || tButton.equals("5") || tButton.equals("6") || tButton.equals("7") || tButton.equals("8")
            || tButton.equals("9")) {
         if (s3.equals("0")) {
            s3 = "";
         }
         s3 += tButton;
         label2.setText(s3);
      } 
      else if (tButton.equals("Del")) {
         int length = label2.getText().length();
         if (length == 1) {
            s3 = "";
            label2.setText("0");
         } 
         else {
            if (!"".equals(s3)) {
               s3 = s3.substring(0, length - 1);
               label2.setText(s3);
            }
         }
      } 
      else if (tButton.equals("AC")) {
         s3 = "";
         s1 = "";
         label2.setText("0");
      } 
      else if (tButton.equals("C")) {
         s1 = "";
         s2 = "";
         s3 = "";
         label1.setText("");
         label2.setText("0");
         number[0] = " ";
         number[1] = " ";
         number[2] = " ";
      } 
      else if (tButton.equals("0") || tButton.equals("00")) {
         if (!("".equals(s3))) {
            if (!"0".equals(s3)) {
               s3 += tButton;
               label2.setText(s3);
            }
         } 
         else {
            s3 = "0";
         }
      }
      else if (tButton.equals(".")) {
         if ("".equals(s3)) {
            s3 = "0" + tButton;
         } 
         else {
            if (s3.indexOf(".") == -1) {
               s3 += tButton;
            }
         }
         label2.setText(s3);
      } 
      else if (tButton.equals("=")) {
         if (!"".equals(s3)) {
            number[2] = s3;
         }
         if ("".equals(s2)) {
            if (!number[1].equals(" ")) {
               s1 = Calculate();
               if ("0으로 나눌 수 없음.".equals(s1)) {
                  s1 = "";
                  s2 = "";
                  s3 = "";
                  number[0] = " ";
                  number[1] = " ";
                  number[2] = " ";
               } 
               else {
                  number[0] = s1;
                  label2.setText(s1);
               }
            }
            label1.setText(s2);
         } 
         else {
            if (" ".equals(number[2])) {
               number[2] = s3;
            }
            s1 = number[0];

            if ("0으로 나눌 수 없음.".equals(s1)) {
               label2.setText(s1);
               s1 = "";
               s2 = "";
               s3 = "";
               number[0] = " ";
               number[1] = " ";
               number[2] = " ";
            } 
            else {
               s1 = Calculate();

               if (!"0으로 나눌 수 없음.".equals(s1)) {
                  s2 = "";
                  number[0] = s1;
                  label1.setText(s2);
                  s3 = "";
               } 
               else {
                  label2.setText("0으로 나눌 수 없음.");
                  label1.setText("");
                  s1 = "";
                  s2 = "";
                  s3 = "";
                  number[0] = " ";
                  number[1] = " ";
                  number[2] = " ";
               }
               System.out.println("?");
            }
         }
      } 
      else if (tButton.equals("/") || tButton.equals("*") || tButton.equals("-") || tButton.equals("+")) {
         if ("".equals(s2)) {
            if ("".equals(s3)) {
               if ("".equals(s1)) {
                  s2 = "0" + tButton;
                  number[0] = "0";
               } 
               else {
                  s2 = s1 + tButton;
               }
            } 
            else {   
               if (!"".equals(s1)) {
                  s2 = s1 + tButton;
               }
               s2 = s3 + tButton;
               number[0] = s3;
            }
            number[1] = tButton;
         } 
         else {
            if ("".equals(s3)) {
               s2 = s2.substring(0, s2.length() - 1) + tButton;
               number[1] = tButton;
            } 
            else {
               s2 += s3 + tButton;
               number[2] = s3;
               s1 = Calculate();
               number[1] = tButton;
               label2.setText(s1);
               number[0] = s1;
            }
         }
         if (number[1].equals("/") && (number[2].equals(" 0") || number[2].equals("0"))) {

         } 
         else {
            s3 = "";
            label1.setText(s2);
         }

      }

   }

   public String Calculate() {
      String num = "";
      String Num1 = "";
      String Num2 = "";

      if (number[0].indexOf(".") != -1) {
         int index1 = number[0].indexOf(".");

         Num1 = number[0].substring(index1, number[0].length());

         if (Num1.replaceAll("0", "").equals(".")) {
            number[0] = number[0].substring(0, index1);
         }
      }

      if (number[2].indexOf(".") != -1) {
         int index2 = number[2].indexOf(".");

         Num2 = number[2].substring(index2, number[2].length());

         if (Num2.replaceAll("0", "").equals(".")) {
            number[2] = number[2].substring(0, index2);
         }
      }
      if (number[1].equals("*")) {
         if (number[0].indexOf(".") != -1 || number[2].indexOf(".") != -1) {
            num = (Double.parseDouble(number[0]) * Double.parseDouble(number[2])) + "";
         } 
         else {
            if ("".equals(number[2])) {
               num = s3;
            } 
            else {
               if (!"".equals(s1)) {
                  number[0] = s1;
               }
               num = (Long.parseLong(number[0]) * Long.parseLong(number[2])) + "";
               s1 = num;
               number[0] = s1;
            }
         }
      } 
      else if (number[1].equals("+")) {
         if (number[0].indexOf(".") != -1 || number[2].indexOf(".") != -1) {
            num = (Double.parseDouble(number[0]) + Double.parseDouble(number[2])) + "";} 
         else {
            if ("".equals(number[2])) {
               num = s3;} else {
               if (!"".equals(s1)) {
                  number[0] = s1;
               }
               num = (Long.parseLong(number[0]) + Long.parseLong(number[2])) + "";
               s1 = num;
               number[0] = s1;}
            }
         } 
      else if (number[1].equals("-")) {
         if (number[0].indexOf(".") != -1 || number[2].indexOf(".") != -1) {
            num = (Double.parseDouble(number[0]) - Double.parseDouble(number[2])) + "";
         } 
         else {
            if ("".equals(number[2])) {
               num = s3;
            } 
            else {
               if (!"".equals(s1)) {
                  number[0] = s1;
               }
               num = (Long.parseLong(number[0]) - Long.parseLong(number[2])) + "";
               s1 = num;
               number[0] = s1;
            }
         }
      } 
      else if (number[1].equals("/")) {
         number[2].trim();
         if (number[0].indexOf(".") != -1 || number[2].indexOf(".") != -1) {
            num = (Double.parseDouble(number[0]) / Double.parseDouble(number[2])) + "";
         } 
         else {
            if (number[2].equals("0")) {
               num = "0으로 나눌 수 없습니다.";
               s3 = "";
            } 
            else {
               if ("".equals(number[2])) {
                  num = s3;
               } 
               else {
                  if (!"".equals(s1)) {
                     number[0] = s1;
                  }
                  num = (Double.parseDouble(number[0]) / Double.parseDouble(number[2])) + "";

                  if (num.indexOf(".") != -1) {
                     int index3 = num.indexOf(".");
                     String testNum3 = num.substring(index3, num.length());
                     if (testNum3.replaceAll("0", "").equals(".")) {
                        num = num.substring(0, index3);
                     }
                  }
                  s1 = num;
                  number[0] = s1;
               }
            }
         }
      }
      return num;

   }

   public static void main(String[] args) {
      new Calculator();
   }
}