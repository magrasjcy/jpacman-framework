package nl.tudelft.jpacman.board;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import nl.tudelft.jpacman.level.CollisionMap;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.sprite.Sprite;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.PlayerFactory;
import  nl.tudelft.jpacman.game.GameFactory;
import nl.tudelft.jpacman.board.Direction;
import java.util.ArrayList;
import java.util.Map;
class CollisionTest {
//
	//           5 things you can meet in game:
	// you are   pellet   space    wall      ghost     pacman
	// pacman     eat     draw     invalid    die       n/a
	// ghost      draw    draw     invalid    turnaway  die
	   /**
     * The board for this level.
     */
    private final Board board = mock(Board.class);

    /**
     * The collision map.
     */
    private final CollisionMap collisions = mock(CollisionMap.class);
    @Test
	void test() {
		List<Ghost> ghosts=new ArrayList<Ghost>();

		CollisionMap collisionMap=collisions;
		PacManSprites pacmanSprites=new PacManSprites();
		Map<Direction, Sprite> pacmanSpriteStore;
		 
		
		
		PlayerFactory playerFactory=new PlayerFactory(pacmanSprites);
		Player player      =playerFactory.createPacMan();

		GameFactory gameFactory=new GameFactory(playerFactory);
				
		List<Square> startPositions=new ArrayList<Square>();
		Level level=new Level(board,  ghosts,startPositions,
        collisionMap);
		Game game =gameFactory.createSinglePlayerGame(level)	;	
		Direction direction= Direction.NORTH;
		//
		GhostFactory ghostfactory=new GhostFactory(pacmanSprites);
		ghosts.add(ghostfactory.createBlinky());
		ghosts.add(ghostfactory.createPinky());
		ghosts.add(ghostfactory.createInky());
		ghosts.add(ghostfactory.createClyde());
		
		game.start();
		Sprite sprites[]= {null,null,null,null};
		
		Sprite wall=pacmanSprites.getWallSprite();
		Sprite ground=pacmanSprites.getGroundSprite();
		Sprite pellet=pacmanSprites.getPelletSprite();
		for (int i=0;i<500;i++) {
		game.move(player, direction); 
		pacmanSpriteStore=
				pacmanSprites.getPacmanSprites();
		    sprites[0]=pacmanSpriteStore.get(direction.NORTH);
	     	sprites[1]=pacmanSpriteStore.get(direction.SOUTH);
		    sprites[2]=pacmanSpriteStore.get(direction.EAST);
		    sprites[3]=pacmanSpriteStore.get(direction.WEST);
		    for (Sprite idx :sprites) {
		    	assertEquals(idx,wall);
		    	assertEquals(idx,ground);
		    	assertEquals(idx,pellet);
		    }
		}
		
	}

}
