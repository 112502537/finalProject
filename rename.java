package final_project;

import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

public class rename extends JFrame implements ActionListener{

	TextField tf = new TextField();
	JButton b = new JButton("Enter");
	String name;
	
	public rename() {
		this.setLayout(null);
		this.setSize(240,100);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screenSize.getWidth()/2 - this.getWidth()/2), (int)(screenSize.getHeight()/2 - this.getHeight()/2));

		tf.setBounds(20, 20, 100, 30);
		b.setBounds(140, 20, 80, 30);
		b.addActionListener(this);
		this.add(tf);
		this.add(b);
		
		this.setVisible(true);
	}

	public String getName() {
		return name;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == b) {//按下按鈕後更改名字
			name = tf.getText();
			Timer timer = new Timer(1000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			timer.start();
		}
	}
	
	

}
