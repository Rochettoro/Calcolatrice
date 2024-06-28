package calc;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Calcolatrice extends JFrame implements ActionListener {

    static String s = null;
    static float primo_fatt=0, secondo_fatt=0;
    static int operazione = 0;
    JTextField testo1, testo2;


    public static void main(String[] args) {
        new Calcolatrice();
    }

    public Calcolatrice() {
        setTitle("Calcolatrice");
        JLabel label = new JLabel(" ≡  Standard");
        label.setFont(new Font("Arial", Font.PLAIN, 25));
        label.setBounds(5,10,300,30);
        add(label);

        testo1 = new JTextField();
        testo1.setEnabled(false);
        testo1.setBounds(160,50,150,100);
        testo1.setHorizontalAlignment(SwingConstants.RIGHT);
        testo1.setFont(new Font("Arial", Font.PLAIN, 25));
        add(testo1);

        testo2 = new JTextField();
        testo2.setEnabled(false);
        testo2.setBounds(10,50,150,100);
        testo2.setHorizontalAlignment(SwingConstants.LEFT);
        testo2.setFont(new Font("Arial", Font.PLAIN, 25));
        add(testo2);

        try {
            String link = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/GNOME_Calculator_icon_2021.svg/128px-GNOME_Calculator_icon_2021.svg.png";
            URL url = new URL(link);
            BufferedImage image = ImageIO.read(url);
            setIconImage(image);
        } catch (IOException e) {
            System.out.println("Immagine non trovata");
        }

        JButton[] tastiCalc = new JButton[24];
        JLabel panel = new JLabel("standard");
        Border border = new LineBorder(Color.GRAY, 1, true);

        int j=0, c=0;
        setLayout(new GridLayout(7,4));
        add(panel);

        tastiCalc[0] = new JButton("%");
        tastiCalc[1] = new JButton("CE");
        tastiCalc[2] = new JButton("C");
        tastiCalc[3] = new JButton("AC");
        tastiCalc[4] = new JButton("1/x");
        tastiCalc[5] = new JButton("x²");
        tastiCalc[6] = new JButton("²√x");
        tastiCalc[7] = new JButton("÷");
        tastiCalc[8] = new JButton("7");
        tastiCalc[9] = new JButton("8");
        tastiCalc[10] = new JButton("9");
        tastiCalc[11] = new JButton("X");
        tastiCalc[12] = new JButton("4");
        tastiCalc[13] = new JButton("5");
        tastiCalc[14] = new JButton("6");
        tastiCalc[15] = new JButton("-");
        tastiCalc[16] = new JButton("1");
        tastiCalc[17] = new JButton("2");
        tastiCalc[18] = new JButton("3");
        tastiCalc[19] = new JButton("+");
        tastiCalc[20] = new JButton("+/-");
        tastiCalc[21] = new JButton("0");
        tastiCalc[22] = new JButton(".");
        tastiCalc[23] = new JButton("=");

        for(int i=0; i<24; i++){
            tastiCalc[i].setFont(new Font("Arial", Font.PLAIN, 20));
        }

        for(int i=0; i<24; i++){
            tastiCalc[i].addActionListener(this);
            if(i%4==0) { j++; c=0;}
            c++;
            tastiCalc[i].setBounds(80*c-78, 121+54*j, 80, 54);
            tastiCalc[i].setBorder(border);
            if(i==23){
                tastiCalc[i].setBackground(new Color(146,222,229));
            }else if(i>=0 && i<=7||i%4==3){
                tastiCalc[i].setBackground(new Color(228,228,228));
            }else{
                tastiCalc[i].setBackground(Color.WHITE);
            }
            add(tastiCalc[i]);
        }

        setLayout(null);
        setBounds(300, 400, 340, 540);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton b = (JButton) e.getSource();
            if(b.getText().equals("1")||b.getText().equals("2")||b.getText().equals("3")||b.getText().equals("4")||b.getText().equals("5")||b.getText().equals("6")||b.getText().equals("7")||b.getText().equals("8")||b.getText().equals("9")||b.getText().equals("0")){
                testo1.setText(testo1.getText()+b.getText());
            }else if(b.getText().equals("+")||b.getText().equals("-")||b.getText().equals("X")||b.getText().equals("÷")){
                if(operazione==0) {
                    testo2.setText(testo1.getText() + b.getText());
                    primo_fatt = Float.parseFloat(testo1.getText());
                    testo1.setText("");
                    s = b.getText();
                    operazione = 1;
                }else {
                    if(testo1.getText().isEmpty()) {
                        testo2.setText(testo2.getText().substring(0, testo2.getText().length() - 1) + b.getText());
                        s = b.getText();
                    }else{
                        calcolo();
                        testo2.setText(testo1.getText() + b.getText());
                        primo_fatt = Float.parseFloat(testo1.getText());
                        testo1.setText("");
                        s = b.getText();
                        operazione = 1;
                    }
                }
            }else if(b.getText().equals("=")){
                calcolo();
            }else if(b.getText().equals("C")){
                testo1.setText("");
            }else if(b.getText().equals("CE")){
                operazione = 0;
                testo1.setText("");
                testo2.setText("");
            }else if(b.getText().equals("AC")){
                if(!testo1.getText().isEmpty()) {
                    testo1.setText(testo1.getText().substring(0, testo1.getText().length()-1));
                    if (testo1.getText().endsWith(".")) {
                        testo1.setText(testo1.getText().substring(0, testo1.getText().length()-1));
                    }
                }
            }else if(b.getText().equals("1/x")){
                if(!testo1.getText().isEmpty())
                    testo1.setText(String.valueOf(1/Float.parseFloat(testo1.getText())));
            }else if(b.getText().equals("x²")){
                if(!testo1.getText().isEmpty())
                    testo1.setText(String.valueOf(Float.parseFloat(testo1.getText())*Float.parseFloat(testo1.getText())));
            }else if(b.getText().equals("²√x")){
                if(!testo1.getText().isEmpty())
                    testo1.setText(String.valueOf(Math.sqrt(Float.parseFloat(testo1.getText()))));
            }else if(b.getText().equals("%")){
                if(!testo1.getText().isEmpty())
                    testo1.setText(String.valueOf((Float.parseFloat(testo1.getText())/100)*Float.parseFloat(testo2.getText().substring(0,testo2.getText().length()-1))));
            }else if(b.getText().equals("+/-")){
                if(!testo1.getText().isEmpty())
                    testo1.setText(String.valueOf(-Float.parseFloat(testo1.getText())));
            }else if(b.getText().equals(".")){
                if(!testo1.getText().contains(".") && !testo1.getText().isEmpty()) {
                    testo1.setText(testo1.getText() + ".");
                }
            }
            fontTesto(testo1);
            fontTesto(testo2);
        }
    }

    private void fontTesto(JTextField tf){
        if(tf.getText().length()<7){
            tf.setFont(tf.getFont().deriveFont(25.0F));
        }
        if(tf.getText().length()>=9 && tf.getText().length()<=10){
            tf.setFont(tf.getFont().deriveFont(20.0F));
        }
        if(tf.getText().length()>=11){
            tf.setFont(tf.getFont().deriveFont(15.0F));
        }
    }

    private void calcolo() {
        if(!testo1.getText().isEmpty() && !testo2.getText().isEmpty()) {
            secondo_fatt = Float.parseFloat(testo1.getText());
            switch (s) {
                case "+" -> testo1.setText(String.valueOf(primo_fatt + secondo_fatt));
                case "-" -> testo1.setText(String.valueOf(primo_fatt - secondo_fatt));
                case "X" -> testo1.setText(String.valueOf(primo_fatt * secondo_fatt));
                case "÷" -> testo1.setText(String.valueOf(primo_fatt / secondo_fatt));
            }
            testo2.setText("");
            operazione = 0;
        }
    }
}
