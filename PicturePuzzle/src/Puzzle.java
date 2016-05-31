/************
 * Siobhan Gannon
 * Period C
 * May 9, 2016
 * This is a sliding puzzle game and final project.
 * 
 */

public class Puzzle extends SimplePicture{
	private final int size;
	private Pixel[][] pic = new Pixel[1000][1000];
	private Picture pict;

	public Puzzle(int s, int p){//s for size (3x3, 4x4, ect.) and p for picture option
		size=s;
		if(p==1)
			pict = new Picture("doge.jpg");
		else if(p==2)
			pict = new Picture("guac.jpg");
		else if(p==3){
			pict = new Picture("spongebob.jpg");
		}
		else if(p==4)
			pict = new Picture("morganfreeman.jpg");
		else
			pict = new Picture("illusion.jpg");

		for (int row = 0; row < 1000; row++) //Assign each position in pixel array to a pixel in the image
     		for (int col = 0; col < 1000; col++) 
      			pic[row][col] = new Pixel(pict,col,row);
	}
	
	//Ignore this
	public void mirrorHorizontalBotToTop() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel lowerPixel = null;
		int length = pixels.length;
		for (int col = 0; col < pixels[0].length; col++) {
			for (int row = 0; row < length / 2; row++) {
				topPixel = pixels[row][col];
				lowerPixel = pixels[length - 1 - row][col];
				topPixel.setColor(lowerPixel.getColor());
			}
		}
	}
	
	public Pixel[][] getPic(){
		return pic;
	}
	
	public Picture getPict(){
		return pict;
	}

	public void setUp(){
		//Open drawing panel
	}
}

/*********************************************************************************************************************************/
/*Richard Oldroyd
 * 5/16/16
 * Number Puzzle runner
 */
import java.util.*;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class NPRunner {

	public static void main(String[] args) {
		create();//
		intro();
	}
	public static void intro() {//introduces user to game and explains rules
		System.out.println("Welcome to Number Puzzle!"
				+ "\nCreated by: Richard Oldroyd and Siobahn Gannon"
				+ "\n-In this game, you will choose a difficulty level, and try to "
				+ "\n recreate the mixed up array of numbers. Try to get the lowest "
				+ "\n score possible. Type \"Ready\" to start the game!");
		Scanner console = new Scanner(System.in);
		String ready = console.next();
		if (ready.startsWith("R") || ready.startsWith("r")) {// If user typed "ready", game starts to initialize
		gameCreator(console);
		}
	}
	
	public static void gameCreator(Scanner console) {
			// user difficulty
			getD(console);
			// Puzzle();
			create();
		}

	private static void create() {//JFrame created
		JFrame f = new JFrame("Number Puzzle");
		JPanel p = new NPPanel();
		f.setSize(990, 1029);
		f.setResizable(false);//CHANGE TO false WHEN FINISHED
		f.setContentPane(p);
		Color SOFTBLUE = new Color(0,191,255);
		p.setBackground(SOFTBLUE);
		f.setVisible(true);
		//call Puzzle class here

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
	}

	public static void end(int moves, Scanner console) {
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
	
}

class NPPanel extends JPanel {//Panel class

	//public static final int X = 0;
	//public static final int Y = 0;
	public NPPanel() {}
	// creates panel
	public void paint(Graphics g) {
		super.paint(g);
		int x = 145;
		int y = 1000;
		Color DARKBLUE = new Color(100,149,237);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, x, y);
		g.fillRect(830, 0, x, y);
		sierp.main(null, g);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 100));
		g.drawString("Number Puzzle", 145, 80);
		g.setFont(new Font("", Font.BOLD, 25));
		g.drawString("", 720, 100);
		
	}
	
}
class sierp {
	
	public static final int X = 145;
	public static final int Y = 1000;

	public static void main(String[] args, Graphics g) {
		g.fillRect(X/3,Y/3,X/3,Y/3);
		squares(g,X,Y,0,0);
		squares(g,X,Y,830,0);
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
