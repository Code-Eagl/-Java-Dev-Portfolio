package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	public int[][] map;
	public int brickWidth;
	public int brickheight;
	
	public MapGenerator(int raw,int col) {
		map= new int[raw][col];
		for (int i=0;i<raw;i++) {
			for(int j=0;j<col;j++) {
				map[i][j]=1;
			}
		}
		brickWidth=540/col;
		brickheight=150/raw;
	}
	public void setBrick(int value, int r, int c) {
		map[r][c]=value;
		
	}
	public void draw(Graphics2D g) {
		for (int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				if(map[i][j]>0) {
					g.setColor(Color.white);
					g.fillRect(j*brickWidth+80, i*brickheight+50, brickWidth, brickheight);
					g.setColor(Color.black);
					g.setStroke(new BasicStroke(5));
					g.drawRect(j*brickWidth+80, i*brickheight+50, brickWidth, brickheight);
				}
			}
		}
		
	}

}
