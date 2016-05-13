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
