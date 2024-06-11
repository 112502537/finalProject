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
	String master = "";//成主名稱
	int passCost = 40;
	int costs = 0;
	Wilderness wd = new Wilderness();
	JLabel maxSc = new JLabel("目前記錄 : " + Integer.toString(wd.maxScr) + "題");
	String player;
	
	public WChoose() {
		this.setSize(500,400);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screenSize.getWidth()/2 - this.getWidth()/2), (int)(screenSize.getHeight()/2 - this.getHeight()/2));
		this.setUndecorated(true);
	}
	
	public void enter(String playerName) {
		setTitle(name);//
		maxSc.setText("目前記錄 : " + Integer.toString(wd.maxScr) + "題");//
		costs = 0;//初始化
		if(playerName.equals(master)) {//城主->過路費增加
			passCost *= 2;
			build1();
			this.setVisible(true);
		}
		else {
			player = playerName;
			build2();
			this.setVisible(true);
		}
	}
	
	//過路費增加
	public void build1() {
		Font font = new Font("芫荽", Font.BOLD, 60);	
		JLabel cost = new JLabel("過路費PLUS!");
		cost.setFont(font);
		cost.setForeground(Color.yellow);
		cost.setBackground(Color.black);
		cost.setBounds(0, 0, 500, 400);
		cost.setHorizontalAlignment(SwingConstants.CENTER);
		cost.setOpaque(true);
		this.setLayout(null);		
		this.add(cost);
		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		timer.start();
		this.addWindowListener (new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				timer.stop();
			}
		});
	}
	
	//選擇視窗
	public void build2() {
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
				
		if(name.equals("荒野")) pass.setText("放棄");
		
		this.add(pass);
		this.add(challenge);
		this.add(passRule);
		this.add(chRule1);
		this.add(chRule2);
		this.add(ruleB);
		this.add(maxSc);
		
		pass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				costs = passCost;
				passChoose pc = new passChoose(passCost);
				dispose();
			}
		});
		challenge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				costs = passCost*2;//入場費
				passChoose pc = new passChoose(passCost*2);//跳出入場費視窗
				dispose();
				pc.addWindowListener(new WindowAdapter(){//關閉後
					public void windowClosed(WindowEvent e) {
						wd.start();//問答顯示
						pc.removeWindowListener(this);
					}
				});
				
				wd.addWindowListener(new WindowAdapter(){//問答結束
					public void windowClosed(WindowEvent e) {
						wFinish wf = new wFinish(wd.success());
						wf.addWindowListener(new WindowAdapter() {
							public void windowClosed(WindowEvent e) {
								if(wd.success()) {//若是挑戰成功
									rename rn = new rename();//輸入新城名
									rn.addWindowListener(new WindowAdapter() {//輸入後再改城名
										public void windowClosed(WindowEvent e) {
											name = rn.getName() + "城";//
											master = player;//
											System.out.println("Master : " + master);
											System.out.println("Castle : " + name);
											rn.removeWindowListener(this);
										}
									});
								}
								wf.removeWindowListener(this);
							}
						});
						wd.getContentPane().removeAll();
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
}
