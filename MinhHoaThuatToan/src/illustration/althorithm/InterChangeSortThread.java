package illustration.althorithm;

import java.awt.Color;
import java.awt.Point;
import java.time.temporal.JulianFields;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class InterChangeSortThread extends SwingWorker<String,Object>{
	 JButton [] buttons;
	 JButton btnRun,btnCreate;
	 int speed;
	
	 JLabel label2,label3;
	 JTextArea area;
	 public InterChangeSortThread(JButton []buttons,JButton buttonRun,JButton btnCreate,int speed,JTextArea area,JLabel label2,JLabel lable3) {
		 
		this.buttons = buttons;
		this.btnRun = buttonRun;
		this.btnCreate=btnCreate;
		this.area = area;
		this.label2=label2;
		this.label3 = lable3;
		this.speed = speed;
		
	}
	 
	

	
	
	@Override
		protected String doInBackground() throws Exception {	
			
			 for (int i = 0; i < buttons.length -1 ; i++) {
				 	
					for(int j = i+1;j < buttons.length;j++) {
						Point point1 = buttons[i].getLocation();
						Point point2 = buttons[j].getLocation();
						
						int x1 = point1.x;
						int y1 = point1.y;					
						int x2 = point2.x;
						int y2 = point2.y;
						buttons[i].setBackground(Color.cyan);
						buttons[j].setBackground(Color.cyan);
						
						while( y1 > 165 && y2 < 285) {
							y1-=10;
							y2+=10;
							publish(x1,y1,x2,y2,buttons[i],buttons[j]);
							Thread.sleep(speed);
						}
						Thread.sleep(200);
						if( Integer.valueOf(buttons[i].getText())> Integer.valueOf(buttons[j].getText()) ) {
								this.label2.setForeground(Color.red);
								Thread.sleep(100);
								while(x1 < point2.x && x2 > point1.x) {
									x1+=10;
									x2-=10;
									publish(x1,y1,x2,y2,buttons[i],buttons[j]);
									Thread.sleep(speed);
								}
								Thread.sleep(100);
								while( y1 < 225 && y2 > 225) {
									y1+=10;
									y2-=10;
									publish(x1,y1,x2,y2,buttons[i],buttons[j]);
									Thread.sleep(speed);
									
								}
								Thread.sleep(100);
								JButton temp = buttons[i];
								buttons[i] = buttons[j];
								buttons[j] = temp;
								
							}else {
								this.label3.setForeground(Color.red);
								while( y1 < 225 && y2 > 225) {
									y1+=10;
									y2-=10;
									publish(x1,y1,x2,y2,buttons[i],buttons[j]);
									Thread.sleep(speed);
									
								}
							}
							this.label2.setForeground(null);	
							this.label3.setForeground(null);
							buttons[i].setBackground(null);
							buttons[j].setBackground(null);
							Thread.sleep(100);
						}
					buttons[i].setEnabled(false);
						
					}								
				
		 
			String res = "true";			
			return res;
		}
		
		@Override
		protected void process(List arg) {
			
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
