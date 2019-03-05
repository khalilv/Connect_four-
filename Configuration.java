import java.io.InputStreamReader;


public class Configuration {
	//thisisUpdated
	public int[][] board;
	public int[] available;
	boolean spaceLeft;
	
	public Configuration(){
		board = new int[7][6];
		available = new int[7];
		spaceLeft = true;
	}
	
	
	public static void main(String []args){
		InputStreamReader input2 = new InputStreamReader(System.in);
		System.out.println("Welcome to Connect Four. Get a sequence of four in a row and you win!");
		System.out.println("The computer has played first. You are player two");
		System.out.println(Game.play(input2));
	}
	
	public void print(){
		System.out.println("| 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
		System.out.println("+---+---+---+---+---+---+---+");
		for (int i = 0; i < 6; i++){
			System.out.print("|");
			for (int j = 0; j < 7; j++){
				if (board[j][5-i] == 0){
					System.out.print("   |");
				}
				else{
					System.out.print(" "+ board[j][5-i]+" |");
				}
			}
			System.out.println();
		}
	}
	
	public void addDisk (int index, int player){
		//add a disk to the board 
		if(this.available[index] < 6){
			for(int a = 0; a < 6; a++){
				if(this.board[index][a] == 0){
					this.board[index][a] = player; 
					this.available[index]++; 
					break; 
				}
			}
		}	
	}
	
	public void removeDisk(int index){
		//remove a disk from the board 
		for(int i = 5; i > -1; i--){
			if( this.board[index][i] != 0){
				this.board[index][i] = 0;
				this.available[index]--; 
				break; 
			}
		}
	}
	
	public boolean isWinning (int lastColumnPlayed, int player){
		//method to check if someone has won the game 
		
		//check down
		int count = 0; 
		int i = lastColumnPlayed; 
		int j = (this.available[lastColumnPlayed]-1); 
		while(j != -1 && this.board[i][j] == player){
			count++; 
			j--; 
		}
		if(count >= 4){
			return true; 
		}
		
		//check diagonally 
		count = 0; 
		i = lastColumnPlayed; 
		j = (this.available[lastColumnPlayed]-1); 
		while(j != -1 && i != -1 && this.board[i][j] == player){
			count++; 
			j--; 
			i--; 
		}
		if(count >= 4){
			return true; 
		}
		
		//check diagonally other way 
		count = 0; 
		i = lastColumnPlayed; 
		j = (this.available[lastColumnPlayed]-1); 
		while(j != -1 && i != 7 && this.board[i][j] == player){
			count++; 
			j--; 
			i++; 
		}
		if(count >= 4){
			return true; 
		}
		
		//check across 
		count = 0; 
		i = lastColumnPlayed; 
		j = (this.available[lastColumnPlayed]-1); 
		while(i != 7 && this.board[i][j] == player){
			count++;
			i++; 
		}
		if(count >= 4){
			return true; 
		}
		
		//check across other way 
		count = 0; 
		i = lastColumnPlayed; 
		j = (this.available[lastColumnPlayed]-1); 
		while(i != -1 && this.board[i][j] == player){
			count++;
			i--; 
		}
		if(count >= 4){
			return true; 
		}
		
		count = 0; 
		i = lastColumnPlayed; 
		j = (this.available[lastColumnPlayed]-1); 
		while(j != -1 && i != -1 && this.board[i][j] == player){
			count++; 
			j--; 
			i--; 
		}
		count--; 
		i = lastColumnPlayed; 
		j = (this.available[lastColumnPlayed]-1);
		while(j != 6 && i != 7 && this.board[i][j] == player){
			count++; 
			j++; 
			i++; 
		}
		if(count >= 4){
			return true; 
		}
		
		count = 0; 
		i = lastColumnPlayed; 
		j = (this.available[lastColumnPlayed]-1); 
		while(j != -1 && i != 7 && this.board[i][j] == player){
			count++; 
			j--; 
			i++; 
		}
		count--; 
		i = lastColumnPlayed; 
		j = (this.available[lastColumnPlayed]-1);
		while(j != 6 && i != -1 && this.board[i][j] == player){
			count++; 
			j++; 
			i--; 
		}
		if(count >= 4){
			return true; 
		}
		
		count = 0; 
		i = lastColumnPlayed; 
		j = (this.available[lastColumnPlayed]-1); 
		while(i != -1 && this.board[i][j] == player){
			count++;
			i--; 
		}
		count--; 
		i = lastColumnPlayed; 
		j = (this.available[lastColumnPlayed]-1);
		while(i != 7 && this.board[i][j] == player){
			count++;
			i++; 
		}
		if(count >= 4){
			return true; 
		}
		return false; 
	}
	
	public int canWinNextRound (int player){
		//method to check if the computer can win the next round 
		for(int i = 0; i < 7; i++){
			if(this.available[i] >= 6){
				continue; 
			}
			this.addDisk(i, player);
			if(this.isWinning(i, player)){
				
				this.removeDisk(i);
				return i; 
			}else{
				this.removeDisk(i);
				continue;
			}
		}
		return -1; 
	}
	
	public int canWinTwoTurns (int player){
		
		//method to check if the computer can win in the next two turns 
		for(int i = 0; i < 7; i++){
			if(this.available[i] > 5){
				continue; 
			}
			this.addDisk(i, player);
			int possibleWin = this.canWinNextRound(player);
			if(possibleWin != -1){
				this.addDisk(possibleWin, 4);
				int possibleWin2 = this.canWinNextRound(player); 
				if(possibleWin2 == -1){
					this.removeDisk(possibleWin);
					this.removeDisk(i);
					continue; 
				}else{
					this.removeDisk(possibleWin);
					this.removeDisk(i);
					return i; 
				}
			}
			this.removeDisk(i);
		}
		return -1; 
	}
	
	

	
	
	
	
}
