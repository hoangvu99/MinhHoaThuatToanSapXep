package illustration.althorithm;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class SelectionSortThread extends SwingWorker<String, Object>{
	
	JButton [] buttons;
	JButton btnRun,btnCreate;	 
	int speed;
	JLabel label2,label3;
	JTextArea area;
	
	
	
	public SelectionSortThread(JButton[] buttons, JButton btnRun, JButton btnCreate, int speed, JLabel label2,
			JLabel label3, JTextArea area) {
		super();
		this.buttons = buttons;
		this.btnRun = btnRun;
		this.btnCreate = btnCreate;
		this.speed = speed;
		this.label2 = label2;
		this.label3 = label3;
		this.area = area;
	}



	@Override
	protected String doInBackground() throws Exception {
		
		for (int i = 0; i < buttons.length/2+1; i++) {
			
			int min = Integer.valueOf(buttons[i].getText());			
			int minIndex = i;	
			int max = Integer.valueOf(buttons[i].getText());
			int maxIndex = i;
			Thread.sleep(100);
			
			for (int j = i+1; j < buttons.length-i; j++) {	
				
				
				if(min > Integer.valueOf(buttons[j].getText())) {					
					min = Integer.valueOf(buttons[j].getText());					
					minIndex = j ;									
				}	
				if(max < Integer.valueOf(buttons[j].getText())) {
					max = Integer.valueOf(buttons[j].getText());
					maxIndex = j;
				}
				
												
			}							
				int last = buttons.length-i-1;
				if(maxIndex == i || minIndex == last) {
					move(buttons[last], buttons[i]);
					JButton temp1 = buttons[i];
					buttons[i] = buttons[last];
					buttons[last] = temp1;
					
					
					if(maxIndex != i ) {
						move(buttons[maxIndex], buttons[last]);
						JButton temp2 = buttons[maxIndex];
						buttons[maxIndex] = buttons[last];
						buttons[last] = temp2;
						System.out.println("2");
						
						
					}
					if(minIndex != last) {
						move(buttons[minIndex], buttons[i]);
						JButton temp3 = buttons[minIndex];
						buttons[minIndex] = buttons[i];
						buttons[i] = temp3;
						System.out.println("3");
						
					}
					
				}else {
				
					move(buttons[i], buttons[minIndex]);
					JButton temp4 = buttons[minIndex];
					buttons[minIndex] = buttons[i];
					buttons[i] = temp4;
					Thread.sleep(100);
					move(buttons[maxIndex], buttons[last]);
					JButton temp5 = buttons[maxIndex];
					buttons[maxIndex] = buttons[last];
					buttons[last] = temp5;
				}
			
			}
			
			
	
		
		
		return null;
	}
	@Override
	protected void process(List<Object> chunks) {
		int x1 = (int) chunks.get(0);
		int y1 = (int) chunks.get(1);
		
		int x2 = (int) chunks.get(2);
		int y2 = (int) chunks.get(3);
		
		JButton buttonA = (JButton)chunks.get(4);
		JButton buttonB = (JButton)chunks.get(5);
		
		buttonA.setLocation(x1, y1);
		buttonB.setLocation(x2, y2);
		
		btnCreate.setEnabled(false);
		btnRun.setEnabled(false);
		
	}
	
	@Override
	protected void done() {
		btnCreate.setEnabled(true);
		btnRun.setEnabled(true);
	}
	
	public void move(JButton button1,JButton button2) throws InterruptedException {
		Point point1 = button1.getLocation();
		Point point2 = button2.getLocation();	
		
		int x1 = point1.x;
		int y1 = point1.y;				
		int x2 = point2.x;
		int y2 = point2.y;
		while(y1 > 165 && y2 < 285 ) {
			y1-=10;
			y2+=10;
			publish(x1,y1,x2,y2,button1,button2);
			Thread.sleep(speed);
		}
		Thread.sleep(100);
		while(x1 < point2.x && x2 > point1.x) {
			x1+=10;
			x2-=10;
			publish(x1,y1,x2,y2,button1,button2);
			Thread.sleep(speed);
		}
		Thread.sleep(100);			
		while(y1 < 225 && y2 > 225 ) {
			y1+=10;
			y2-=10;
			publish(x1,y1,x2,y2,button1,button2);
			Thread.sleep(speed);
		}
		Thread.sleep(100);
		
	}
	

}
