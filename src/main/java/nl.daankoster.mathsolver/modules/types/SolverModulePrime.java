package nl.daankoster.mathsolver.modules.types;

import nl.daankoster.mathsolver.modules.SolverModule;

import java.util.HashMap;
import java.util.Map;

public class SolverModulePrime implements SolverModule {


    @Override
    public Object solve(Object data) {

        if (data instanceof Integer) {
            return getPrimeFactors((Integer) data);
        }

        return null;
    }

    /**
     * @param number input integer to get prime factors from.
     * @return a list of prime factors.
     */
    public Map<Integer, Integer> getPrimeFactors(int number) {
        int absNumber = Math.abs(number);

        Map<Integer, Integer> primeFactorsMap = new HashMap<>();

        for (int factor = 2; factor <= absNumber; factor++) {
            while (absNumber % factor == 0) {
                Integer power = primeFactorsMap.get(factor);
                if (power == null) {
                    power = 0;
                }
                primeFactorsMap.put(factor, power + 1);
                absNumber /= factor;
            }
        }

        return primeFactorsMap;
    }


}
