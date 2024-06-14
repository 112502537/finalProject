package final_project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class WChoose extends JFrame
{
	String name = "荒野";//地塊名稱
	public int master = 0;//城主名稱
	int passCost = 40;//過路費
	int costs = 0;//當玩家消耗的經驗值
	public Wilderness wd = new Wilderness();//問答
	JLabel maxSc = new JLabel("目前記錄 : " + Integer.toString(wd.maxScr) + "題");//顯示目前最高紀錄
	int player;//第幾位玩家
	int i; // 第幾塊土地
	public Boolean chooseP = false;//是否選擇挑戰
	Boolean s = false;//是否成功
	
	
	public WChoose(int i) {
		this.i = i;
		this.setSize(500,400);//視窗大小
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screenSize.getWidth()/2 - this.getWidth()/2), (int)(screenSize.getHeight()/2 - this.getHeight()/2));//視窗置中
		this.setUndecorated(true);//關閉外框
	}
	
	public void enter(int playerName) {
		setTitle(name);//城池名稱
		maxSc.setText("目前記錄 : " + Integer.toString(wd.maxScr) + "題");
		costs = 0;//初始化
		if(playerName == master) {//城主->過路費增加
			passCost *= 2;//過路費翻倍
			build1();//建立畫面
			this.setVisible(true);
		}
		else {//非城主->選擇
			player = playerName;
			build2();
			this.setVisible(true);
		}
	}
	
	//過路費增加
	public void build1() {
		Font font = new Font("芫荽", Font.BOLD, 60);	//字體
		JLabel cost = new JLabel("過路費PLUS!");
		cost.setFont(font);
		cost.setForeground(Color.yellow);
		cost.setBackground(Color.black);
		cost.setBounds(0, 0, 500, 400);
		cost.setHorizontalAlignment(SwingConstants.CENTER);
		cost.setOpaque(true);
		this.setLayout(null);		
		this.add(cost);
		//延遲1秒關閉
		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		timer.start();
		//如果關閉視窗，便關閉timer
		this.addWindowListener (new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				timer.stop();
			}
		});
	}
	
	//選擇視窗
	public void build2() {
		chooseP = false;//初始化
		JButton pass = new JButton("過路費");
		JButton challenge = new JButton("入場費");
		ImageIcon icon = new ImageIcon("wild.jpg");//背景圖
		Dimension frameSize = new Dimension(500, 400);
		JLabel passRule = new JLabel("交出" + Integer.toString(passCost) + "經驗值");
		JLabel chRule1 = new JLabel("交出" + Integer.toString(passCost * 2) + "經驗值");
		JLabel chRule2 = new JLabel("挑戰成功成為城主");
		JButton ruleB = new JButton("遊戲規則");
		
		addImageByRepaint(frameSize, icon);
		this.setLayout(null);
		this.setSize(500,400);//視窗大小
		Font font = new Font("芫荽", Font.BOLD, 30);//字體設定
		Font font1 = new Font("芫荽", Font.BOLD, 15);
		
		pass.setBounds(50, 100, 150, 75);
		pass.setBackground(Color.BLACK);
		pass.setFont(font);
		pass.setForeground(Color.white);
		
		challenge.setBounds(275, 100, 150, 75);
		challenge.setBackground(Color.BLACK);
		challenge.setFont(font);
		challenge.setForeground(Color.white);
		
		passRule.setBounds(50, 200, 150, 15);
		passRule.setBackground(new Color(150, 150, 150, 120));//半透明
		passRule.setFont(font1);
		passRule.setForeground(Color.white);
		passRule.setHorizontalAlignment(SwingConstants.CENTER);
		passRule.setOpaque(true);
		
		chRule1.setBounds(275, 200, 150, 15);
		chRule1.setBackground(new Color(150, 150, 150, 120));
		chRule1.setFont(font1);
		chRule1.setForeground(Color.white);
		chRule1.setHorizontalAlignment(SwingConstants.CENTER);
		chRule1.setOpaque(true);
		
		chRule2.setBounds(275, 215, 150, 15);
		chRule2.setBackground(new Color(150, 150, 150, 120));
		chRule2.setFont(font1);
		chRule2.setForeground(Color.white);
		chRule2.setHorizontalAlignment(SwingConstants.CENTER);
		chRule2.setOpaque(true);
		
		
		ruleB.setBounds(10, 10, 100, 15);
		ruleB.setBackground(Color.BLACK);
		ruleB.setFont(font1);
		ruleB.setForeground(Color.white);
		ruleB.setHorizontalAlignment(SwingConstants.CENTER);
		
		maxSc.setBounds(320, 360, 180, 40);
		maxSc.setBackground(new Color(0, 0, 102, 120));
		maxSc.setFont(new Font("芫荽", Font.BOLD, 20));
		maxSc.setForeground(Color.white);
		maxSc.setHorizontalAlignment(SwingConstants.CENTER);
		maxSc.setOpaque(true);
				
		if(name.equals("荒野")) {//如果是荒野地塊，便修改選項
			pass.setText("放棄");
			challenge.setText("佔領");
		}
		
		this.add(pass);
		this.add(challenge);
		this.add(passRule);
		this.add(chRule1);
		this.add(chRule2);
		this.add(ruleB);
		this.add(maxSc);
		
		pass.addActionListener(new ActionListener() {//選擇放棄或是過路費
			public void actionPerformed(ActionEvent e) {
				s = false;//未挑戰->未成功
				costs = passCost;//消耗值為過路費
				passChoose pc = new passChoose(passCost);//跳出視窗顯示消耗值
				dispose();//關閉選擇視窗
			}
		});
		challenge.addActionListener(new ActionListener() {//選擇入場費或是佔領
			public void actionPerformed(ActionEvent e) {
				chooseP = true;//選擇挑戰
				s = false;//預設為失敗
				costs = passCost*2;//入場費是過路費的2倍
				passChoose pc = new passChoose(passCost*2); // 跳出入場費視窗
				dispose();//關閉選擇視窗
				pc.addWindowListener(new WindowAdapter(){ // 關閉後
					public void windowClosed(WindowEvent e) {
						wd.start();//問答顯示
						pc.removeWindowListener(this);//移除監聽
					}
				});
				wd.addWindowListener(new WindowAdapter(){//問答結束
					public void windowClosed(WindowEvent e) {
						s = wd.success();//確認是否成功
						wFinish wf = new wFinish(wd.success());//顯示結果視窗
						wf.addWindowListener(new WindowAdapter() {//結果視窗關閉後
							public void windowClosed(WindowEvent e) {
								if(wd.success()) {// 若是挑戰成功
									rename rn = new rename();//輸入新城名
									rn.addWindowListener(new WindowAdapter() {//輸入後再改城名
										public void windowClosed(WindowEvent e) {
											name = rn.getName() + "城";//
											master = player;//修改城主名稱
											rn.removeWindowListener(this);
										}
									});
								}
								wf.removeWindowListener(this);
							}
						});
						wd.getContentPane().removeAll();//畫面清除
						wd.removeWindowListener(this);
					}
				});
			}
		});
		
		ruleB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rule r = new rule("WChoose");//規則視窗
			}
		});
	}
	
	//背景圖
	public void addImageByRepaint(Dimension d, ImageIcon icon) {
        ImagePanel imagePanel = new ImagePanel(d, icon.getImage());
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
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, d.width, d.height, this);
        }
    }
	
	//回傳消耗值
	public int getCost() {
		return costs;
	}
	//回傳是否挑戰成功
	public boolean successful() {
		return s;
	}

}