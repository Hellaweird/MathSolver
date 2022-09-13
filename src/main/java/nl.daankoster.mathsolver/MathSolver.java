package nl.daankoster.mathsolver;


import nl.daankoster.mathsolver.data.type.AnswerDataMultiInt;
import nl.daankoster.mathsolver.data.type.InputDataMultiInt;
import nl.daankoster.mathsolver.generator.MathProblemGenerator;
import nl.daankoster.mathsolver.generator.types.MathProblemGGD;
import nl.daankoster.mathsolver.generator.types.MathProblemKGV;
import nl.daankoster.mathsolver.generator.types.MathProblemPrime;
import nl.daankoster.mathsolver.modules.SolverModule;
import nl.daankoster.mathsolver.modules.types.SolverModuleGGD;
import nl.daankoster.mathsolver.modules.types.SolverModuleKGV;
import nl.daankoster.mathsolver.modules.types.SolverModulePrime;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MathSolver extends Frame {

    public static List<SolverModule> modules = new ArrayList<>();
    public static List<MathProblemGenerator> problemGenerators = new ArrayList<>();

    public MathSolver(){

        Button b = new Button("Maak een nieuwe opdracht.");
        Button b2 = new Button("Toon het antwoord.");
        Choice choice = new Choice();
        choice.add("GGD Generator");
        choice.add("KGV Generator");
        choice.add("Prime Generator");



        Label label = new Label("Vraag:");
        Label label2 = new Label("Antwoord:");
        Label label3 = new Label("Vraag gekopieerd naar clipbord");
        label2.hide();
        label3.hide();
        choice.setBounds(20, 60, 400, 50);
        label.setBounds(20,100,400,50);
        label2.setBounds(20,200,200,50);
        label3.setBounds(20,160,200,20);
        b.setBounds(20,250,200,50);
        b2.setBounds(220,250,200,50);

        b.addActionListener((e) -> {
            switch (choice.getSelectedItem().toLowerCase(Locale.ROOT)){
                case "ggd generator":
                    AnswerDataMultiInt outputData1 = (AnswerDataMultiInt) getGeneratorModule(MathProblemGGD.class).generate();
                    label.setText("Wat is de grootste gemeenschapelijke deler: " + outputData1.val1 + " " + outputData1.val2 );
                    SolverModuleGGD solverModuleGGD = (SolverModuleGGD) getSolverModule(SolverModuleGGD.class);

                    label2.setText("Antwoord: " + solverModuleGGD.solve(new InputDataMultiInt(outputData1.val1, outputData1.val2)));
                    break;
                case "kgv generator":
                    AnswerDataMultiInt outputData2 = (AnswerDataMultiInt) getGeneratorModule(MathProblemKGV.class).generate();
                    label.setText("Wat is de kleinste gemeenschapelijke veelvoud: " + outputData2.val1 + " " + outputData2.val2 );
                    SolverModuleKGV solverModuleKGV = (SolverModuleKGV) getSolverModule(SolverModuleKGV.class);

                    label2.setText("Antwoord: " + solverModuleKGV.solve(new InputDataMultiInt(outputData2.val1, outputData2.val2)));
                    break;
                case "prime generator":
                    int outputData3 = (int) getGeneratorModule(MathProblemPrime.class).generate();
                    label.setText("Wat zijn de priem getallen van dit getal: " + outputData3  );
                    SolverModulePrime solverModulePrime = (SolverModulePrime) getSolverModule(SolverModulePrime.class);
                    label2.setText("Antwoord: " + ((HashMap<Integer, Integer>)solverModulePrime.solve(outputData3)).keySet());
                    break;


            }
            label2.hide();
            label3.show();
            StringSelection stringSelection = new StringSelection(label.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

        });


        b2.addActionListener((e) -> {
            label2.show();
        });


        add(choice);
        add(label);
        add(label2);
        add(b);
        add(b2);
        add(label3);

        setSize(500,500);

        setTitle("Math problem generator");

        setLayout(null);

        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        modules.add(new SolverModulePrime());
        modules.add(new SolverModuleGGD());
        modules.add(new SolverModuleKGV());
        problemGenerators.add(new MathProblemGGD());
        problemGenerators.add(new MathProblemKGV());
        problemGenerators.add(new MathProblemPrime());
        new MathSolver();
    }


    public static SolverModule getSolverModule(Class clazz){
        return modules.stream().filter(sm -> sm.getClass()
                .getSimpleName()
                .equalsIgnoreCase(clazz.getSimpleName()))
                .findFirst().orElse(null);
    }

    public static MathProblemGenerator getGeneratorModule(Class clazz){
        return problemGenerators.stream().filter(sm -> sm.getClass()
                        .getSimpleName()
                        .equalsIgnoreCase(clazz.getSimpleName()))
                .findFirst().orElse(null);
    }
}
