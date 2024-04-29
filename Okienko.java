import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Okienko extends JFrame {

    private JPanel contentPane;
    private JTextField textCVR;
    private JTextField textCSR;
    private JTextField textRVR;
    private JTextField textGeneratorOutput;
    private JTextField valueCircle;
    private JTextField textPulseDelay;
    private JTextField textMaxValue;
    private Generator gen;

    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Okienko frame = new Okienko();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    
    
    public Okienko() {
        Systick s = new Systick();
        gen = new Generator(s); // Inicjalizacja generatora
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 6, 0, 0));
        
        
        
        ////	PIERWSZY PANEL APLIKACJI OKIENKOWEJ
        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2);
        panel_2.setLayout(new GridLayout(0, 1, 0, 0));
        
        JButton btnSetcsr = new JButton("SetCSR");
        panel_2.add(btnSetcsr);
        
        JButton btnSetrvr = new JButton("SetRVR");
        panel_2.add(btnSetrvr);
        
        JButton btnSetcvr = new JButton("SetCVR");
        panel_2.add(btnSetcvr);
        //////
        
        
        ////	DRUGI PANEL APLIKACJI OKIENKOWEJ
        JPanel panel_3 = new JPanel();
        contentPane.add(panel_3);
        panel_3.setLayout(new GridLayout(0, 1, 0, 0));
        
        JSpinner spinnerCSR = new JSpinner();
        panel_3.add(spinnerCSR);
        
        JSpinner spinnerRVR = new JSpinner();
        panel_3.add(spinnerRVR);
        
        JSpinner spinnerCVR = new JSpinner();
        panel_3.add(spinnerCVR);
        ////
        
        
        ////	TRZECI PANEL APLIKACJI OKIENKOWEJ
        JPanel panel = new JPanel();
        contentPane.add(panel);
        panel.setLayout(new GridLayout(0, 1, 0, 0));
        
        textCSR = new JTextField();
        panel.add(textCSR);
        textCSR.setColumns(10);
        
        textRVR = new JTextField();
        panel.add(textRVR);
        textRVR.setColumns(10);
        
        textCVR = new JTextField();
        panel.add(textCVR);
        textCVR.setColumns(10);
        ////
        
        
        ////	CZWARTY PANEL APLIKACJI OKIENKOWEJ
        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1);
        panel_1.setLayout(new GridLayout(0, 1, 0, 0));        
        
        textPulseDelay = new JTextField();
        panel_1.add(textPulseDelay);

        JButton btnRunGenerator = new JButton("Generator");
        panel_1.add(btnRunGenerator);
        
        textGeneratorOutput = new JTextField();
        textGeneratorOutput.setColumns(10);
        panel_1.add(textGeneratorOutput);
        
        JButton btnStopGenerator = new JButton("Stop Generator");
        panel_1.add(btnStopGenerator);
        ////
        
        
        ////	PIĄTY PANEL APLIKACJI OKIENKOWEJ
        JPanel panel_5 = new JPanel();
        contentPane.add(panel_5);
        panel_5.setLayout(new GridLayout(0, 1, 0, 0));
        
        textMaxValue = new JTextField("Wpisz wartość pokrętła"); // Ustawienie tekstu zastępczego
        textMaxValue.setForeground(Color.GRAY); // Ustawienie szarego koloru dla tekstu zastępczego
        textMaxValue.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textMaxValue.getText().equals("Wpisz wartość pokrętła")) {
                    textMaxValue.setText("");
                    textMaxValue.setForeground(Color.BLACK); // Zmiana koloru tekstu na standardowy
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textMaxValue.getText().isEmpty()) {
                    textMaxValue.setForeground(Color.GRAY);
                    textMaxValue.setText("Wpisz wartość pokrętła");
                }
            }
        });
        panel_5.add(textMaxValue);
        textMaxValue.setColumns(10);
       
        ObracajaceKolo obracajaceKoloPanel = new ObracajaceKolo(textPulseDelay);
        panel_5.add(obracajaceKoloPanel);
        
        ////
        
        
        ////	SZÓSTY PANEL APLIKACJI OKIENKOWEJ		(Informację o autorze itp.)
        JPanel panel_6 = new JPanel();
        panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS)); // Ustawienie BoxLayout
        contentPane.add(panel_6);

        JLabel labelSysTick = new JLabel("SysTick");
        JLabel labelAuthor = new JLabel("Autor: Damian Łojko");
        JLabel labelIndexNumber = new JLabel("Numer indeksu: 264376");

        panel_6.add(labelSysTick);
        panel_6.add(Box.createVerticalStrut(5));
        panel_6.add(labelAuthor);
        panel_6.add(Box.createVerticalStrut(5));
        panel_6.add(labelIndexNumber);
        
        ////
        
        
        ////	KOMUNIKAT DLA POLA TEKSTOWEGO MAX WARTOŚCI KNOBA
        textMaxValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textMaxValue.getText().trim();
                if (!text.isEmpty()) {
                    try {
                        int maxValue = Integer.parseInt(text);
                        obracajaceKoloPanel.setMaxValue(maxValue);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Nieprawidłowa wartość maksymalna", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    obracajaceKoloPanel.setMaxValue(null);
                }
            }
        });
        ////

        
        
        //// USTAWANIE WARTOŚCI RVR, CSR I CVR
        btnSetrvr.addActionListener(e -> {
            s.setRVR((Integer) spinnerRVR.getValue());
        });
        
        btnSetcsr.addActionListener(e -> {
            s.setCSR((Integer) spinnerCSR.getValue());
        });
        
        btnSetcvr.addActionListener(e -> {
            s.setCVR((Integer) spinnerCVR.getValue());
        });
        ////
        
        
        
        //// ZATRZYMANIE GENERATORA
        btnStopGenerator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gen != null && gen.isAlive()) {
                    gen.halt(); // Zatrzymanie generatora
                    JOptionPane.showMessageDialog(null, "Generator został zatrzymany", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Generator nie jest uruchomiony", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
       ////
        
        
   //// URUCHOMENIE GENERATORA
        btnRunGenerator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pulseDelayValue = 1000; // Domyślna wartość pulseDelay

                if (!textPulseDelay.getText().trim().isEmpty()) {
                    try {
                        pulseDelayValue = Integer.parseInt(textPulseDelay.getText().trim());
                    } catch (NumberFormatException ex) {
                        pulseDelayValue = 1000;
                    }
                }

                if (gen != null && gen.isAlive()) {
                    JOptionPane.showMessageDialog(null, "Generator już działa", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    gen = new Generator(s);
                    gen.setPulseDelay(pulseDelayValue);
                    gen.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            textGeneratorOutput.setText("Generator: " + gen.getTickCount());
                        }
                    });
                    gen.start();
                }
            }
        });

        

        ////	WYŚWIETLANIE WARTOŚCI GENERATORA
        gen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textGeneratorOutput.setText("Generator: " + gen.getTickCount());
            }
        });
        ////

        
        //// WYŚWIETLENIE WARTOŚCI RVR, CSR I CVR
        s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textRVR.setText("" + s.getRVR());
                textCSR.setText("" + s.getCSR());
                textCVR.setText("" + s.getCVR());
            }
        });
        ////
    }

    public void setGeneratorPulseDelay(int pulseDelay) {
        if (gen != null) {
            gen.setPulseDelay(pulseDelay);
        }
    }
}
