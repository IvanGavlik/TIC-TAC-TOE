import java.util.Arrays;

import com.example.model.Game;
import com.example.model.GameLevel;
import com.example.model.GameStatus;
import com.example.model.Player;
import com.example.service.ComputerService;
import com.example.service.ComputerServiceImpl;

public class TestMinMax {
	
	static ComputerService comp = new ComputerServiceImpl();
	
	public static void main(String[] args) {
		
		Game game = new Game();
		game.setGameId(1);
		game.setGameStatus(GameStatus.IN_PROGRES);
		game.setLevel(GameLevel.EASY);
		
		String[][] board = { { "O", "", "" }, { "", "", "" }, { "", "", "" } };
		game.initBoard(board);
		game.setBoard(board);
		
		game.setPlayerO(new Player("Ivan"));
		game.setPlayerX(new Player("computer"));
		game.setCurrentPlayer(game.getPlayerO());
		
		System.out.println("start: " + Arrays.deepToString(game.getBoard()));
		comp.playMove(game);
		
	}

}
