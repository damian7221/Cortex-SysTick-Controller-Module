import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ObracajaceKolo extends JPanel {
    private CustomPanel panel;
    private JTextField textPulseDelay;
    private Integer maxValue = null;
    private boolean isMaxValueSet = false;

    public ObracajaceKolo(JTextField textPulseDelay) {
        this.textPulseDelay = textPulseDelay; // Przypisanie referencji do pola tekstowego
        panel = new CustomPanel();

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    public int getAngle() {
        return panel.getAngle();
    }
    
    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
        isMaxValueSet = (maxValue != null);
    }

    private class CustomPanel extends JPanel {
        private int angle = 0;
        private int scaledValue = 0; // Zakres od 0 do 10000

        public CustomPanel() {
            setPreferredSize(new Dimension(300, 300));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    updateAngle(e.getX(), e.getY());
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    updateAngle(e.getX(), e.getY());
                }
            });
        }

        private void updateAngle(int x, int y) {
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int deltaX = x - centerX;
            int deltaY = y - centerY;
            
            if (!isMaxValueSet) {
                JOptionPane.showMessageDialog(null, "Podaj maksymalną wartość pokrętła!", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (Math.sqrt(deltaX * deltaX + deltaY * deltaY) <= 50) { // Sprawdzenie czy kursor jest wewnątrz koła
                angle = (int) Math.toDegrees(Math.atan2(deltaY, deltaX));
                angle = (angle + 360) % 360;

                // Skalowanie wartości kąta do zakresu 0-10000
                scaledValue = scaleValue(angle, 0, 360, 0, maxValue);
                
                // Aktualizacja pulsedelay w generatorze
                Component parent = SwingUtilities.getWindowAncestor(this);
                if (parent instanceof Okienko) {
                    int scaledPulseDelay = scaleValue(angle, 0, 360, 0, maxValue);
                    ((Okienko) parent).setGeneratorPulseDelay(scaledPulseDelay);

                    // Aktualizacja pola tekstowego textPulseDelay
                    textPulseDelay.setText(String.valueOf(scaledPulseDelay));
                }

                repaint();
            }
        }

        private int scaleValue(int valueIn, int baseMin, int baseMax, int limitMin, int limitMax) {
            // Skalowanie wartości z jednego zakresu do innego
            return (int) (((double) (valueIn - baseMin) / (baseMax - baseMin)) * (limitMax - limitMin) + limitMin);
        }

        public int getAngle() {
            return scaledValue; // Zwrócenie przeskalowanej wartości
        }
        
        

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = 50;

            // Rysowanie mniejszego koła
            int x = (int) (centerX + radius * Math.cos(Math.toRadians(angle)));
            int y = (int) (centerY + radius * Math.sin(Math.toRadians(angle)));
            g.setColor(Color.BLACK);
            g.fillOval(x - 20, y - 20, 40, 40);

            // Rysowanie większego koła
            g.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
        }
        
        
    }
}
