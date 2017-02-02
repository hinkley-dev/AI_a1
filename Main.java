import java.util.Comparator;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;




class Main
{

	int deltaX, deltaY ,
			initialx1 ,initialy1 ,
			initialx2 , initialy2 ,
			initialx3 , initialy3 ,
			initialx4 , initialy4,
			id, move;

	byte axis;



	final int boardSize = 10;
	boolean[][] board;

	StateComparator comp;
	TreeSet<GameState> set;
	GameState initial;

  //move vairables
	Main(){
		board = new boolean[boardSize][boardSize];
		comp = new StateComparator();
		set = new TreeSet<GameState>(comp);
		initial = new GameState(null);
		drawBoard(initial);


	}





	public static void main(String args[])
	{
			Main main = new Main();
			main.run();

	}



	public void run(){
		bfs(initial);
	}

	public void drawBoard(GameState a){

		//set all to true originially
		for(int i = 0; i < boardSize; ++i){
			for(int j = 0; j < boardSize; ++j){
				board[i][j] = true;
			}
		}

		//set black pieces
		for(int i = 0; i < 10; i++) { board[i][0] =  board[i][9] = false; }
		for(int i = 1; i < 9; i++) { board[0][i] =  board[9][i] = false; }
		board[1][1] = board[1][2] = board[2][1] =
		board[7][1] = board[8][1]= board[8][2] =
		board[1][7]= board[1][8]=board[2][8] =
		board[8][7]= board[7][8]= board[8][8] =
		board[3][4]= board[4][4]= board[4][3] = false;

		for( byte id = 0; id < 11; id++){
			setInitialValues(id);
			int xValue = id*2;
			int yValue = id*2 + 1;
			//set current piece to false
			board[initialx1 + a.state[xValue]][initialy1 + a.state[yValue]] =
			board[initialx2 + a.state[xValue]][initialy2 + a.state[yValue]] =
			board[initialx3 + a.state[xValue]][initialy3 + a.state[yValue]] = false;

			if(id == 0 || id == 6 || id == 7){
				board[initialx4 + a.state[xValue]][initialy4 + a.state[yValue]] = false;
			}
		}


	}

	public void setInitialValues(int id){
		switch((id)){
			case 0:
				initialx1 = 1; initialy1 = 3;
				initialx2 = 2; initialy2 = 3;
				initialx3 = 1; initialy3 = 4;
				initialx4 = 2; initialy4 = 4;
				break;
			case 1:
				initialx1 = 1;  initialy1 = 5;
				initialx2 = 2;  initialy2 = 6;
				initialx3 = 1;  initialy3 = 6;
				 break;
			case 2:
				 initialx1 = 2;  initialy1 = 5;
				 initialx2 = 3;  initialy2 = 5;
				 initialx3 = 3;  initialy3 = 6;
				 break;
			case 3:
				 initialx1 = 3;  initialy1 = 7;
				 initialx2 = 3;  initialy2 = 8;
				 initialx3 = 4;  initialy3 = 8;
				 break;
			case 4:
				 initialx1 = 4;  initialy1 = 7;
				 initialx2 = 5;  initialy2 = 7;
				 initialx3 = 5;  initialy3 = 8;
				 break;
			case 5:
				 initialx1 = 6;  initialy1 = 7;
				 initialx2 = 6;  initialy2 = 8;
				 initialx3 = 7;  initialy3 = 7;
				 break;
			case 6:
				 initialx1 = 5;  initialy1 = 4;
				 initialx2 = 5;  initialy2 = 5;
				 initialx3 = 5;  initialy3 = 6;
				 initialx4 = 4;  initialy4 = 5;
				 break;
			case 7:
				 initialx1 = 6;  initialy1 = 4;
				 initialx2 = 6;  initialy2 = 5;
				 initialx3 = 6;  initialy3 = 6;
				 initialx4 = 7;  initialy4 = 5;
				 break;
			case 8:
				 initialx1 = 8;  initialy1 = 5;
				 initialx2 = 8;  initialy2 = 6;
				 initialx3 = 7;  initialy3 = 6;
				 break;
			case 9:
				 initialx1 = 6;  initialy1 = 2;
				 initialx2 = 6;  initialy2 = 3;
				 initialx3 = 5;  initialy3 = 3;
				 break;
			case 10:
				 initialx1 = 5;  initialy1 = 1;
				 initialx2 = 5;  initialy2 = 2;
				 initialx3 = 6;  initialy3 = 1;
				 break;
			default:
				System.out.println("Error with block id entry");
				break;
		}

	}

	public void bfs(GameState initial){

		GameState temp;
		Queue<GameState> q = new LinkedList();
		q.add(initial);
		set.add(initial);
		while(!q.isEmpty()){

			temp = q.poll();


			//is temp the final state
			if(isFinalState(temp)){
				writeResults(temp);
				return;

			}else{
				//if not add all possible moves to queue
				GameState a;

				for(int id = 0; id < 11; ++id){
					for(byte axis = 0; axis < 2; axis++){
						for(int move = -1; move < 2; move = move +2){

							drawBoard(temp);
							if(validMove(id, axis, move, temp)){
								a = new GameState(temp);
								a.state = temp.state.clone();
								a.state[id*2 + axis] += move;

								if(!set.contains(a)){
									set.add(a);
									q.add(a);
								}
							}
						}
					}
				}
			}



		}

	}

	public boolean isFinalState(GameState a){
		if (a.state[0] == 4 && a.state[1] == -2)
			return true;
		else
			return false;
	}

public void writeResults(GameState a){

	Stack<GameState> s = new Stack<>();
	GameState b;



	while(a != null){
		s.push(a);
		a = a.prev;
	}

	while(!s.empty()){
		b = s.pop();
		b.print();
	}

}


	public boolean validMove(int id, byte axis, int move, GameState a){

			boolean valid = false;
			setInitialValues(id);

			//set current values to true so pieces can move over themselves
			int xValue = id*2;
			int yValue = id*2 + 1;

			board[initialx1 + a.state[xValue]][initialy1 + a.state[yValue]] =
			board[initialx2 + a.state[xValue]][initialy2 + a.state[yValue]] =
			board[initialx3 + a.state[xValue]][initialy3 + a.state[yValue]] = true;

			if(id == 0 || id == 6 || id == 7){
				board[initialx4 + a.state[xValue]][initialy4 + a.state[yValue]] = true;
			}


			if(isSpaceOpen(id, axis, move, a))
				valid = true;

      return valid;

  }



	public boolean isSpaceOpen(int id, byte axis, int move, GameState a ){
		boolean open = false;
		int xValue = id*2;
		int yValue = id*2 + 1;

		//4 piece
		if(id == 0 || id == 6 || id == 7){
			if(axis == 0){
				if(board[initialx1 + a.state[xValue] + move][initialy1 + a.state[yValue]] == true &&
				board[initialx2 + a.state[xValue] + move][initialy2 + a.state[yValue]] == true &&
				board[initialx3 + a.state[xValue] + move][initialy3 + a.state[yValue]] == true &&
				board[initialx4 + a.state[xValue] + move][initialy4 + a.state[yValue]] == true)
					open = true;

			}else{
				if(board[initialx1 + a.state[xValue]][initialy1 + a.state[yValue]+ move] == true &&
				board[initialx2 + a.state[xValue]][initialy2 + a.state[yValue] + move] == true &&
				board[initialx3 + a.state[xValue]][initialy3 + a.state[yValue] + move] == true &&
				board[initialx4 + a.state[xValue]][initialy4 + a.state[yValue] + move] == true)
					open = true;
			}
		//3 piece
		}else{
			if(axis == 0){
				if(board[initialx1 + a.state[xValue] + move][initialy1 + a.state[yValue]] == true &&
				board[initialx2 + a.state[xValue] + move][initialy2 + a.state[yValue]] == true &&
				board[initialx3 + a.state[xValue] + move][initialy3 + a.state[yValue]])
					open = true;

			}else{
				if(board[initialx1 + a.state[xValue]][initialy1 + a.state[yValue]+ move] == true &&
				board[initialx2 + a.state[xValue]][initialy2 + a.state[yValue] + move] == true &&
				board[initialx3 + a.state[xValue]][initialy3 + a.state[yValue] + move] == true)
					open = true;
			}
		}


		return open;
	}






}
