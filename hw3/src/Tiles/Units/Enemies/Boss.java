package Tiles.Units.Enemies;

import Tiles.Board;
import Tiles.Point;
import Tiles.Units.HeroicUnit;
import Tiles.Units.Players.Player;

import javax.swing.text.Position;

public class Boss extends Monster implements HeroicUnit {

    Integer combatTicks=0;

    public Boss(Character tile, Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experienceValue, Integer visionRange) {
        super(tile, position, name, healthPool, healthAmount, attackPoints, defencePoints, experienceValue, visionRange);
    }

    @Override
    public void GameTick(Player p, Board board) {
        //todo
    }

    @Override
    public void CastAbility(Board board) {
        //todo
    }
}
