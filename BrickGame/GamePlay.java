package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.StreamCorruptedException;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements ActionListener, KeyListener{
	private boolean play=false;
	private int score =0;
	private int totalBrick=21;
	private Timer timer;
	private int delay =4;
	private int ballposX=120;
	private int ballposY=350;
	private int ballxdir=-1;
	private int ballydir=-2;
	private int playerx=350;
	private MapGenerator map;
	
	public GamePlay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		
		timer =new Timer(delay, this);
		timer.start();
		
		map=new MapGenerator(3, 7);
	}
	public void paint(Graphics g) {
		//black canvas
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//border
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(1, 3, 3, 592);
		g.fillRect(682, 5, 3, 592);
		
		//paddle
		g.setColor(Color.green);
		g.fillRect(playerx, 550, 100, 8);
		
		//bricks
		map.draw((Graphics2D) g);
		
		//ball
		g.setColor(Color.red);
		g.fillOval(ballposX, ballposY, 20, 20 );
		
		//score
		g.setColor(Color.green);
		g.setFont(new Font("serif",Font.BOLD,20));
		g.drawString("Score :"+score, 550, 30);
		
		//gameover
		if(ballposY>=570){
			play=false;
			ballxdir=0;
			ballydir=0;
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD,40));
			g.drawString("Gane over!! Score "+score, 200, 300);
			g.setFont(new Font("serif", Font.BOLD,30));
			g.drawString("Press Enteer to Restart", 230, 350);
			
		}
		if(totalBrick<=0) {
			play=false;
			ballxdir=0;
			ballydir=0;
			
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD,40));
			g.drawString("you won : Score"+score, 200, 300);
			g.setFont(new Font("serif", Font.BOLD,30));
			g.drawString("Press Enteer to Restart", 230, 350);

		}
	}
	
	private void moveLeft() {
		play=true;
		playerx-=20;
	}
	private void moveRight() {
		play=true;
		playerx+=20;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//this is the code for controlling paddle
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(playerx<=0)
				playerx=0;
			else
				moveLeft();
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(playerx>=600)
				playerx=600;
			else
				moveRight();
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(!play) {
				score =0;
				totalBrick=21;
				ballposX=120;
				ballposY=350;
				ballxdir=-1;
				ballydir=-2;
				playerx=350; 
				map=new MapGenerator(3, 7);
			}
		}
		repaint();

	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(play) {
			if(ballposX<=0) {
				ballxdir=-ballxdir;
			}
			if(ballposX>=670) {
				ballxdir=-ballxdir;
			}
			if(ballposY<=0) {
				ballydir=-ballydir;
			}
			int random =(int)(Math.random()*1000);
			Rectangle ballRact=new  Rectangle(ballposX, ballposY, 20,20);
			Rectangle paddleRext=new Rectangle(playerx,550,100,8);
			if (ballRact.intersects(paddleRext)) {
				ballydir=-ballydir;
			}
			
			A:for(int i=0;i<map.map.length;i++) {
				for(int j=0;j<map.map[0].length;j++) {
					if(map.map[i][j]>0) {
						int width=map.brickWidth;
						int height=map.brickheight;
						int brickXpos=80+j*width;
						int brickypos=50+i*height;
						
						Rectangle brickRect=new Rectangle(brickXpos, brickypos, width, height);
						if(ballRact.intersects(brickRect)) {
							map.setBrick(0, i, j);
							totalBrick--;
							score+=5;
							
							if(ballposX+19<=brickXpos || ballposX+1>=brickXpos+width) {
								ballxdir=-ballxdir;
							}
							else {
								ballydir=-ballydir;
							}
							break A;
						}
					}
				}
			}
		
			ballposX+=ballxdir;
			ballposY+=ballydir;
		}
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	

}
