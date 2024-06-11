package final_project;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class test1 {

	public test1() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		WChoose wc = new WChoose();//建立物件
		
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		while(!s.equals("e")){
			if(s.equals("1")) wc.enter("1");//進入地塊，傳入玩家名稱
			else wc.enter("2");//進入地塊，傳入玩家名稱
			wc.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent e) {
					System.out.println(wc.getCost());//當視窗要關閉時回傳消耗值
					wc.getContentPane().removeAll();//清空視窗
					wc.removeWindowListener(this);//關閉追蹤
				}
			});
			s = sc.next();
		}
	}

}
