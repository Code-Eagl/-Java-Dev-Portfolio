package Game;

import java.awt.Component;

import javax.swing.JFrame;

public class MainClass extends JFrame {
	public MainClass() {
		setTitle("Brick Game");
		setSize(700, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GamePlay frame=new GamePlay();
		add(frame);
		
		setVisible(true);
		setResizable(false);
		

	}

	public static void main(String[] args) {
		new MainClass();
		

	}

}
