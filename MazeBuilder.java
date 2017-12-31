import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class MazeBuilder {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the number of Rows and Columns:");
		int row = sc.nextInt();
		int column = sc.nextInt();
		while(row <1 || column<1) {
			System.out.println("The number of rows and columns should be greater then 0");
			System.out.println("Please enter the number of Rows and Columns:");
			row = sc.nextInt();
			column = sc.nextInt();
		}
		DisjSets ds = new DisjSets( row*column );
		Random randomno = new Random();
		int count = 1, wallCount = 5, cell1 = 0, cell2 = 0, wall = 0;
		boolean [] barArray = new boolean[row*column];
		boolean [] underscoreArray = new boolean[row*column];
		Arrays.fill(barArray, true);
		Arrays.fill(underscoreArray, true);
		//		 1-> top
		//0-> left		2-> right
		//		 3-> down
		while(count < row*column) {
			cell1 = randomno.nextInt(row*column);
			wall = randomno.nextInt(wallCount);
			if(wall == 2) {
				if((cell1+1)%column == 0) {
					continue;
				}
				cell2 = cell1 +1;
			}else if(wall == 1) {
				if(cell1 < column) {
					continue;
				}
				cell2 = cell1-column;
			}else if(wall == 0) {
				if(cell1%column == 0) {
					continue;
				}
				cell2 = cell1 -1;
			}else{
				if(cell1 >= (row*column - column)) {
					continue;
				}
				cell2 = cell1 + column;
			}
			
			if(ds.find(cell1) != ds.find(cell2)) {
				ds.union(ds.find(cell1), ds.find(cell2));
				if(wall == 0 || wall == 2) {
					barArray[Math.max(cell1,cell2)] = false;
				}else {
					underscoreArray[Math.min(cell1,cell2)] = false;
				}
				count++;
			}
		}
		// Star printing the maze
		for(int i = 0; i < column; i++) {
			if(i == 0) {
				System.out.print("   ");
			}else {
				System.out.print("_ ");
			}				
		}
		System.out.println();
		for(int i = 0; i < row*column; i++) {
			if(barArray[i] == true && i != 0) {
				System.out.print("|");
			}else {
				System.out.print(" ");
			}
			if(underscoreArray[i] == true && i < (row*column - 1) ) {
				System.out.print("_");
			}else {
				System.out.print(" ");
			}			 
            if( i % column == column - 1  && i < (row*column - 1)) {
            	System.out.println("|");
            }
		}
		System.out.println();
		// End printing the maze		
	}
}
