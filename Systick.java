import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Systick {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Systick cortexM01 = new Systick();
//        cortexM01.setSourceInternal(); 
//        cortexM01.setRVR(1);
//        cortexM01.setCVR(4);
//        cortexM01.setEnable();
//        cortexM01.tickInternal();
//        System.out.println("CVR is 1?: " + cortexM01.getCVR());
//        cortexM01.setRVR(0);
//        cortexM01.tickInternal();
//        System.out.println("CVR is 0?: " + cortexM01.getCVR());
//        System.out.println("RVR is 0?: " + cortexM01.getRVR());
//        cortexM01.tickInternal();
//        System.out.println("CVR is 0?: " + cortexM01.getCVR());
//        System.out.println("RVR is 0?: " + cortexM01.getRVR());
//        cortexM01.setRVR(2);
//        cortexM01.tickInternal();
//        System.out.println("CVR is 0?: " + cortexM01.getCVR());
//        System.out.println("RVR is 2?: " + cortexM01.getRVR());
//        cortexM01.setEnable();
//        cortexM01.tickInternal();
//        System.out.println("CVR is 2?: " + cortexM01.getCVR());
//        cortexM01.tickInternal();
//        System.out.println("CVR is 1?: " + cortexM01.getCVR());
//        cortexM01.tickInternal();
//        System.out.println("CVR is 0?: " + cortexM01.getCVR());
		
		//Systick s = new Systick();
		//System.out.println("CSR is?: " + s.getCSR());
        //s.setSourceInternal(); 
        //System.out.println("CSR is?: " + s.getCSR());
       // s.setCVR(4);
//
//
   		//s.setRVR(4);
      // s.setEnable();
       // System.out.println("CSR is?: " + s.getCSR());
      // s.tickInternal();
      // System.out.println("RVR is 4?: " + s.getRVR());
       // for (int i=0; i<5; i++) {
        //    s.tickInternal();
        //   System.out.println("CVR is: " + s.getCVR());
        //  System.out.println("CountFlag is: " + s.isCountFlag());
//}
//        System.out.println("RVR is 4?: " + s.getRVR());
//
//        System.out.println("CountFlag is true?: " + s.isCountFlag());
//        s.getCSR();
//        System.out.println("CountFlag is flase?: " + s.isCountFlag());
		
//		s.setRVR(9);
//		s.setCVR(10);
//		s.setCSR(7);
//		System.out.println("CountFlag: " + s.getCountFlag());
//		
//		for(int i = 0; i<=10; i++) {
//			s.tickInternal();
//			System.out.println("CountFlag: " + s.getCountFlag());
//			System.out.println("CVR: " + s.getCVR());
//		}
		
//		System.out.println("\n");
//		System.out.println("RVR: " + s.getRVR());
//		System.out.println("CVR: " + s.getCVR());
//		System.out.println("CSR: " + s.getCSR());
//		System.out.println("\n");
//			
//		System.out.println("CountFlag: " + s.isCountFlag());

	}

	private int RVR, CVR, CSR;
	ActionListener al;
	
	public Systick(){		
		RVR = 0x000000;
		CVR = 0x000000;
		CSR = 0x000000;
		
	}
	

	public void tickInternal() {
			
		if((CSR & (1 << 2)) == 0) {			
			if((CSR & (1 << 0)) != 0) {		
				if(CVR != 0) {
					CVR = CVR - 1;
					if(CVR == 0) {
						CSR = CSR | (1 << 16);
					}
				}
				else {
					CVR = RVR;
				}
			}
		}
		if((CSR & (1 << 1))!= 0 & (CSR & (1 << 16))!= 0) {
			System.out.println("Interrupt!\n");		
		}
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Włączam silnik"));
	}

	public void tickExternal() {
		
		if((CSR & (1 << 2)) == 1) {			
			if((CSR & (1 << 0)) != 0) {		
				if(CVR != 0) {
					CVR = CVR - 1;
					if(CVR == 0) {
						CSR = CSR | (1 << 16);
					}
				}
				else {
					CVR = RVR;
					
				}
			}
		}
		if((CSR & (1 << 1))!= 0 & (CSR & (1 << 16))!= 0) {
			System.out.println("Interrupt!\n");
		}
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Włączam silnik"));
	}


	public void setRVR(int value) {
		
		
		if(value == 0) {
			CSR = CSR & ~(1 << 0);
			RVR = 0x000000;
			CVR = 0x000000;
		}
		RVR = value & 0xFFFFFF;
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"W��czam silnik"));
	}

	public void setCVR(int value) {
		
		
		CVR = 0x000000;;
		CSR = CSR & ~(1 << 16);
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"W��czam silnik"));
	}

	public void setCSR(int value) {
		CSR = value & 0xFFFFFF;
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"W��czam silnik"));
	}


	public void reset() {
		CSR = 0x000000;
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"W��czam silnik"));
	}

	public void setEnable() {		
		CSR = CSR | (1 << 0);
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"W��czam silnik"));
	}

	public void setDisable() {
		CSR = CSR & ~(1 << 0);
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"W��czam silnik"));
	}

	public void setSourceExternal() {
		CSR = CSR | (1 << 2);
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"W��czam silnik"));
	}

	public void setSourceInternal() {
		CSR = CSR & ~(1 << 2);
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"W��czam silnik"));
	}

	public void setInterruptEnable() {
		CSR = CSR | (1 << 1);
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"W��czam silnik"));
	}

	public void setInterruptDisable() {
		CSR = CSR & ~(1 << 1);
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"W��czam silnik"));
	}
	

	public int getCVR() {
		return CVR;
	}

	public int getRVR() {
		return RVR;
	}

	public int getCSR() {
		int temp = CSR;
		CSR = CSR & ~(1 << 16);
		return temp;
	}


	public boolean getEnabled() {
		CSR = CSR & ~(1 << 16);
		
		int a = (CSR & (1 << 0));
	    if (a <= 0) return false;
	    return true;
	}
	
	public boolean getInterrupt() {
		CSR = CSR & ~(1 << 16);
		
		int a = (CSR & (1 << 1));
	    if (a <= 0) return false;
	    return true;
	}

	public boolean getSource() {
		CSR = CSR & ~(1 << 16);
		
		int a = (CSR & (1 << 2));
	    if (a <= 0) return false;
	    return true;
	}

	public boolean getCountFlag() {
		int a = (CSR & (1 << 16));
		CSR = CSR & ~(1 << 16);
		
	    if (a <= 0) return false;
	    return true;
	}


	public boolean isCountFlag() {
		int a = (CSR & (1 << 16));
	    if (a <= 0) return false;
	    return true;
	}

	public boolean isEnableFlag() {
		int a = (CSR & (1 << 0));
	    if (a <= 0) return false;
	    return true;
	}

	public boolean isInterruptFlag() {
		int a = (CSR & (1 << 1));
	    if (a <= 0) return false;
	    return true;
	}

	public boolean isSourceFlag() {
		int a = (CSR & (1 << 2));
	    if (a <= 0) return false;
	    return true;
	}
	
	public void addActionListener(ActionListener l) {
		al=AWTEventMulticaster.add(al, l);
	}
	public void removActionListener(ActionListener l) {
		al=AWTEventMulticaster.remove(al, l);
	}
	
	
}

