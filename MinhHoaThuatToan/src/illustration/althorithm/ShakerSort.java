package illustration.althorithm;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class ShakerSort extends SwingWorker<String, Object>{
	
	JButton [] buttons;
	JButton btnRun,btnCreate;	 
	int speed;
	JLabel label2,label3;
	JTextArea area;
	
	
	@Override
	protected String doInBackground() throws Exception {
		
		boolean flag ;
		btnCreate.setEnabled(false);
		btnRun.setEnabled(false);
		do {
			flag = true;
			label2.setForeground(Color.red);
			for (int i = 0; i < buttons.length-1; i++) {
				
				buttons[i].setBackground(Color.cyan);
				buttons[i+1].setBackground(Color.cyan);
				Thread.sleep(200);
				if(Integer.valueOf(buttons[i].getText()) > Integer.valueOf(buttons[i+1].getText()) ) {					
					Point point1 = buttons[i].getLocation();
					Point point2 = buttons[i+1].getLocation();					
					int x1 = point1.x;
					int y1 = point1.y;					
					int x2 = point2.x;
					int y2 = point2.y;					
					while( y1 > 165 && y2 < 285 ) {
						y1-=10;
						y2+=10;
						publish(x1,y1,x2,y2,buttons[i],buttons[i+1]);
						Thread.sleep(speed);
					}
					Thread.sleep(100);
					while(x1 < point2.x && x2 > point1.x) {
						x1+=10;
						x2-=10;
						publish(x1,y1,x2,y2,buttons[i],buttons[i+1]);
						Thread.sleep(speed);
					}
					Thread.sleep(100);					
					while(y1 < 225 && y2 > 225) {
						y1+=10;
						y2-=10;
						publish(x1,y1,x2,y2,buttons[i],buttons[i+1]);
						Thread.sleep(speed);
					}					
					Thread.sleep(100);
					
					JButton temp = buttons[i];
					buttons[i] = buttons[i+1];
					buttons[i+1] = temp;
					flag= false;				
				}else {
					buttons[i].setBackground(null);
					buttons[i+1].setBackground(null);
				}
				
				buttons[i].setBackground(null);
				buttons[i+1].setBackground(null);
				Thread.sleep(100);
			}		
			label2.setForeground(null);
			Thread.sleep(100);
			label3.setForeground(Color.red);
			for (int i = buttons.length-1 ; i > 0; i--) {			
				buttons[i].setBackground(Color.cyan);
				buttons[i-1].setBackground(Color.cyan);
				Thread.sleep(200);
				if(Integer.valueOf(buttons[i].getText()) < Integer.valueOf(buttons[i-1].getText()) ) {					
					Point point1 = buttons[i-1].getLocation();
					Point point2 = buttons[i].getLocation();					
					int x1 = point1.x;
					int y1 = point1.y;					
					int x2 = point2.x;
					int y2 = point2.y;					
					while( y1 <285  && y2 > 165 ) {
						y1+=10;
						y2-=10;
						publish(x1,y1,x2,y2,buttons[i-1],buttons[i]);
						Thread.sleep(speed);
					}
					Thread.sleep(100);
					while(x1 < point2.x && x2 > point1.x) {
						x1+=10;
						x2-=10;
						publish(x1,y1,x2,y2,buttons[i-1],buttons[i]);
						Thread.sleep(speed);
					}
					Thread.sleep(100);
					
					while(y1 > 225 && y2 < 225) {
						y1-=10;
						y2+=10;
						publish(x1,y1,x2,y2,buttons[i-1],buttons[i]);
						Thread.sleep(speed);
					}
					
					Thread.sleep(100);
					
					JButton temp = buttons[i];
					buttons[i] = buttons[i-1];
					buttons[i-1] = temp;
					flag= false;
					
				}else {
					buttons[i].setBackground(null);
					buttons[i-1].setBackground(null);
					Thread.sleep(100);
				}
				buttons[i].setBackground(null);
				buttons[i-1].setBackground(null);
				Thread.sleep(100);
				
			}
			label3.setForeground(null);
			Thread.sleep(100);
			
		} while (!flag);
		
		
		
		return null;
	}

	@Override
	protected void process(List<Object> chunks) {
		int x1 = (int) chunks.get(0);
		int y1 = (int) chunks.get(1);
		
		int x2 = (int) chunks.get(2);
		int y2 = (int) chunks.get(3);
		
		JButton button1 = (JButton) chunks.get(4);
		JButton button2 = (JButton) chunks.get(5);
		
		button1.setLocation(x1, y1);
		button2.setLocation(x2, y2);
		
		
	}
	@Override
	protected void done() {
		btnCreate.setEnabled(true);
		btnRun.setEnabled(true);
	}
	public ShakerSort(JButton[] buttons, JButton btnRun, JButton btnCreate, int speed, JLabel label2, JLabel label3,
			JTextArea area) {
		super();
		this.buttons = buttons;
		this.btnRun = btnRun;
		this.btnCreate = btnCreate;
		this.speed = speed;
		this.label2 = label2;
		this.label3 = label3;
		this.area = area;
	}
	
	
}
