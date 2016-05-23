import java.util.Random;

/************
 * Siobhan Gannon
 * Period C
 * May 9, 2016
 * This is a sliding puzzle game and final project.
 * 
 */

public class Puzzle extends SimplePicture{
	private int size;
	private int[][] arr;

	public Puzzle(int s){//s for size (3x3, 4x4, ect.)
		size=s;
		arr = new int[size][size];
		makeNumbers();
	}

	public void setUp(){
		//Open drawing panel
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
}