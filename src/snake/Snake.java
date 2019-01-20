package snake;

import java.awt.*;

//貪食蛇
public class Snake {
	private Node head = null;//頭
	private Node tail = null;//尾
	private int size = 0;//一開始0節
	
	private Node n = new Node(20,30, Dir.L);
		
	public Snake() {
		head = n;
		tail = n;
		size = 1;
	}
	
	public void addToTail() {//變長
		//判斷方向: 如蛇往左 尾巴就要加在右邊
		Node node = null;
		switch(tail.dir) {//尾巴的方向
			case L:
				node = new Node(tail.row,tail.col+1, tail.dir);
				break;
			case U:
				node = new Node(tail.row+1,tail.col, tail.dir);
				break;
			case R:
				node = new Node(tail.row,tail.col-1, tail.dir);
				break;
			case D:
				node = new Node(tail.row-1,tail.col, tail.dir);
				break;
		}
		tail.next = node;
		tail = node;
		size++;
	}
	
	public void addToHead() {//變長(從頭部)
		//判斷方向: 如蛇往左 尾巴就要加在右邊
		Node node = null;
		switch(head.dir) {//尾巴的方向
			case L:
				node = new Node(head.row,head.col-1, head.dir);
				break;
			case U:
				node = new Node(head.row-1,head.col, head.dir);
				break;
			case R:
				node = new Node(head.row,head.col+1, head.dir);
				break;
			case D:
				node = new Node(head.row+1,head.col, head.dir);
				break;
		}
		node.next = head;
		head = node;
		size++;
	}
	
	//把整條蛇畫出來
	public void draw(Graphics g) {
		if(size <=0) return;
		for(Node n = head; n != null; n=n.next ) {
			n.draw(g);
		}
	}
	
	private class Node{//每節
		int w = Yard.BLOCK_SIZE;//每節的寬等於地圖的寬
		int h = Yard.BLOCK_SIZE;//每節的高等於地圖的寬
		int row, col;//所在位置
		Dir dir = Dir.L;//一開始就給他朝左走
		Node next = null;//下一節是誰(一開始為空)
		
		Node(int row, int col, Dir dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
		
		void draw(Graphics g) {//把自己畫出來
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			
			//畫矩型 左上角的點就是所在位置乘以地圖的格子寬(高)
			g.fillRect(Yard.BLOCK_SIZE*row, Yard.BLOCK_SIZE*col, w, h);
			g.setColor(c);
		}
	}
	
	
	
}
