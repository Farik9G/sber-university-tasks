package calculator;

import java.util.ArrayList;
import java.util.List;

public class CalculatorImpl implements Calculator {

    @Override
    public List<Integer> fibonacci(int number) {
        if (number < 0 || number > 46) {
            throw new IllegalArgumentException("Число должно находиться в диапазоне [0:46]");
        }

        List<Integer> result = new ArrayList<>();

        if (number == 0) {
            result.add(number);
        } else {
            result.add(0);
            result.add(1);

            for (int i = 2; i <= number; i++) {
                result.add(result.get(i - 1) + result.get(i - 2));
            }
        }

        return result;
    }
}

