package final_project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class passChoose extends JFrame{

	public passChoose(int c) {
		this.setLayout(null);
		this.setSize(500,400);//視窗大小	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screenSize.getWidth()/2 - this.getWidth()/2), (int)(screenSize.getHeight()/2 - this.getHeight()/2));
		this.setUndecorated(true);
		
		Font font = new Font("芫荽", Font.BOLD, 100);
		JLabel cost = new JLabel("- " + Integer.toString(c) + " !");//扣多少經驗值
		cost.setFont(font);
		cost.setForeground(Color.yellow);
		this.setBackground(Color.white);
		cost.setBackground(Color.black);
		cost.setBounds(0, 0, 500, 400);
		cost.setHorizontalAlignment(SwingConstants.CENTER);
		cost.setOpaque(true);
		this.add(cost);
		this.setVisible(true);
		
		//延遲一秒關閉
		Timer timer = new Timer(1500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		timer.start();
	}

	public passChoose(String c) {//遊戲勝利畫面
		this.setLayout(null);
		this.setSize(500,400);//視窗大小	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screenSize.getWidth()/2 - this.getWidth()/2), (int)(screenSize.getHeight()/2 - this.getHeight()/2));
		this.setUndecorated(true);
		
		Font font = new Font("芫荽", Font.BOLD, 100);
		JLabel cost = new JLabel(c + "!");
		cost.setFont(font);
		cost.setForeground(Color.yellow);
		this.setBackground(Color.white);
		cost.setBackground(Color.black);
		cost.setBounds(0, 0, 500, 400);
		cost.setHorizontalAlignment(SwingConstants.CENTER);
		cost.setOpaque(true);
		this.add(cost);
		this.setVisible(true);
		
		//延遲一秒關閉
		Timer timer = new Timer(1500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		timer.start();
	}
}
