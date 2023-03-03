package escape;

import escape.builder.EscapeGameInitializer;
import escape.builder.LocationInitializer;
import escape.builder.RuleDescriptor;
import escape.required.LocationType;
import escape.required.Rule;

import java.util.Objects;

public class Score {

    private final String player;
    private int score;
    EscapeGameInitializer gameInitializer;

    /**
     * Constructor for Score
     *
     * @param player          player
     * @param gameInitializer gameInitializer
     */
    public Score(String player, EscapeGameInitializer gameInitializer) {
        this.gameInitializer = gameInitializer;
        this.player = player;
    }

    /**
     * gets the turn limit for the game
     *
     * @return turn limit
     */

    public int getTurnLimit() {
        RuleDescriptor[] rules = gameInitializer.getRules();
        for (RuleDescriptor rule : rules) {
            if (rule.ruleId == Rule.RuleID.TURN_LIMIT) {
                return rule.ruleValue;
            }
        }
        return -1;
    }

    /**
     * accumulates score for each player
     *
     * @param from starting coordinate
     * @param to   ending coordinate
     */
    public void accumScore(CoordinateImpl from, CoordinateImpl to) {
        EscapeGameManagerImpl<CoordinateImpl> escapeGameManager = new EscapeGameManagerImpl<>(gameInitializer);


        LocationInitializer[] LI = gameInitializer.getLocationInitializers();
        for (LocationInitializer locationInitializer : LI) {
            if (locationInitializer.x == to.getRow() && locationInitializer.y == to.getColumn() && locationInitializer.locationType == LocationType.EXIT) {
                if (Objects.equals(gameInitializer.currPlayer, gameInitializer.opponentPlayer)) {
                    gameInitializer.opponentPlayerScore++;
                } else {
                    gameInitializer.originalPlayerScore++;
                }
            }
        }
    }


}
