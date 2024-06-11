package final_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class relax extends JFrame{

	public relax() {
		ImageIcon icon = new ImageIcon("客棧.gif");
		JPanel p = new JPanel();
	    JLabel l = new JLabel();
	    
	    l.setIcon(icon);
        p.add(l);
        getContentPane().add(p);
        setLocationRelativeTo(null);
        setResizable(false);
		setSize(480, 360);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Timer timer = new Timer(1500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		timer.start();
	}
	
	public static void main(String[] args){
		relax r = new relax();
	}
}
