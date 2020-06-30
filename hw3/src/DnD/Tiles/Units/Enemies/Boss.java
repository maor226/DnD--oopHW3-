package DnD.Tiles.Units.Enemies;

import DnD.Tiles.Board;
import DnD.Tiles.Point;
import DnD.Tiles.Units.HeroicUnit;
import DnD.Tiles.Units.Players.Player;

public class Boss extends Monster implements HeroicUnit {

    Integer combatTicks=0;
    Integer abilityFrequency ;

    public Boss(Character tile, Point position, String name, Integer healthPool, Integer attackPoints, Integer defencePoints, Integer experienceValue, Integer visionRange, Integer abilityFrequency) {
        super(tile, position, name, healthPool, attackPoints, defencePoints, experienceValue, visionRange);
        this.abilityFrequency = abilityFrequency;
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
