package snake;
import java.awt.Color;
//貪食蛇的地圖(後院)
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Yard extends Frame {
	


	public static final int ROWS =  50;//橫的格子
	public static final int COLS = 50;//縱的格子
	public static final int BLOCK_SIZE = 15;//每格的寬高
	
	Snake s = new Snake();
	
	Image offScreenImage = null;
	
	public void launch() {//畫圖主method
		this.setLocation(300,300);
		this.setSize(COLS*BLOCK_SIZE,ROWS* BLOCK_SIZE);
		
		
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame寫法
		this.addWindowListener(new WindowAdapter() {//Frame寫法
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setVisible(true);
		
		new Thread(new PaintThread()).start();//調用thread並啟動
		this.addKeyListener(new KeyMonitor());
	}

	
	public static void main(String[] args) {
		new Yard().launch();
	}
	
	//畫背景
	public void paint(java.awt.Graphics g) {
		// TODO Auto-generated method stub
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.fillRect(0,0,COLS*BLOCK_SIZE,ROWS* BLOCK_SIZE);
		g.setColor(Color.DARK_GRAY);
		//畫出橫線
		for(int i=1; i<ROWS; i++) {
			g.drawLine(0, BLOCK_SIZE *i , ROWS*BLOCK_SIZE, BLOCK_SIZE *i);
		}
		
		//畫出直線
		for(int i=1; i<COLS; i++) {
			g.drawLine(BLOCK_SIZE *i, 0 , BLOCK_SIZE *i, ROWS* BLOCK_SIZE);
		}
		
		g.setColor(c);
		
		s.draw(g);//畫蛇
	}
	
	@Override
	public void update(Graphics g) {//覆寫update方法 判斷如果圖是一樣的就不repaint
		if(offScreenImage == null) {
			offScreenImage = this.createImage(COLS*BLOCK_SIZE,ROWS* BLOCK_SIZE);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0,0, null);
	}
	
	//讓蛇移動
	//建立thread類
	private class PaintThread implements Runnable{

		@Override
		public void run() {
			while(true) {
				repaint();//重繪
				try {
					Thread.sleep(50);//50ms
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//監聽鍵盤
	private class KeyMonitor extends KeyAdapter{
		
		public void keyPressed(KeyEvent e) {
			s.keyPressed(e);
		}


	}
	

}
