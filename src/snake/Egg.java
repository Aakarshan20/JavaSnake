package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

//貪食蛇的蛋
public class Egg {
	int row, col;
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	int w = Yard.BLOCK_SIZE;
	int h = Yard.BLOCK_SIZE;
	private static Random r = new Random();
	
	public Egg(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public Egg() {
		//隨機找一行 找一列 讓他出現在位置上
		this(	r.nextInt(Yard.ROWS-3)+3, //避免出現在frame的標題列裡面
				r.nextInt(Yard.COLS));
	}
	
	
	public void reAppear() {//重新出現
		//從Yard.ROWS中隨機取一個int
		this.row = r.nextInt(Yard.ROWS-3)+3;
		this.col = r.nextInt(Yard.COLS-3);
	}
	
	//蛋的碰撞區
	public Rectangle getRect() {
		return new Rectangle(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row, w, h);
	}
	
	public void draw(Graphics g) {
		 Color c = g.getColor();
         g.setColor(Color.GREEN);
         g.fillOval(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row, w, h);
         g.setColor(c);
	}
	
}
