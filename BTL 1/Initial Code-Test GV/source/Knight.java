public class Knight extends Fighter {
    public Knight(int baseHp, int wp) {
        super(baseHp, wp);
    }

    @Override
    public double getCombatScore() {
        int baseHp = getBaseHp();
        int wp = getWp();

        double score = (wp == 1) ? (double)baseHp : (double)baseHp / 10.0;
        
        if (Utility.isSquare(Battle.GROUND)) {
            score = (double)baseHp * 2.0;
        }
        return Math.min(score,999);
    }
}
