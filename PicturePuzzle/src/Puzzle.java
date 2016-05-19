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
public class NPRunner {

	public static void main(String[] args) {
		create();
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
		JPanel b = new ImagePanel();
		f.setSize(1920, 1029);
		f.setResizable(false);
		f.setContentPane(p);
		p.setBackground(Color.ORANGE);
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

	public NPPanel() {}
	// creates panel
	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(new Font("Arial", Font.PLAIN, 80));
		g.drawString("Number Puzzle", 700, 70);
		g.setFont(new Font("", Font.PLAIN, 25));
		g.drawString("", 720, 100);
	}
}

 class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel() {
       try {                
          image = ImageIO.read(new File("background.jpeg"));
       } catch (IOException ex) {
            // handle exception...
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }
}
