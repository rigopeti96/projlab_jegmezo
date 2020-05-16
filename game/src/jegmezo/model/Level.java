package jegmezo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level {
    private List<Tile> tiles = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private PolarBear polarBear;
    private GameState gamestate;
    private GameController gamecontroller;


    public Level(GameController gc){
        polarBear=new PolarBear(gc);
        for(int i=0;i<4;i++)
            players.add(new Eskimo(gc,i));
        LevelGenerator lg=new LevelGenerator(gc,4);
        tiles=lg.generate();
    }

    public List<Tile> getTiles(){return tiles;}

}
