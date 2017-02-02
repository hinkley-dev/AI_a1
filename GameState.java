import java.util.Comparator;
import java.util.TreeSet;




class GameState
{
	GameState prev;
	byte[] state;


	GameState(GameState _prev)
	{
		prev = _prev;
		state = new byte[22];

	}

//just for debugging
	public void print(){
		for(int i = 0; i < 11; i++)
		System.out.print("(" + state[2 * i] + "," +
			state[2 * i + 1] + ") ");
		System.out.println();
	}

}
