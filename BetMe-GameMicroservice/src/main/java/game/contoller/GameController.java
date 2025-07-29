package game.contoller;

import game.entity.GameEntity;
import game.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/Game")
@CrossOrigin
public class GameController {

    private final GameService gameService;


    @GetMapping("/get/ongoingGame")
    public GameEntity getOngoingGame(){
      return  gameService.getOngoingGame();

    }
}
