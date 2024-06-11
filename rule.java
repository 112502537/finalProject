package final_project;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import final_project.WChoose.ImagePanel;

public class rule extends JFrame{

	ImagePanel imagePanel;
	ImageIcon ruleM = new ImageIcon("rule.jpg");
	ImageIcon ruleBegin = new ImageIcon("beginRule.jpg");
	Dimension frameSize = new Dimension(500, 400);
	
	public rule(String fr) {
		this.setLayout(null);
		this.setSize(500,400);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screenSize.getWidth()/2 - this.getWidth()/2), (int)(screenSize.getHeight()/2 - this.getHeight()/2));
		
		if(fr.equals("WChoose")) addImageByRepaint(frameSize, ruleM);
		if(fr.equals("beginning")) addImageByRepaint(frameSize, ruleBegin);
		this.setVisible(true);
	}

	//把規則畫上去
	public void addImageByRepaint(Dimension d, ImageIcon icon) {
        imagePanel = new ImagePanel(d, icon.getImage());
        setContentPane(imagePanel);
        setVisible(true);
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
}
