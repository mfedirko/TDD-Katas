import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PrimeFactors {
    private final int num;

    public PrimeFactors(int num) {
        this.num = num;
    }

    public List<Integer> getFactors() {
        List<Integer> res = new ArrayList<>();
        int n = num;
        for (int k = 2; k <= n; k++)
            for (; n % k == 0; n /= k)
                res.add(k);
        return res;
    }
}
