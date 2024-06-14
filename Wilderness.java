package final_project;
import javax.swing.*;
import javax.swing.border.Border;

import final_project.WChoose.ImagePanel;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Wilderness extends JFrame implements ActionListener
{
	JLabel question = new JLabel(); // 題目
	JRadioButton a = new JRadioButton("A");//選項按鈕
	JRadioButton b = new JRadioButton("B");
	JRadioButton c = new JRadioButton("C");
	JRadioButton d = new JRadioButton("D");
	ImageIcon icon = new ImageIcon("wild.jpg");//背景圖
	int rd = 0; // 隨機題目
	Boolean correct = false; // 回答正確與否
	int qq = 0; // 第幾題
	int score = 0; //本次積分
	int maxScr = 2; //初始值
	Dimension frameSize = new Dimension(1000, 800);
	boolean s = false;//成功與否
	
	ArrayList<String[]> historyQ = new ArrayList<String[]>();
	File history = new File("history.TXT");//打開題目庫
	ArrayList<String[]> englishQ = new ArrayList<String[]>();
	File english = new File("english.TXT");//打開題目庫
	ArrayList<String[]> swordQ = new ArrayList<String[]>();
	File sword = new File("sword.TXT");//打開題目庫
	ArrayList<String[]> geographyQ = new ArrayList<String[]>();
	File geography = new File("geography.TXT");//打開題目庫
	ArrayList<String[]> bigQ = new ArrayList<String[]>();
	File big = new File("big.TXT");//打開題目庫
	
	
	public Wilderness() {
		this.setSize(1000,800);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		
		readFile(history, historyQ);//讀取問題和答案
		readFile(english, englishQ);
		readFile(sword, swordQ);
		readFile(geography, geographyQ);
		readFile(big, bigQ);
	}

	public void actionPerformed(ActionEvent e) {
		qq++;
		if(e.getSource() == a) {
			a.setForeground(Color.red);//如果被點擊則變換文字顏色
			if(rd/3 == 0) correct = true;//判斷問題答案
			else correct = false;//確認是否答對
		}
		else a.setForeground(Color.black);//點選其他按鈕，文字變為黑色
		if(e.getSource() == b) {
			b.setForeground(Color.red);
			if(rd/3 == 1) correct = true;
			else correct = false;
		}
		else b.setForeground(Color.black);
		if(e.getSource() == c) {
			c.setForeground(Color.red);
			if(rd/3 == 2) correct = true;
			else correct = false;
		}
		else c.setForeground(Color.black);
		if(e.getSource() == d) {
			d.setForeground(Color.red);
			if(rd/3 == 3) correct = true;
			else correct = false;
		}
		else d.setForeground(Color.black);
		
		a.removeActionListener(this);//去除監聽動作
		b.removeActionListener(this);
		c.removeActionListener(this);
		d.removeActionListener(this);
		
		if(correct) score++;//如果答對則加積分
		switch(qq) {
		case 1:
			setQ(englishQ);//第2題的問題
			break;
		case 2:
			setQ(swordQ);
			break;
		case 3:
			setQ(geographyQ);
			break;
		case 4:
			setQ(bigQ);
			break;
		case 5:
			if(score > maxScr) {//如果超過城主積分->挑戰成功
				maxScr = score;
				s = true;
			}
			
			dispose();//關閉視窗
			break;
		}
	}
	
	public boolean success() {
		return s;//回傳成功與否
	}
	
	public void start() { // 開始遊戲
		correct = false; // 回答正確與否
		qq = 0; // 第幾題
		score = 0;//積分歸零
		s = false;//預設
		build();//建立視窗
		this.setVisible(true);//視窗顯示	
		setQ(historyQ);//從歷史開始
	}
	
	public void build() {
		addImageByRepaint();
		this.setLayout(null);
		Border blackline = BorderFactory.createLineBorder(Color.BLACK);//題目框顏色		
		Font font = new Font("芫荽", Font.BOLD, 30);//字體設定
		JPanel pane = new JPanel();
		
		ButtonGroup bg = new ButtonGroup();//選項 -> 一次只能選一個
		a.setFont(font);//字體設定
		b.setFont(font);
		c.setFont(font);
		d.setFont(font);
		bg.add(a);//加入buttongroup
		bg.add(b);
		bg.add(c);
		bg.add(d);
		pane.add(a);//加入pane
		pane.add(b);
		pane.add(c);
		pane.add(d);
		pane.setBounds(100, 600, 800, 200);//設定位置
		pane.setOpaque(false);
    	this.add(pane);//加入視窗
		
		question.setHorizontalAlignment(SwingConstants.CENTER);//文字置中
		question.setFont(font);
		question.setBounds(100, 100, 800, 400);
		question.setBackground(Color.WHITE);//背景色
		question.setBorder(blackline);//框線
		question.setOpaque(true);//可以看到背景顏色
		this.add(question);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setQ(ArrayList<String[]> questions) {
		a.addActionListener(this);//監聽動作
		b.addActionListener(this);
		c.addActionListener(this);
		d.addActionListener(this);
		rd = (int)(Math.random() * 12);//隨機題目
		question.setText(questions.get(rd)[0]);//變更題目
		a.setText("A " +  questions.get(rd)[1] + " ");//變更選項
		b.setText("B " +  questions.get(rd)[2] + " ");
		c.setText("C " +  questions.get(rd)[3] + " ");
		d.setText("D " +  questions.get(rd)[4] + " ");
	}
	
	private void readFile(File file, ArrayList<String[]> questions) {//讀問題檔
		try {
			Scanner scn = new Scanner(file);
			while(scn.hasNextLine()) {
				String[] line = scn.nextLine().split(" ");
				questions.add(line);
			}
			scn.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//畫圖
	public void addImageByRepaint() {
        ImagePanel imagePanel = new ImagePanel(frameSize, icon.getImage());
        setContentPane(imagePanel);
    }
	
	class ImagePanel extends JPanel {
        Dimension d;
        Image image;

        public ImagePanel(Dimension d, Image image) {
            super();
            this.d = d;
            this.image = image;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, d.width, d.height, this);
        }
    }
}
