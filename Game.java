import java.io.*;


public class Game {
	//thisisUpdated
	public static int play(InputStreamReader input){
		BufferedReader keyboard = new BufferedReader(input);
		Configuration c = new Configuration();
		int columnPlayed = 3; int player;
		
		// first move for player 1 (played by computer) : in the middle of the grid
		c.addDisk(firstMovePlayer1(), 1);
		int nbTurn = 1;
		
		while (nbTurn < 42){ // maximum of turns allowed by the size of the grid
			player = nbTurn %2 + 1;
			if (player == 2){
				columnPlayed = getNextMove(keyboard, c, 2);
			}
			if (player == 1){
				columnPlayed = movePlayer1(columnPlayed, c);
			}
			//System.out.println(columnPlayed);
			c.addDisk(columnPlayed, player);
			if (c.isWinning(columnPlayed, player)){
				c.print();
				System.out.println("Congrats to player " + player + " !");
				return(player);
			}
			nbTurn++;
		}
		return -1;
	}
	
	public static int getNextMove(BufferedReader keyboard, Configuration c, int player){
		// ADD YOUR CODE HERE
		for(;;){
			c.print();
			System.out.println();
			System.out.println("Which column do you wish to play in?  ");
			String input;
			try {
				input = keyboard.readLine();
				int x = Integer.parseInt(input);
				if(x > -1 && x < 7){
					if(c.available[x] < 6){
						return x; 
					}else{
						System.out.println("this column is full");
					}
				}else{
					System.out.println("invalid input, please try again");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				continue; 
			}  catch (NumberFormatException e){
				System.out.println("invalid input, please try again");
				continue; 
			}
			
		}
		// DON'T FORGET TO CHANGE THE RETURN
	}
	
	public static int firstMovePlayer1 (){
		return 3;
	}
	
	public static int movePlayer1 (int columnPlayed2, Configuration c){
		// ADD YOUR CODE HERE
		int a = c.canWinNextRound(1);
		if(a != -1){
			return a; 
		}
		int b = c.canWinTwoTurns(1);
		if(b != -1){
			return b; 
		}
		if(c.board[columnPlayed2][5] == 0){
			return columnPlayed2; 
		}
		
		int d = columnPlayed2; 
		d--; 
		if(d > -1 && c.board[d][5] == 0 ){
			return d; 
		}
		d += 2; 
		if(d < 7 && c.board[d][5] == 0){
			return d; 
		}
		d -= 3; 
		if(d > -1 && c.board[d][5] == 0){
			return d; 
		}
		d += 4; 
		if(d < 7 && c.board[d][5] == 0 ){
			return d; 
		}
		d -= 5; 
		if(d > -1 && c.board[d][5] == 0 ){
			return d; 
		}
		d += 6; 
		if(d < 7 && c.board[d][5] == 0 ){
			return d; 
		}
		d -= 7; 
		if(d > -1 && c.board[d][5] == 0){
			return d; 
		}
		d += 8; 
		if(d < 7 && c.board[d][5] == 0 ){
			return d; 
		}
		d -= 9; 
		if(d > -1 && c.board[d][5] == 0 ){
			return d; 
		}
		d += 10; 
		if(d < 7 && c.board[d][5] == 0 ){
			return d; 
		}
		d -= 11; 
		if(d > -1 && c.board[d][5] == 0 ){
			return d; 
		}
		d += 12; 
		if(d < 7 && c.board[d][5] == 0 ){
			return d; 
		}
		d -= 13; 
		if(d > -1 && c.board[d][5] == 0 ){
			return d; 
		}
		d += 14; 
		if(d < 7 && c.board[d][5] == 0){
			return d; 
		}
		return 0; // DON'T FORGET TO CHANGE THE RETURN
	}
	
}

