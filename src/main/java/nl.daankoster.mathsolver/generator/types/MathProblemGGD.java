package nl.daankoster.mathsolver.generator.types;

import nl.daankoster.mathsolver.MathSolver;
import nl.daankoster.mathsolver.data.type.AnswerDataMultiInt;
import nl.daankoster.mathsolver.data.type.InputDataMultiInt;
import nl.daankoster.mathsolver.generator.MathProblemGenerator;
import nl.daankoster.mathsolver.modules.types.SolverModuleGGD;

import java.util.Random;

public class MathProblemGGD implements MathProblemGenerator {


    @Override
    public Object generate() {
        Random random = new Random();
        SolverModuleGGD ggdSolver = (SolverModuleGGD) MathSolver.getSolverModule(SolverModuleGGD.class);
        int answer = 0;

        while (answer != 1){
            int value1 = random.nextInt(800);
            int value2 = random.nextInt(800);

            answer = (int) ggdSolver.solve(new InputDataMultiInt(value1, value2));

            if (answer == 1) answer = 0;
            if (answer > 1 && (answer != value1 && answer != value2)){
                return new AnswerDataMultiInt(value1, value2);
            }
        }
        return new AnswerDataMultiInt(answer, answer);
    }
}
