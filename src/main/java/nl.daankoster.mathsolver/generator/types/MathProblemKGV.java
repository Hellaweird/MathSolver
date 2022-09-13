package nl.daankoster.mathsolver.generator.types;

import nl.daankoster.mathsolver.MathSolver;
import nl.daankoster.mathsolver.data.type.AnswerDataMultiInt;
import nl.daankoster.mathsolver.data.type.InputDataMultiInt;
import nl.daankoster.mathsolver.generator.MathProblemGenerator;
import nl.daankoster.mathsolver.modules.SolverModuleGGD;
import nl.daankoster.mathsolver.modules.SolverModuleKGV;

import java.util.Random;

public class MathProblemKGV implements MathProblemGenerator {
    @Override
    public Object generate() {
        Random random = new Random();
        SolverModuleKGV kgvSolver = (SolverModuleKGV) MathSolver.getSolverModule(SolverModuleKGV.class);
        int answer = 0;

        while (true){
            int value1 = random.nextInt(800);
            int value2 = random.nextInt(800);

            answer = (int) kgvSolver.solve(new InputDataMultiInt(value1, value2));

            if (answer > 1 && answer < 2000  && (answer != value1 && answer != value2)){
                return new AnswerDataMultiInt(value1, value2);
            }
        }
    }
}
