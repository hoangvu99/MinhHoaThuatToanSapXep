package illustration.althorithm;

import java.awt.Point;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class InsertionSort extends SwingWorker<String, Object>{
	JButton [] buttons;
	JButton btnRun,btnCreate;	 
	int speed;
	JLabel label2,label3;
	JTextArea area;
	public InsertionSort(JButton[] buttons, JButton btnRun, JButton btnCreate, int speed, JLabel label2, JLabel label3,
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
		for (int i = 1; i < buttons.length; i++) {						
			Point point1 = buttons[i].getLocation();			
			int x1 = point1.x;
			int y1 = point1.y;
			while(y1 >165) {
				y1-=10;
				publish(x1,y1,buttons[i]);
				Thread.sleep(speed);
			}
			Thread.sleep(100);			
			
			
			int x = Integer.valueOf(buttons[i].getText());
			int j = i-1;
			
			while(x < Integer.valueOf(buttons[j].getText())) {
				buttons[j+1] = buttons[j];
				j--;
			}
			
			buttons[j+1]=buttons[i];
			
			
		}				
		return null;
	}
	
	@Override
	protected void process(List<Object> chunks) {
		int x = (int) chunks.get(0);
		int y = (int) chunks.get(1);
		
		JButton button = (JButton)chunks.get(2);
		button.setLocation(x, y);
		super.process(chunks);
	}
	
}
