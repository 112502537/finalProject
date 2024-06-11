package final_project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class begin {

	static JButton hajime = new JButton("遊戲開始");
	static JButton ruleB = new JButton("遊戲規則");
	static ImageIcon beginning = new ImageIcon("beginning.png");
	static JFrame beginF = new JFrame();
	static Dimension frameSize = new Dimension(1500, 800);
	static JLabel gameName = new JLabel("SQUIRREL WAR");
	static JLabel b = new JLabel();
	
	public begin() {
		Font font = new Font("芫荽", Font.BOLD, 80);
		beginF.setSize(1200, 800);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		beginF.setLocation((int)(screenSize.getWidth()/2 - beginF.getWidth()/2), (int)(screenSize.getHeight()/2 - beginF.getHeight()/2));
		beginF.setUndecorated(true);
		beginF.setLayout(null);
		
		hajime.setBounds(350, 500, 500, 100);
		hajime.setBackground(new Color(0, 102, 0, 100));
		hajime.setFont(font);
		hajime.setForeground(Color.white);
		
		gameName.setBounds(125, 200, 950, 150);
		gameName.setFont(new Font("Courier 10 Pitch", Font.BOLD, 120));
		gameName.setForeground(Color.WHITE);
		gameName.setBackground(new Color(0,135,0,150));
		gameName.setOpaque(true);
		
		ruleB.setBounds(450, 700, 300, 40);
		ruleB.setBackground(new Color(0, 102, 0));
		ruleB.setFont(new Font("芫荽", Font.BOLD, 30));
		ruleB.setForeground(Color.white);
		
		addImageByRepaint(frameSize, beginning);
		beginF.add(hajime);
		beginF.add(gameName);
		beginF.add(ruleB);
		
		hajime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beginF.dispose();
			}
		});
		
		ruleB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rule r = new rule("beginning");
			}
		});
	}
	
	public static void main(String[] args) {
		begin b = new begin();
	}
	
	static class ImagePanel extends JPanel {
	    Dimension d;
	    Image image;

	    public ImagePanel(Dimension d, Image image) {
	        super();
	        this.d = d;
	        this.image = image;
	    }

	    
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, d.width, d.height, beginF);
	    }
	}
	
	public static void addImageByRepaint(Dimension d, ImageIcon icon) {
        ImagePanel imagePanel = new ImagePanel(d, icon.getImage());
        beginF.setContentPane(imagePanel);
        beginF.setVisible(true);
    }
}
