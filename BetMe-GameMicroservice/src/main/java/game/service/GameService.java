package game.service;

import game.entity.GameEntity;
import game.model.GameState;

public interface GameService {

        public GameEntity getOngoingGame();
        public GameState cyrrentState();
}
