package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
 
 
public class Snake {
     
    Node head = null;
    Node tail = null;
    int size = 0;
    Node n = new Node(20, 30, Dir.L);
     
    public  Snake() {
        head = n;
        tail = n;
        size = 1;
    }
     
    public class Node {
         
        int w = Yard.BLOCK_SIZE;
        int h = Yard.BLOCK_SIZE;
        int row, col;
        Dir dir = Dir.L;
        Node next = null;
        Node prev = null;
         
        Node(int row, int col, Dir dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }
         
        public void draw(Graphics g) {
             
            Color c = g.getColor();
            g.setColor(Color.BLACK);
            g.fillRect(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row, w, h);
            g.setColor(c);
             
        }
         
     
    } 
     
    public void addToTail() {
         
        Node node = null;
         
        switch(tail.dir) {
            case L:
                node  = new Node(tail.row, tail.col + 1, tail.dir);
                break;
            case U:
                node  = new Node(tail.row + 1, tail.col, tail.dir);
                break;
            case R:
                node  = new Node(tail.row, tail.col - 1, tail.dir);
                break;
            case D:
                node  = new Node(tail.row - 1, tail.col, tail.dir);
                break;
        }
         
        tail.next = node;
        node.prev = tail;
        tail = node;
        size++;
         
    }
     
    public void addToHead() {
         
        Node node = null;
         
        switch(head.dir) {
            case L:
                node  = new Node(head.row, head.col - 1, head.dir);
                break;
            case U:
                node  = new Node(head.row - 1, head.col, head.dir);
                break;
            case R:
                node  = new Node(head.row, head.col + 1, head.dir);
                break;
            case D:
                node  = new Node(head.row + 1, head.col, head.dir);
                break;
        }
         
        node.next = head;
        head.prev = node;
        head = node;
        size++;
         
    }
     
    public void draw(Graphics g) {
        if (size <= 0)     return;
        move();
        for(Node n = head; n != null; n = n.next) {
            n.draw(g);
        }
 
    }
 
    private void move() {
 
        addToHead();
        deleteFromTail();
    }
 
    private void deleteFromTail() {
        if(size ==0) return;
        tail = tail.prev;
        tail.next = null;
    }
 
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key) {
        case  KeyEvent.VK_LEFT:
            head.dir = Dir.L;
            break;
        case  KeyEvent.VK_UP:
            head.dir = Dir.U;
            break;
        case  KeyEvent.VK_RIGHT:
            head.dir = Dir.R;
            break;
        case  KeyEvent.VK_DOWN:
            head.dir = Dir.D;
            break;
        }
    }
}