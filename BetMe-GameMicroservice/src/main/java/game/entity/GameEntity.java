package game.entity;


import game.model.GameState;
import game.service.impl.GameServiceImpl;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Entity
@Table(name = "recently_finished_games")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdDateAndTime;
    private LocalDateTime finishedDateAndTime;
    private String gameStatus;
    private int gameResult;


    @Enumerated(EnumType.STRING)
    private GameState gameState;

}
