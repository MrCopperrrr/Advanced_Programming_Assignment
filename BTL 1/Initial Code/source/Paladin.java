public class Paladin extends Knight {
    public Paladin(int baseHp, int wp) {
        super(baseHp, wp);
    }

    @Override
    public double getCombatScore() {
        if (isFibonacci(getBaseHp())) {
            int n = getFibonacciIndex(getBaseHp());
            if (n > 2) return 1000 + n;
        }
        return getBaseHp() * 3.0;
    }

    private boolean isFibonacci(int num) {
        int a = 0, b = 1, c = 1;
        while (c < num) {
            a = b;
            b = c;
            c = a + b;
        }
        return c == num;
    }

    private int getFibonacciIndex(int num) {
        int a = 0, b = 1, c = 1, index = 2;
        while (c < num) {
            a = b;
            b = c;
            c = a + b;
            index++;
        }
        return (c == num) ? index : -1;
    }
}
