package nl.daankoster.mathsolver.modules;

import nl.daankoster.mathsolver.MathSolver;
import nl.daankoster.mathsolver.SolverModule;
import nl.daankoster.mathsolver.data.type.InputDataMultiInt;

import java.util.ArrayList;
import java.util.List;

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
