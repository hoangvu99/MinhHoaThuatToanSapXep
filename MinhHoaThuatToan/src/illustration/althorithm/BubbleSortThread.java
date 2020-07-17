package illustration.althorithm;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class BubbleSortThread extends SwingWorker<String, Object>{
	
	JButton [] buttons;
	JButton btnRun,btnCreate;	 
	int speed;
	JLabel label2,label3;
	JTextArea area;
	
	
	public BubbleSortThread() {
		super();
	}
	public BubbleSortThread(JButton[] buttons, JButton btnRun, JButton btnCreate, int speed,JTextArea area,JLabel label2,JLabel label3) {
		super();
		this.buttons = buttons;
		this.btnRun = btnRun;
		this.btnCreate = btnCreate;
		this.speed = speed;
		this.area = area;
		this.label2=label2;
		this.label3=label3;
	}
	@Override
	protected String doInBackground() throws Exception {
		
		for (int i = 0; i < buttons.length-1; i++) {
			for (int j = buttons.length-1; j > i; j--) {
				Point point1 = buttons[j-1].getLocation();
				Point point2 = buttons[j].getLocation();
				
				int x1 = point1.x;
				int y1 = point1.y;					
				int x2 = point2.x;
				int y2 = point2.y;
				buttons[j].setBackground(Color.cyan);
				buttons[j-1].setBackground(Color.cyan);			
				while( y1 <285  && y2 > 165) {
					y1+=10;
					y2-=10;
					publish(x1,y1,x2,y2,buttons[j-1],buttons[j]);
					Thread.sleep(speed);
				}
				Thread.sleep(200);
				if(Integer.valueOf(buttons[j].getText()) < Integer.valueOf(buttons[j-1].getText()) ) {
					this.label2.setForeground(Color.red);
					Thread.sleep(200);
					while(x1 < point2.x && x2 > point1.x) {
						x1 +=10;
						x2 -=10;
						publish(x1,y1,x2,y2,buttons[j-1],buttons[j]);
						Thread.sleep(speed);
					}
					Thread.sleep(100);
					while (y1 > 225 && y2 < 225) {
						y1-=10;
						y2+=10;
						publish(x1,y1,x2,y2,buttons[j-1],buttons[j]);
						Thread.sleep(speed);						
						
					}
					Thread.sleep(100);
					JButton temp = buttons[j];
					buttons[j] = buttons[j-1];
					buttons[j-1] = temp;		
				
					
				}else {
					this.label3.setForeground(Color.red);
					Thread.sleep(100);
					while (y1 > 225 && y2 < 225) {
						y1-=10;
						y2+=10;
						publish(x1,y1,x2,y2,buttons[j-1],buttons[j]);
						Thread.sleep(speed);						
						
					}				
				}
				this.label2.setForeground(null);	
				this.label3.setForeground(null);
				buttons[j-1].setBackground(null);
				buttons[j].setBackground(null);
				Thread.sleep(100);				
			}
			buttons[i].setEnabled(false);
		}
		return null;
	}
	@Override
	protected void process(List<Object> arg) {
		int x1 = (int) arg.get(0);
		int y1 = (int) arg.get(1);
		int x2 = (int) arg.get(2);
		int y2 = (int) arg.get(3);
		JButton button1 =  (JButton) arg.get(4);
		JButton button2 =  (JButton) arg.get(5);				
		button1.setLocation(x1, y1);
		button2.setLocation(x2, y2);
		btnRun.setEnabled(false);
		btnCreate.setEnabled(false);
	}
	@Override
	protected void done() {
		btnRun.setEnabled(true);
		btnCreate.setEnabled(true);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(true);
		}
	}
	
}
