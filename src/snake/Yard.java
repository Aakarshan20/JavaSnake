package snake;
//Yard.java 
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Yard extends Frame {

  public static final int ROWS = 30;
  public static final int COLS = 30;
  public static final int BLOCK_SIZE = 15;
   
  //private boolean flag = true;
  
  PaintThread paintThread = new PaintThread();
  private boolean gameover = false;
  
  private int score = 0;
   
  public int getScore() {
	return score;
	}
	
	



	public void setScore(int score) {
		this.score = score;
	}

  Snake s = new Snake(this);
  Egg e = new Egg();
  
   
  Image offScreenImage = null;
   
  public void lauch() {
      this.setLocation(300, 300);
      this.setSize(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
      this.addWindowListener(new WindowAdapter() {

          public void windowClosing(WindowEvent arg0) {
              System.exit(0);
          }
           
      });           
      this.setVisible(true);
       
      //new Thread(new PaintThread()).start();
      this.addKeyListener(new KeyMonitor());
      
      new Thread(paintThread).start();
  }
   
   
  public static void main(String[] args) {
       
      new Yard().lauch();
       
  }
  
  public void stop() {
	 //gameover = false; 
	  gameover = true;
  }
   
  public void paint(Graphics g) {
      Color c = g.getColor();
      g.setColor(Color.GRAY);
      g.fillRect(0, 0, COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
      g.setColor(Color.DARK_GRAY);
      for(int i=1; i<ROWS; i++) {
          g.drawLine(0, BLOCK_SIZE * i, COLS * BLOCK_SIZE, BLOCK_SIZE * i);
      }
      for(int i=1; i<COLS; i++) {
          g.drawLine(BLOCK_SIZE * i, 0, BLOCK_SIZE * i, BLOCK_SIZE * ROWS);
      }
      
      //畫分數
      g.setColor(Color.YELLOW);
      g.drawString("score: "+score, 10, 60);
      if(gameover) {//如果掛掉
    	  //g.setFont(new Font("Verdana", Font.BOLD | Font.HANGING_BASELINE, 50));
          g.setFont(new Font("Verdana", Font.BOLD, 50));
    	  g.drawString("geme over", 60, 180);
    	  paintThread.gameOver();
      }
      g.setColor(c);
      e.draw(g);//畫蛋
      s.draw(g);//畫蛇
      s.eat(e);//吃蛋
  }
      
  
      
     
   

  public void update(Graphics g) {
      if(offScreenImage == null) {
          offScreenImage = this.createImage(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
      }
      Graphics gOff = offScreenImage.getGraphics();
      paint(gOff);
      g.drawImage(offScreenImage, 0, 0, null);
  }
   
  private class PaintThread implements Runnable{
	  private boolean running = true;
      public void run() {
          while(running) {
              repaint();
              try {
                  Thread.sleep(100);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }
      
      public void gameOver() {
    	  running = false;
      }
       
  }
   
  private class KeyMonitor extends KeyAdapter{

      public void keyPressed(KeyEvent e) {
          s.keyPressed(e); 
      }
       
  }

}
