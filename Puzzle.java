import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.event.EventListenerList;

/************
 * Siobhan Gannon
 * Period C
 * May 9, 2016
 * This is a sliding puzzle game and final project.
 * 
 */

public class Puzzle implements ActionListener, MouseListener{ //extends EventListenerList
	private static int size;
	private static int[][] arr;
	public static JButton[][] gameButtons;
	private JFrame f = new JFrame("Number Puzzle");
	private JPanel p = new NPPanel();
	private static int moves=0; //CHANGED TO INT
	private static int s=0;
	private String s1="";
	private String s2="";
	private int p1=0;
	private int p2=0;

	//Constructor will begin the game
	public Puzzle(){
		intro();
		Scanner console = new Scanner(System.in);
		getD(console);
		arr=new int [size][size];
		makeNumbers();
		create();
	}

	public void intro() {//introduces user to game and explains rules
		System.out.println("Welcome to Number Puzzle!"
				+ "\nCreated by: Richard Oldroyd and Siobhan Gannon"
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
		    gameButtons= new JButton[size][size]; //try deleting JButton[][] here
			for (int i = 0; i < size; i++){
			  for (int j = 0; j < size; j++){
				String name = arr[i][j] + "";
			    gameButtons[i][j] = new JButton(name);
			    gameButtons[i][j].setBounds(start+s*i,start+s*j,s,s);
			    p.add(gameButtons[i][j]);
			    gameButtons[i][j].addMouseListener(this);
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
		boolean again = check();
		if(!again)
			makeNumbers();
	}
	
	//This will check if the number arrangement is possible
	public boolean check(){
		int inversions=0;
		for(int i=0; i<arr.length-1; i++){
			for(int j=0; j<arr[0].length-1; j++){
				for(int k=0; k<arr.length-1; k++){
					for(int m=0; m<arr[0].length-1; m++){
						if(arr[i][j]>arr[k][m])
							inversions++;
					}
				}
			}
		}
		if(inversions%2==0)
			return true;
		else
			return false;
	}
	
	public static void getD(Scanner console) {//asks difficulty and makes sure user answer is valid
		System.out.println("What is the difficulty you want?(Type the #):");
		System.out.println("		1. Easy(3x3 Grid/1-8)		2. Medium(4x4 Grid/1-15)		3. Hard(5x5 Grid)/1-24");
		System.out.print("User choice: ");
		int d = console.nextInt();
		boolean check=true;
		while(check){
		if(d>3 || d<1) {
			System.out.println("Try Again\n");
			System.out.print("User choice: ");
			d = console.nextInt();
		}
		else
			check=false;
		}
		System.out.print("Creating board...");
		size=d+2;
		gameButtons = new JButton[size][size];
	}

	//This will run when the user has won the game
	public static void end(Scanner console) {
		System.out.println("==============================================\n"
				+ "CONGRATULATIONS, YOU WIN!"
				+ "\nTotal moves: " + moves
				+ "\nWould you like to play again?(Y or N)");
			checkEnd(console);
	}
	
	//This will check if the user wants to play agian
	public static void checkEnd(Scanner console) {//for after game is finished
		String again = console.next();
		if(again.startsWith("Y") || again.startsWith("y")) {
			getD(console);
		}
	}
	
	//ADDED THIS METHOD
//This will check if the user has solved the puzzle
	public static void checker(){
	boolean win=true;
	int counter = 0;
	Scanner console = new Scanner(System.in);
	for (int i = 0; i < size; i++){
		for (int j = 0; j < size; j++){
			counter++;
			if(arr[i][j]!=counter){
				win = false;}
		}
	}
	if(win){
		end(console);
	}
	}
	
	//This will get a random color
	public static Color colorChooser(){
		Random rand= new Random();
		int RandX= rand.nextInt(256);
		int RandY= rand.nextInt(256);
		int RandZ= rand.nextInt(256);
		
		Color random = new Color(RandX, RandY, RandZ);
		return random;
}

	//Overloaded method to tell if buttons have been pressed
	public void pressed(MouseEvent ae)
	{
	  int temp=0;
	  for (int i = 0; i < size; i++){
	    for (int j = 0; j < size; j++){
		String name = gameButtons[i][j].getText();//ADDED NAME
	       if(((JButton)ae.getSource()).getText().equals(name))//gameButtons[i][j] was clicked
	       {
	    	System.out.println("click");
	    	gameButtons[i][j].setBackground(colorChooser());
	    	gameButtons[i][j].setContentAreaFilled(false); 
	    	gameButtons[i][j].setOpaque(true);

	        s++;
	            
	        if(s%2==1){
	        	s1="" + arr[i][j];
	            p1=i;
	            p2=j;
	            }
	        else{
	       	  	s2="" + arr[i][j];
	       	  	//if(Math.abs(i-p1)<=1 && Math.abs(j-p2)<=1 && i!=p1 && j!=p2){
	       	  	gameButtons[i][j].setText(s1);
	       	  	gameButtons[p1][p2].setText(s2);
	       	  	moves++; //CHANGED FROM MOVES+=0.5
	       	  	temp=arr[i][j];
	       	  	arr[i][j]=arr[p1][p2];
	       	  	arr[p1][p2]=temp;
	       	  //}
	       	 }
	       }
	     }
	  }
	checker();
	}
	
	//Must be implemented
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		pressed(arg0);
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		//Color DARKBLUE = new Color(100,149,237);
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

//This will create a sierpinski's carpet design in the JPanel
class sierp {
	
	public static final int X = 205;
	public static final int Y = 1000;

	public static void main(String[] args, Graphics g) {
		g.fillRect(X/3,Y/3,X/3,Y/3);
		squares(g,X,Y,0,0);
		squares(g,X,Y,890,0);
	}
	public static void squares(Graphics g, int x, int y, int factorX, int factorY) {
		//Making buttons random colors instead of sierpinski
		//Random rand= new Random();
		//int RandX= rand.nextInt(256);
		//int RandY= rand.nextInt(256);
		//int RandZ= rand.nextInt(256); 
		
		//Color random = new Color(RandX, RandY, RandZ);
Color SOFTBLUE = new Color(0,191,255);
		g.setColor(SOFTBLUE);
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
