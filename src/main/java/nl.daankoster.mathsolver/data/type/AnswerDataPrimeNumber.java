package nl.daankoster.mathsolver.data.type;

import nl.daankoster.mathsolver.data.AnswerData;

public class AnswerDataPrimeNumber extends AnswerData {

    int[] value;

    public AnswerDataPrimeNumber(int[] value){
        this.value = value;
    }

    @Override
    public Object getValue(){
        return value;
    }

}
