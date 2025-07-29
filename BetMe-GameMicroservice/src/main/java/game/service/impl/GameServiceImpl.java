package game.service.impl;

import game.entity.GameEntity;
import game.model.GameState;
import game.repository.GameRepository;
import game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService {

    private final Random random = new Random();
    private final GameRepository gameRepository;

    private GameEntity currentGame;
    private GameEntity recentlyFinishedGame;

    private GameState currentState = GameState.WAITING;
    private LocalDateTime stateStartTime;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Scheduled(fixedRate = 1000)
    public void gameLoop() {
        LocalDateTime now = LocalDateTime.now();

        switch (currentState) {
            case WAITING:
                currentGame = new GameEntity();
                currentGame.setGameStatus("ongoing");
                currentGame.setCreatedDateAndTime(now);
                currentGame.setGameState(GameState.ONGOING);
                currentGame.setGameResult(-1);
                currentGame=gameRepository.save(currentGame);
                stateStartTime = now;
                currentState = GameState.ONGOING;

                break;

            case ONGOING:
                if (stateStartTime.plusSeconds(30).isBefore(now)) {

                    currentGame.setGameStatus("finished");
                    currentGame.setFinishedDateAndTime(now);
                    currentGame.setGameResult(random.nextBoolean() ? 1 : 0);
                    currentGame.setGameState(GameState.BREAK_TIME);
                    recentlyFinishedGame = gameRepository.save(currentGame);
                    stateStartTime = now;
                    currentState = GameState.BREAK_TIME;
                }
                break;

            case BREAK_TIME:
                if (stateStartTime.plusSeconds(10).isBefore(now)) {
                    currentState = GameState.WAITING;
                    stateStartTime = now;
                }
                break;
        }
    }

    @Override
    public GameEntity getOngoingGame() {
        if(currentGame!=null) {
            return currentGame;
        }
        return null;
    }

    @Override
    public GameState cyrrentState() {
        return null;
    }
}
