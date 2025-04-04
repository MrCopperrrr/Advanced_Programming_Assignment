public class Warrior extends Fighter {
    public Warrior(int baseHp, int wp) {
        super(baseHp, wp);
    }

    @Override
    public double getCombatScore() {
        int baseHp = getBaseHp();
        int wp = getWp();

        double score = (wp == 1) ? baseHp : baseHp / 10.0;

        if (Utility.isPrime(Battle.GROUND)) {
            score = baseHp * 2.0;//.0
        }
        return score;
    }
}
