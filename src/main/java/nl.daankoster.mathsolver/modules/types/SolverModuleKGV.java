package nl.daankoster.mathsolver.modules.types;

import nl.daankoster.mathsolver.MathSolver;
import nl.daankoster.mathsolver.data.type.InputDataMultiInt;
import nl.daankoster.mathsolver.modules.SolverModule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SolverModuleKGV implements SolverModule {

    @Override
    public Object solve(Object data) {

        if (data instanceof InputDataMultiInt){
            InputDataMultiInt multiInt = (InputDataMultiInt) data;
            return lcm(multiInt.val1, multiInt.val2);
        }

        return null;
    }

    /**
     * @param number1 first integer
     * @param number2 second integer
     * @return returns least common multiple.
     * https://www.baeldung.com/java-least-common-multiple
     */
    int lcm(int number1, int number2) {
        if(number1 == 0 || number2 == 0) {
            return 0;
        }

        Map<Integer, Integer> primeFactorsForNum1 = getPrimeFactors(number1);
        Map<Integer, Integer> primeFactorsForNum2 = getPrimeFactors(number2);

        Set<Integer> primeFactorsUnionSet = new HashSet<>(primeFactorsForNum1.keySet());
        primeFactorsUnionSet.addAll(primeFactorsForNum2.keySet());

        int lcm = 1;

        for (Integer primeFactor : primeFactorsUnionSet) {
            lcm *= Math.pow(primeFactor,
                    Math.max(primeFactorsForNum1.getOrDefault(primeFactor, 0),
                            primeFactorsForNum2.getOrDefault(primeFactor, 0)));
        }

        return lcm;
    }

    public Map<Integer, Integer> getPrimeFactors(int i){
        return (Map<Integer, Integer>) MathSolver.getSolverModule(SolverModulePrime.class).solve(i);
    }

}
