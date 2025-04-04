public class Knight extends Fighter {
    public Knight(int baseHp, int wp) {
        super(baseHp, wp);
    }

    @Override
    public double getCombatScore() {
        int baseHp = getBaseHp();
        int wp = getWp();

        double score = (wp == 1) ? baseHp : baseHp / 10.0;
        
        if (Utility.isSquare(Battle.GROUND)) {
            score = baseHp * 2;
        }
        return score;
    }
}
