public class Paladin extends Knight {
    public Paladin(int baseHp, int wp) {
        super(baseHp, wp);
    }

    @Override
    public double getCombatScore() {
        if (isFibonacci(getBaseHp())) {
            int n = getFibonacciIndex(getBaseHp());
            if (n > 2) return (double)1000 + n;
        }
        return (double)getBaseHp() * 3.0;
    }

    private boolean isFibonacci(int num) {
        if (num == 0) return true; 
        int a = 0, b = 1, c = 1;
        while (c < num) {
            a = b;
            b = c;
            c = a + b;
        }
        return c == num;
    }
    
    private int getFibonacciIndex(int num) {
        if (num == 0) return 1; 
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
