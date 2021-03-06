import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static State qs, qF1, qF2;
    static State q0, q1, q2, q3, q4;
    static State qa, qb, qc, qe, qf;

    static List<State> currentStates = new LinkedList<>();
    static List<State> statesToAdd;

    static List<State> states = new ArrayList<>();
    static String[] inputList;

    static Map<String, State> statesMap = new LinkedHashMap<>();

    public static void main(String[] args) {

//        Inicializacja stanów i przejść
        createStatesRules();

//        Wyświetlenie tabeli przejść
        System.out.println("Tabela przejść:");
        for (State state : states) {
            System.out.print("Stan(" + state.stateName + "): ");
            for (String rule : state.ruleSet) {
                System.out.print(rule + " ");
            }
            System.out.print("\n");
        }

//        Wczytanie pliku wejściowego
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Michal\\Desktop\\input.txt"));
            String strCurrentLine = br.readLine();
            inputList = strCurrentLine.split("#");
        } catch (Exception ex) {
            System.out.println("Unable to read file.");
        }

        System.out.print("\n");
//        Wyświetlenie ciągów wejściowych
        for (String input : inputList) {
            System.out.println(input);
        }
        System.out.print("\n");

        for (String input : inputList) {  // każde wejście
            currentStates.add(qs);
            for (int i = 0; i < input.length(); i++) {  // każda cyfra/litera
                statesToAdd = new LinkedList<>();
                for (int j = 0; j < currentStates.size();  j++) {
                    if (currentStates.get(j).isActive) {
                        if (currentStates.get(j).getStateName().equals("qs")) {
                            System.out.println(currentStates.get(j).stateName + "(" + input.charAt(i) + ")" + " -> qs");
                        }
                        System.out.println(currentStates.get(j).stateName + "(" + input.charAt(i) + ")" + " -> " + currentStates.get(j).getNextState(input.charAt(i)));
                        if (!currentStates.get(j).getNextState(input.charAt(i)).equals("x")) {
                            if (!currentStates.get(j).getStateName().equals(currentStates.get(j).getNextState(input.charAt(i)))) {
                                statesToAdd.add(statesMap.get(currentStates.get(j).getNextState(input.charAt(i))));
                            }
                        } else {
                            currentStates.get(j).setActive(false);
                        }
                    }
                }
                currentStates.addAll(statesToAdd);
                System.out.println("");
            }


            if (currentStates.contains(qF1) && currentStates.contains(qF2)) {
                System.out.println("Automat akceptuje słowo: " + input + " (powtórzyła się cyfra i litera)");
            } else if (currentStates.contains(qF1)) {
                System.out.println("Automat akceptuje słowo: " + input + " (powtórzyła się cyfra)");
            } else if (currentStates.contains(qF2)) {
                System.out.println("Automat akceptuje słowo: " + input + " (powtórzyła się litera)");
            } else {
                System.out.println("Automat nie akceptuje słowa: " + input);
            }
            System.out.print("\n");
            currentStates = new LinkedList<>();

        }


    }

//     if (!currentStates.get(j).getStateName().equals("qF1") && !currentStates.get(j).getNextState(input.charAt(i)).equals("qF1"))

    public static boolean checkIfStateExist(List<State> currentStates, String stateName) {
        List<State> filtredList = currentStates.stream().filter(state -> state.getStateName().equals(stateName)).collect(Collectors.toList());
        if (filtredList.size() != 0)
            return false;
        else
            return true;
    }

    public static void createStatesRules() {
        qs = new State("qs", "0>q0;1>q1;2>q2;3>q3;4>q4;a>qa;b>qb;c>qc;e>qe;f>qf".split(";"));
        qF1 = new State("qF1", "0>qF1;1>qF1;2>qF1;3>qF1;4>qF1;a>qF1;b>qF1;c>qF1;e>qF1;f>qF1".split(";"));
        qF2 = new State("qF2", "0>qF2;1>qF2;2>qF2;3>qF2;4>qF2;a>qF2;b>qF2;c>qF2;e>qF2;f>qF2".split(";"));

        q0 = new State("q0", "0>qF1;1>x;2>x;3>x;4>x;a>x;b>x;c>x;e>x;f>x".split(";"));
        q1 = new State("q1", "0>x;1>qF1;2>x;3>x;4>x;a>x;b>x;c>x;e>x;f>x".split(";"));
        q2 = new State("q2", "0>x;1>x;2>qF1;3>x;4>x;a>x;b>x;c>x;e>x;f>x".split(";"));
        q3 = new State("q3", "0>x;1>x;2>x;3>qF1;4>x;a>x;b>x;c>x;e>x;f>x".split(";"));
        q4 = new State("q4", "0>x;1>x;2>x;3>x;4>qF1;a>x;b>x;c>x;e>x;f>x".split(";"));

        qa = new State("qa", "0>x;1>x;2>x;3>x;4>x;a>qF2;b>x;c>x;e>x;f>x".split(";"));
        qb = new State("qb", "0>x;1>x;2>x;3>x;4>x;a>x;b>qF2;c>x;e>x;f>x".split(";"));
        qc = new State("qc", "0>x;1>x;2>x;3>x;4>x;a>x;b>x;c>qF2;e>x;f>x".split(";"));
        qe = new State("qe", "0>x;1>x;2>x;3>x;4>x;a>x;b>x;c>x;e>qF2;f>x".split(";"));
        qf = new State("qf", "0>x;1>x;2>x;3>x;4>x;a>x;b>x;c>x;e>x;f>qF2".split(";"));

        states.add(qs);
        states.add(qF1);
        states.add(qF2);

        states.add(q0);
        states.add(q1);
        states.add(q2);
        states.add(q3);
        states.add(q4);

        states.add(qa);
        states.add(qb);
        states.add(qc);
        states.add(qe);
        states.add(qf);

        for (State state : states) {
            statesMap.put(state.stateName, state);
        }
    }
}
