package nl.daankoster.mathsolver.generator.types;

import nl.daankoster.mathsolver.MathSolver;
import nl.daankoster.mathsolver.data.type.AnswerDataMultiInt;
import nl.daankoster.mathsolver.data.type.InputDataMultiInt;
import nl.daankoster.mathsolver.generator.MathProblemGenerator;
import nl.daankoster.mathsolver.modules.SolverModuleGGD;
import nl.daankoster.mathsolver.modules.SolverModulePrime;

import java.util.HashMap;
import java.util.Random;

public class MathProblemPrime implements MathProblemGenerator {

    @Override
    public Object generate() {
        Random random = new Random();
        SolverModulePrime primeSolver = (SolverModulePrime) MathSolver.getSolverModule(SolverModulePrime.class);
        HashMap<Integer,Integer> answer = new HashMap<>();

        while (true){
            int value1 = random.nextInt(800);

            answer = (HashMap<Integer, Integer>) primeSolver.solve(value1);

            boolean isHigherThan10 = answer.keySet().stream().anyMatch((v) -> (v > 10));


            if (answer.size() > 2 && value1 < 1000 && !isHigherThan10){
                return value1;
            }
        }
    }
}
