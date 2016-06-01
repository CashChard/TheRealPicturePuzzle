import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/************
 * Siobhan Gannon
 * Period C
 * May 9, 2016
 * This is a sliding puzzle game and final project.
 * 
 */

public class Puzzle implements java.awt.event.ActionListener{
	private static int size;
	private int[][] arr;
	public JButton[][] gameButtons;
	private JFrame f = new JFrame("Number Puzzle");
	private JPanel p = new NPPanel();
	private static double moves=0;

	public Puzzle(){
		arr = new int[size][size];
		intro();
	}

	public void intro() {//introduces user to game and explains rules
		System.out.println("Welcome to Number Puzzle!"
				+ "\nCreated by: Richard Oldroyd and Siobahn Gannon"
				+ "\n-In this game, you will choose a difficulty level, and try to "
				+ "\n recreate the mixed up array of numbers. Try to get the lowest "
				+ "\n score possible. Type \"Ready\" to start the game!");
		boolean go = true;
		while(go){
		Scanner console = new Scanner(System.in);
		String ready = console.next();
		if (ready.toLowerCase().equals("ready")) {// If user typed "ready", game starts to initialize
			go=false;
		}
		else
			System.out.println("Are you ready yet?");
		}
		Scanner console = new Scanner(System.in);
		getD(console);
		create();
	}

	
	private void create() {//JFrame created
	  		JFrame f = new JFrame("Number Puzzle");
	  		JPanel p = new NPPanel();
	 		f.setSize(1920, 1029);
	 		f.setResizable(false);
	 		f.setSize(1100, 1029);
	 		f.setResizable(false);//CHANGE TO false WHEN FINISHED
	  		f.setContentPane(p);
	 		Color SOFTBLUE = new Color(0,191,255);
	 		p.setBackground(SOFTBLUE);
	  		f.setVisible(true);
	  		p.setLayout(null);//Must make panel null to allow bounds to be set for the button
	  		int s = 660/size - size*10;
	  		int start = 205 + (660-s*size)/2;
		    JButton[][] gameButtons= new JButton[size][size];
			for (int i = 0; i < size; i++) 
			{
			  for (int j = 0; j < size; j++) 
			  {
			    gameButtons[i][j] = new JButton("Puzzle Game");
			    gameButtons[i][j].setBounds(start+s*i,start+s*j,s,s);
			    p.add(gameButtons[i][j]);
			  }
			}
	}

	public void makeNumbers(){//Assign random numbers from 1-size*size without duplicates
		Random rand = new Random();
		boolean check=true;
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){//Loop through array [size][size]
				arr[i][j] = rand.nextInt(size*size)+1; //Randomly assign number
				
				for(int m=0; m<size; m++){//Check if number has been used
					for(int n=0; n<size; n++){
						if(!(i==m && j==n) && arr[i][j]==arr[m][n])
							check=false;
					}
				}
				if(!check){//If number was duplicated, the loop will restart
					j--;
					check=true;
				}
			}
		}
	}
	
	public static void getD(Scanner console) {//asks difficulty and makes sure user answer is valid
		System.out.println("What is the difficulty you want?(Type the #):");
		System.out.println("		1. Easy(3x3 Grid/1-8)		2. Medium(4x4 Grid/1-15)		3. Hard(5x5 Grid)/1-24");
		System.out.print("User choice: ");
		int d = console.nextInt();
		if(d>3 || d<1) {
			System.out.println("Try Again\n");
			getD(console);
		}
		size=d+2;
	}

	public static void end(Scanner console) {
		System.out.println("==============================================\n"
				+ "CONGRATULATIONS, YOU WIN!"
				+ "\nTotal moves: " + moves
				+ "\nWould you like to play again?(Y or N)");
			checkEnd(console);
	}
	public static void checkEnd(Scanner console) {//for after game is finished
		String again = console.next();
		if(again.startsWith("Y") || again.startsWith("y")) {
			getD(console);
		}
	}

	//Overloaded method to tell if buttons have been pressed
	public void actionPerformed(ActionEvent ae)
	{
	  for (int i = 0; i < size; i++){
	    for (int j = 0; j < size; j++){
	       if(ae.getSource()==gameButtons[i][j]) //gameButtons[i][j] was clicked
	       {
	            System.out.println("Button " + i + j + " is pressed");
	            moves+=0.5;
	       }
	     }
	  }
	}
}

class NPPanel extends JPanel {//Panel class

	//public static final int X = 0;
	//public static final int Y = 0;
	public NPPanel() {}
	// creates panel
	public void paint(Graphics g) {
		super.paint(g);
		int x = 205;
		int y = 1000;
		Color DARKBLUE = new Color(100,149,237);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, x, y);
		g.fillRect(890, 0, x, y);
		sierp.main(null, g);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 100));
		g.drawString("Number Puzzle", 205, 80);
		g.setFont(new Font("", Font.BOLD, 25));
		g.drawString("", 720, 100);
		
	}
	
}
class sierp {
	
	public static final int X = 205;
	public static final int Y = 1000;

	public static void main(String[] args, Graphics g) {
		g.fillRect(X/3,Y/3,X/3,Y/3);
		squares(g,X,Y,0,0);
		squares(g,X,Y,890,0);
	}
	public static void squares(Graphics g, int x, int y, int factorX, int factorY) {
		Random rand= new Random();
		int RandX= rand.nextInt(256);
		int RandY= rand.nextInt(256);
		int RandZ= rand.nextInt(256);
		
		Color random = new Color(RandX, RandY, RandZ);
		g.setColor(random);
		g.fillRect((x/3)+factorX,(y/3)+factorY,x/3,y/3);
	
		if(x>0 && y>0) {
			squares(g,x/3,y/3,factorX,factorY);
			squares(g,x/3,y/3,factorX + x/3,factorY);
			squares(g,x/3,y/3,factorX + x/3*2,factorY);
			squares(g,x/3,y/3,factorX+x/3*2,factorY);
			squares(g,x/3,y/3,factorX,factorY+y/3);
			squares(g,x/3,y/3,factorX,factorY+y/3*2);
			squares(g,x/3,y/3,factorX+x/3,factorY+y/3*2);
			squares(g,x/3,y/3,factorX+x/3*2,factorY+y/3);
			squares(g,x/3,y/3,factorX+x/3*2,factorY+y/3*2);
		}
	}
}
