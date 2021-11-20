package RomanNumerals;


public class RomanNumeral {

    private final int baseTen;
    private String str;

    public RomanNumeral(int baseTen) {
        if (baseTen < 0 || baseTen > 4000) throw new IllegalArgumentException("Range is 0 to 4000 inclusive");
        this.baseTen = baseTen;
    }
    private String buildString() {
        StringBuilder sb = new StringBuilder();
        int num = this.baseTen;
        for (NumeralSymbol sym : NumeralSymbol.values())
            for (; num >= sym.baseTen; num -= sym.baseTen)
                sb.append(sym.name());
        return sb.toString();
    }

    @Override
    public String toString() {
        if (this.str == null) {
            this.str = buildString();
        }
        return this.str;
    }

    private enum NumeralSymbol {
        M(1000),
        CM(900),
        D(500),
        CD(400),
        C(100),
        XC(90),
        L(50),
        XL(40),
        X(10),
        IX(9),
        V(5),
        IV(4),
        I(1);

        final int baseTen;
        NumeralSymbol(final int baseTen) {
            this.baseTen = baseTen;
        }
        boolean isSubstitution() {
            return name().length() > 1;
        }
    }
}
