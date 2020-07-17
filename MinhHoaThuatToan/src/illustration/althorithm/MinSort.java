package illustration.althorithm;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class MinSort extends SwingWorker<String, Object>{
	JButton [] buttons;
	JButton btnRun,btnCreate;	 
	int speed;
	JLabel label2,label3;
	JTextArea area;
	public MinSort(JButton[] buttons, JButton btnRun, JButton btnCreate, int speed, JLabel label2, JLabel label3,
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
	@Override
	protected String doInBackground() throws Exception {
		for (int i = 0; i < buttons.length-1; i++) {
			int minIndex = i;
			int min = Integer.valueOf(buttons[i].getText());
			
			for (int j = i+1; j < buttons.length; j++) {
				if(min > Integer.valueOf(buttons[j].getText())) {
					min = Integer.valueOf(buttons[j].getText());
					
					minIndex = j;
				}
			}
			if(minIndex != i) {
				Point point1 = buttons[i].getLocation();
				Point point2 = buttons[minIndex].getLocation();
				
				int x1 = point1.x;
				int y1 = point1.y;
				int x2 = point2.x;
				int y2 = point2.y;
				buttons[minIndex].setBackground(Color.CYAN);
				while(y1 > 165 && y2 < 285) {
					y1-=10;
					y2+=10;
					publish(x1,y1,x2,y2,buttons[i],buttons[minIndex]);
					Thread.sleep(speed);
					
				}
				Thread.sleep(100);
				while(x1< point2.x && x2 > point1.x) {
					x1+=10;
					x2-=10;
					publish(x1,y1,x2,y2,buttons[i],buttons[minIndex]);
					Thread.sleep(speed);
				}
				Thread.sleep(100);
				while(y1 < 225 && y2 > 225) {
					y1+=10;
					y2-=10;
					publish(x1,y1,x2,y2,buttons[i],buttons[minIndex]);
					Thread.sleep(speed);
				}
				buttons[minIndex].setBackground(null);
				JButton temp = buttons[i];
				buttons[i] = buttons[minIndex];
				buttons[minIndex] = temp;
			}
			buttons[i].setEnabled(false);
		}
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
		btnRun.setEnabled(false);
		btnCreate.setEnabled(false);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(true);
		}
	}
	
	
}
