package DnD.Tiles.Units.Enemies;

import DnD.Tiles.Board;
import DnD.Tiles.Point;
import DnD.Tiles.Units.HeroicUnit;
import DnD.Tiles.Units.Players.Player;

public class Boss extends Monster implements HeroicUnit {

    Integer combatTicks=0;
    Integer abilityFrequency = 5;

    public Boss(Character tile, Point position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defencePoints, Integer experienceValue, Integer visionRange) {
        super(tile, position, name, healthPool, healthAmount, attackPoints, defencePoints, experienceValue, visionRange);
    }

    @Override
    public void GameTick(Player p, Board board) {
        if (p != null) {
            if (combatTicks == abilityFrequency) {
                combatTicks = 0;
                CastAbility(board);
            }
            else {
                super.GameTick(p, board);
            }
        }
        else {
            super.GameTick(p, board);
        }
        combatTicks++;
    }

    @Override
    public void CastAbility(Board board) {
        Player player=board.getPlayer();
        player.Hit(attackPoints,getName());
        NotifyObserver(name+" casted his ability");
    }


}
