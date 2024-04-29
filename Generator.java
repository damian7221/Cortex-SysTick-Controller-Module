import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Generator extends Thread implements PulseSource {

    private ActionListener actionListener;
    private int tickCount;
    private int pulseDelay;
    private volatile boolean running = true; // Dodana flaga do kontrolowania pętli
    private Systick systick; // Referencja do obiektu Systick
    

    public Generator(Systick systick) {
        this.systick = systick;
        tickCount = 0;
        pulseDelay = 1000;
    }

    @Override
    public void addActionListener(ActionListener pl) {
        actionListener = pl;
    }

    @Override
    public void removeActionListener(ActionListener pl) {
        actionListener = null;
    }

    @Override
    public void trigger() {
        tickCount++;
        if (actionListener != null) {
            actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Generate"));
        }
    }

    @Override
    public void setMode(byte mode) {
    	
    }

    @Override
    public byte getMode() {
        return 0;
    }

    @Override
    public void halt() {
        running = false; // Zmienia flagę na false, co spowoduje zatrzymanie pętli w metodzie run
    }

    @Override
    public void setPulseDelay(int ms) {
       pulseDelay = ms;
    }

    @Override
    public int getPulseDelay() {
        return pulseDelay;
    }

    public int getTickCount() {
        return tickCount;
    }

    @Override
    public void setPulseCount(int burst) {

    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(pulseDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            trigger();
            systick.tickInternal(); // Dodanie wywołania metody tickInternal
        }
    }

    public static void main(String[] args) {
    	Systick systick = new Systick();
        Generator generator = new Generator(systick);

        generator.start();
    }
}
