import java.util.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PPRunner {

	public static void main(String[] args) {
		create();
		intro();
		}	
	public static void intro() {
		System.out.println("Welcome to Picture Puzzle!"
				+ "\nCreated by: Richard Oldroyd and Siobahn Gannon"
				+ "\n-In this game, you will choose from an array of pictures,"
				+ "\n choose a difficulty level, and try to recreate the"
				+ "\n picture when it is all mixed up. Try to get the lowest"
				+ "\n score possible. Type \"Ready\" to start the game!");
		Scanner console = new Scanner(System.in);
		String ready = console.next();
		if(ready.startsWith("R") || ready.startsWith("r")) {//If user typed "ready", game starts to initialize
		//user difficulty
		System.out.println("What is the difficulty you want?(Type the #):");
		System.out.println("		1. Easy(3x3 Grid)		2. Medium(4x4 Grid)		3. Hard(5x5 Grid)");
		System.out.print("User choice: ");
		int d = console.nextInt();
		checkD(d);
		//user picture choice
		System.out.println("What picture do you want?(Type the #):");
		System.out.println("		1. Spongebob		2. Dog		3. Food		4. Illusion		5. Morgan Freeman");
		System.out.print("User choice: ");
		int s = console.nextInt();
		checkS(s);
		//Puzzle();
		create();
		}
		
	}
	public static void create() {
		
		JFrame f = new JFrame("Picture Puzzle");
		JPanel p = new PPPanel();
		f.setSize(2000, 1600);
		f.setContentPane(p);
		p.setBackground(Color.ORANGE);
		f.setVisible(true);	
	}
	public static void checkD(int d) {
		
	}
	public static void checkS(int s) {
		
	}
}

class PPPanel extends JPanel {

	public PPPanel() {}	
	//creates panel
	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(new Font("Arial", Font.PLAIN, 80));
		g.drawString("Picture Puzzle", 700, 70);
		g.setFont(new Font("",Font.PLAIN, 25));
		g.drawString("", 720, 100);
	}
}
