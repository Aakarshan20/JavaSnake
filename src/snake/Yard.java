package snake;
//Yard.java 
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Yard extends Frame {

  public static final int ROWS = 50;
  public static final int COLS = 50;
  public static final int BLOCK_SIZE = 10;
   
  private boolean gameOver = false;
   
  Snake s = new Snake();
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
       
      new Thread(new PaintThread()).start();
      addKeyListener(new KeyMonitor()); 
  }
   
   
  public static void main(String[] args) {
       
      new Yard().lauch();
       
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

      public void run() {
          while(true) {
              repaint();
              try {
                  Thread.sleep(100);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
           
      }
       
  }
   
  private class KeyMonitor extends KeyAdapter{

      public void keyPressed(KeyEvent e) {
          s.keyPressed(e); 
      }
       
  }

}
