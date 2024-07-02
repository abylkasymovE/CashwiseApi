package steps;

import io.cucumber.java.Scenario;

public class Hooks {
    public static void setUp(Scenario scenario){
        System.out.println("STARTING SCENARIO: ");
    }
}
