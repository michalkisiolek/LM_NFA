public class State {

    public String stateName;
    public String[] ruleSet;

    public State(String stateName, String[] ruleSet) {
        this.stateName = stateName;
        this.ruleSet = ruleSet;
    }

    public String getNextState(char character) {
        for (String rule: ruleSet) {
            String[] transition = rule.split(">");
            if (transition[0].equals(String.valueOf(character))) {
                return transition[1];
            }
        }
        return "x";
    }
}
