package solver;

//This program solves sudoku boards

public class Sudoku {
	public int[][] board;
	public Sudoku(int[][] givenBoard) {
		board = givenBoard;
	}
	public boolean solve() {		
		if(System.nanoTime() - Driver.start < 20000000000.0) {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					
					if(board[i][j] == 0) {
						for(int k = 1; k < 10; k++) {
							board[i][j] = k;
							if(checkValidity()) {
								if(solve()) return true;
								else board[i][j] = 0;
							}
							else board[i][j] = 0;
						}		
						return false;
					}
					
					
				}
			}
		
			return true;
		}
		else return false;
	}
	public boolean checkValidity() {
		boolean ret = true;
		//check horizontals
		
		for(int i = 0; i < 9; i++) {
			for(int k = 0; k < 8; k++)
				for(int j = k+1; j < 9; j++)
					if(!checkValidity(board[i][k], board[i][j])) return false;
		}
		
		//check verticals
		
		for(int j = 0; j < 9; j++) {
			for(int k = 0; k < 8; k++) {
				for(int i = k+1; i < 9; i++) {
					if(!checkValidity(board[k][j], board[i][j])) return false;
				}
			}
		}
		
		//check boxes
		
		
		for(int k = 3; k <= 9; k+=3) {
			for(int h = 3; h <= 9; h+=3) {
				int[] temp = new int[9];
				int idx = 0;
				for(int i = k-3; i < k; i++) {
					for(int j = h-3; j < h; j++) {
						temp[idx] = board[i][j];
						idx++;
					}
				}
				for(int a = 0; a < 8; a++) {
					for(int b = a+1; b < 9; b++) {
						if(!checkValidity(temp[a], temp[b])) return false;
					}
				}
			}
		}
		
		return ret;
	}
	public boolean checkValidity(int a, int b) {
		if(a != 0 && b != 0) {
			if (a == b) return false;
		}
		return true;
	}
	public int[][] gimme(){
		return board;
	}
}
