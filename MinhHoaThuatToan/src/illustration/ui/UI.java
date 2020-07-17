package illustration.ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import illustration.althorithm.BubbleSortThread;
import illustration.althorithm.InsertionSort;
import illustration.althorithm.InterChangeSortThread;
import illustration.althorithm.MinSort;
import illustration.althorithm.SelectionSortThread;
import illustration.althorithm.ShakerSort;

public class UI extends JFrame{
	
	JButton button1,button2,buttonRun,button4,button3,button5,button6,button7,button8,button9,button10,buttonPause,buttonCreate;
	JLabel label2,label3;
	JTextArea area;
	JComboBox comboBox,optionalAlgorithm;
	JPanel panelMain,pnRight;
	JButton temp;
	static int swapButton =1;
	JButton []buttonArr = new JButton[10];
	int []buttonLocations = {50,100,150,200,250,300,350,400,450,500};
	Random random = new Random();
	public UI(String title) {
		this.setTitle(title);
		addControls();
		addEvents();
	}
	
	private void addEvents() {
		
		buttonRun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int speed = getSpeed(comboBox.getSelectedIndex());
				int i = optionalAlgorithm.getSelectedIndex();
				if (i==0) {
					
				}
				switch (i) {
				case 0:
					InterChangeSortThread interChangeSort = new InterChangeSortThread(buttonArr,buttonRun,buttonCreate,speed,area,label2,label3);
					interChangeSort.execute();
					break;
				case 1:
					
					BubbleSortThread bubbleSort = new BubbleSortThread(buttonArr, buttonRun, buttonCreate, speed,area,label2,label3);
					bubbleSort.execute();
					break;
				case 2: 
					MinSort minSort = new MinSort(buttonArr, buttonRun, buttonCreate, speed, label2, label3, area);
					minSort.execute();
					break;
				case 3 : 
					ShakerSort shakerSort = new ShakerSort(buttonArr, buttonRun, buttonCreate, speed, label2, label3, area);
					shakerSort.execute();
					break;
				case 4:
					InsertionSort insertSort = new InsertionSort(buttonArr, buttonRun, buttonCreate,speed, label2, label3, area);
					insertSort.execute();
					break;
				case 5 :
					SelectionSortThread selectionSortThread = new SelectionSortThread(buttonArr, buttonRun, buttonCreate, speed, label2, label3, area);
					selectionSortThread.execute();
				
					
				default:
					break;
				}
				 
				
				
				

				
				
				
			}
		});
		
		
		buttonCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				for (int i = 0; i < buttonArr.length; i++) {
					int randomIndex = random.nextInt(buttonArr.length);
					JButton button = buttonArr[i];
					buttonArr[i] = buttonArr[randomIndex];
					buttonArr[randomIndex] = button;
				}
				
				for (int i = 0; i < buttonLocations.length; i++) {
					buttonArr[i].setText(String.valueOf(random.nextInt(100)));
					buttonArr[i].setLocation(buttonLocations[i], 225);
					panelMain.add(buttonArr[i]);
				}
				
				
			}
		});
		
		optionalAlgorithm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = optionalAlgorithm.getSelectedIndex();
				switch (i) {
				case 0:
					area.setText("Ý tưởng : Dùng 2 vòng lặp để duyệt mảng và đưa các phần tử nhỏ nhất về đầu mảng bằng cách so sánh các cặp phần tử a[i][j]");
					label2.setText("+ Nếu a[i] > a[j] : thì đổi vị trí 2 phần tử ");
					label3.setText("+ Nếu a[i] < a[j] : thì không đổi vị trí");
					break;
				case 1 : 
					area.setText("Ý tưởng : Dùng 2 vòng lặp để duyệt mảng và đẩy các phần tử nhỏ nhất về đầu mảng bằng cách so sánh các cặp phần tử bắt đầu từ cuối mảng");
					label2.setText("+ Nếu a[j] < a[j-1] : thì đổi vị trí 2 phần tử ");
					label3.setText("+ Nếu a[j] > a[j-1] : thì không đổi vị trí");
					break;
				case 2 : 
					area.setText("Ý tưởng : Dùng 2 vòng lặp để duyệt mảng . Vòng lặp thứ nhất duyệt từ i=0 -> gần cuối mảng . Vòng lặp thứ 2 duyệt từ i+1 -> cuối mảng");
					label2.setText("B1: Tìm phần tử nhỏ nhất trong khoảng từ i+1 -> cuối mảng");
					label2.setSize(380, 100);
					label3.setText("B2: Đổi đổi vị trí cặp phần tử a[i] và a[min]");
					break;
				case 3 : 
					area.setText("Ý tưởng : Duyệt mảng từ 2 phía . Đẩy các phần tử lớn hơn trong 1 cặp các phần tử liền kề về phía bên phải , và đẩy các phần tử nhỏ hơn trong cặp các phần tử liền kề về phía bên trái");
					label2.setText("+ Lượt 1: Nếu a[i] > a[i+1] thì đổi vị trí 2 cặp phần tử, ngược lại thì không đổi");
					label2.setSize(450, 100);
					label3.setText("+ Lượt 2: Nếu a[j] < a[j-1] thì đổi vị trí 2 cặp phần tử, ngược lại thì không đổi");
					label3.setSize(450, 100);
					break;
				case 5 : 
					area.setText("Ý tưởng : Dùng 2 vòng lặp để duyệt mảng . Vòng lặp thứ nhất duyệt từ i=0 -> gần cuối mảng . Vòng lặp thứ 2 duyệt từ i+1 -> cuối mảng");
					label2.setText("+ Lượt 1: phần tử nhỏ nhất được đổi về phía đầu mảng");
					label2.setSize(450, 100);
					label3.setText("+ Lượt 2: phần tử lớn nhất được đổi về phía cuối mảng");
					label3.setSize(450, 100);
					break;
				
				default:
					break;
				}
				
				
				
			}
		});
		
	}
	
	
	
	public int getSpeed(int i ) {
		if(i == 0 || i == 1 ) {
			return 100;
		}else if(i == 2 ) {
			return 200;
		}else {
			return 50;
		}
	}
	
	public void addControls() {
		Container container = this.getContentPane();
		 panelMain =  new JPanel();
		 
		
		area = new JTextArea();
		
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		area.setBounds(50, 20, 500, 50);
		
		
		label2 = new JLabel();
		label2.setBounds(70, 60, 250, 100);
	
		
		
		label3 = new JLabel();
		label3.setBounds(70, 90, 250, 100);
		
		area.setText("Ý tưởng : Dùng 2 vòng lặp để duyệt mảng và đưa các phần tử nhỏ nhất về đầu mảng bằng cách so sánh các cặp phần tử a[i][j]");
		label2.setText("+ Nếu a[i] > a[j] : thì đổi vị trí 2 phần tử");
		label3.setText("+ Nếu a[i] < a[j] : thì không đổi vị trí");
		panelMain.add(area);
		panelMain.add(label2);
		panelMain.add(label3);
		
		button3 = new JButton();
			
		button3.setSize(50,50);
		
		button5 = new JButton();
		button5.setSize(50,50);
		
		button4 = new JButton();
		button4.setSize(50,50);				
		
		button1 = new JButton();
		button1.setSize(50,50);
		
		button2 = new JButton();
		button2.setSize(50,50);
		
		button6 = new JButton();
		button6.setSize(50,50);
		
		button7 = new JButton();
		button7.setSize(50,50);
		button8 = new JButton();
		button8.setSize(50,50);
		button9 = new JButton();
		button9.setSize(50,50);
		
		button10 = new JButton();
		button10.setSize(50,50);
		

		
		
		
		buttonArr[0] = button1;
		buttonArr[1] = button2;
		buttonArr[2] = button3;
		buttonArr[3] = button4;
		buttonArr[4] = button5;
		buttonArr[5] = button6;
		buttonArr[6] = button7;
		buttonArr[7] = button8;
		buttonArr[8] = button9;
		buttonArr[9] = button10;
		
		for (int i = 0; i < buttonArr.length; i++) {
			int randomIndex = random.nextInt(buttonArr.length);
			JButton button = buttonArr[i];
			buttonArr[i] = buttonArr[randomIndex];
			buttonArr[randomIndex] = button;
		}
		
		for (int i = 0; i < buttonLocations.length; i++) {
			buttonArr[i].setText(String.valueOf(random.nextInt(100)));
			buttonArr[i].setLocation(buttonLocations[i], 225);
			panelMain.add(buttonArr[i]);
		}
		
		
		
		pnRight = new JPanel();	
		pnRight.setBackground(Color.white);
		pnRight.setLayout(null);
		buttonRun = new JButton("Run");
		buttonRun.setBounds(90, 250, 100, 50);
		pnRight.add(buttonRun);
		

		
		Vector<String>vector = new Vector<String>();
		vector.add("Speed");
		vector.add("Bình thường");
		vector.add("Chậm");
		vector.add("Nhanh");
		
		comboBox = new JComboBox(vector);
		comboBox.setBounds(90, 200, 100, 30);
		pnRight.add(comboBox);
		
		
		Vector<String>vectorAlgorithm = new Vector<String>();
		vectorAlgorithm.add("Interchange");
		vectorAlgorithm.add("Bubble");
		vectorAlgorithm.add("Selection");
		vectorAlgorithm.add("Shaker");
		vectorAlgorithm.add("Insertion");
		vectorAlgorithm.add("Min Max sort");
		optionalAlgorithm = new JComboBox(vectorAlgorithm);
		optionalAlgorithm.setBounds(90, 150, 100, 30);
		pnRight.add(optionalAlgorithm);
		
		buttonCreate = new JButton("Create");
		buttonCreate.setBounds(90, 80, 100, 50);
		
		pnRight.add(buttonCreate);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panelMain, pnRight);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(600);
		splitPane.setEnabled(false);
		panelMain.setLayout(null);
		container.add(splitPane);
		
		
		
	}
	
	
	
	public void showWindow() {
		
		this.setSize(900, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	

}
