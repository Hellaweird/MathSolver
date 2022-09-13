package nl.daankoster.mathsolver.modules.types;

import nl.daankoster.mathsolver.data.type.InputDataMultiInt;
import nl.daankoster.mathsolver.modules.SolverModule;

public class SolverModuleGGD implements SolverModule {

    @Override
    public Object solve(Object data) {

        if (data instanceof InputDataMultiInt){
            InputDataMultiInt multiInt = (InputDataMultiInt) data;

            return ggdByEuclidsAlgorithm(multiInt.val1, multiInt.val2);
        }

        return null;
    }

    /**
     * @param n1 first integer
     * @param n2 second integer
     * @return returns the greatest common divider.
     * Simple algorith for finding the requered value.
     */
    int ggdByEuclidsAlgorithm(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return ggdByEuclidsAlgorithm(n2, n1 % n2);
    }

}
