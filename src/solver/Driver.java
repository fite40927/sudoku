package solver;

import java.util.Scanner;

/*
530070000
600195000
098000060
800060003
400803001
700020006
060000280
000419005
000080079

7005010
630004590
20060007
460000000
9010600
49
300080050
78500061
50200800

3006
3500080
100640073
500000010
487000
80000009
430025007
50006200
700100000

800000000
3600000
70090200
50007000
45700
100030
1000068
8500010
90000400

6100008
70090020
300006900
600002300
80040010
4300009
9200004
50070080
800005100

800302007
40060090
5000600
100608005
30020010
400703006
6000800
20030060
500206001

7009002
30060070
600700400
700800200
80020090
3004001
2001006
70050030
400600500

10006000
730860
6020004
49005002
5902300
200300540
800090700
97083000
500090

*/

public class Driver {
	public static long start;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[][] myBoard = new int[9][9];
		boolean running = true;
		
		while(running) {
			System.out.println("Enter your unsolven sudoku board line by line, no spaces");
			System.out.println("If the space is empty, enter 0");
			System.out.println("ex. 002001006\n");
			
			for(int i = 0; i < 9; i++) {
				int x = scan.nextInt();
				int z = 100000000;
				for(int k = 0; k < 9; k++){
					myBoard[i][k] = x/z;
					x -= (int)(x/z) * z;
					z/=10;
				}
			}
			
			System.out.println("\nIs this your board? y/n");
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					System.out.print(myBoard[i][j] + " ");
				}
				System.out.println();
			}
			if(scan.next().equalsIgnoreCase("y")) {
				Sudoku board = new Sudoku(myBoard);
				if(!board.checkValidity()) {
					System.out.println("\ninvalid board");
					System.out.println("Run again? (y/n)");
					if(scan.next().equalsIgnoreCase("n")) running = false;
				}
				else {
					System.out.println("\nSolving your Sudoku board...");
					start = System.nanoTime();
					if(board.solve()) {
						long time = System.nanoTime() - start;
						System.out.println("\nSolved!");
						System.out.println(time/1000000000.00 + " seconds\n");
						for(int i = 0; i < 9; i++) {
							for(int j = 0; j < 9; j++) {
								System.out.print(board.gimme()[i][j] + " ");
							}
							System.out.println();
						}
						System.out.println("\nRun again? (y/n)");
						if(scan.next().equalsIgnoreCase("n")) running = false;
					}
					else System.out.println("\nerror");
				}
			}
			else {
				System.out.println("Try again? (y/n)");
				if(scan.next().equalsIgnoreCase("n")) running = false;
			}
			System.out.println();
		}
	}
}
