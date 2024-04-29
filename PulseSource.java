import java.awt.event.*;


public interface PulseSource
{
    final static byte BURST_MODE = 0;
    final static byte CONTINOUS_MODE = 1;

    void addActionListener(ActionListener pl);// upraszczamy (powinna by� nowa klasa PulseListener)
    void removeActionListener(ActionListener pl);// upraszczamy (powinna by� nowa klasa  PulseListener)

    void trigger();
    void setMode(byte mode);
    byte getMode() ;
    void halt() ;	//zatrzymaj generacj� 
    void setPulseDelay(int ms) ;
    int getPulseDelay() ;
    void setPulseCount(int burst) ;
}