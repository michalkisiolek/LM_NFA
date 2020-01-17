public class State {

    public String stateName;
    public String[] ruleSet;
    public boolean isActive;

    public State(String stateName, String[] ruleSet) {
        this.stateName = stateName;
        this.ruleSet = ruleSet;
        isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getStateName() {
        return stateName;
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
