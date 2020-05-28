package Tiles;

import Tiles.Units.Enemies.Enemy;
import Tiles.Units.Players.Player;

import java.util.List;

public class Board {
    private Tile[][] tiles;
    private Player player;
    private List<Enemy> enemies;

    public Board(Tile[][] tiles,Player player,List<Enemy> enemies)
    {
        this.tiles=tiles;
        this.player =player;
        this.enemies = enemies;
    }

    public void GameTick() /**just a place saver**/
    {
        player.GameTick();
        for (Enemy e : enemies)
        {
            e.GameTick();
        }
    }
}
