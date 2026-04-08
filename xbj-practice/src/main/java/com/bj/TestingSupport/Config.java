package com.bj.TestingSupport;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.test.StateMachineTestPlan;
import org.springframework.statemachine.test.StateMachineTestPlanBuilder;

import static org.hamcrest.Matchers.*;

public class Config {
    private static StateMachine<String, String> buildMachine() throws Exception {
        StateMachineBuilder.Builder<String, String> builder = StateMachineBuilder.builder();

        builder.configureConfiguration()
                .withConfiguration()
                .autoStartup(true);

        builder.configureStates()
                .withStates()
                .initial("SI")
                .state("S1");

        builder.configureTransitions()
                .withExternal()
                .source("SI").target("S1")
                .event("E1")
                .action(c -> {
                    c.getExtendedState().getVariables().put("key1", "value1");
                });

        return builder.build();
    }

    public static void main(String[] args) throws Exception {
        StateMachine<String, String> machine = buildMachine();
        StateMachineTestPlan<String, String> plan =
                StateMachineTestPlanBuilder.<String, String>builder()
                        .defaultAwaitTime(2)
                        .stateMachine(machine)
                        .step()
                        .expectStates("SI")
                        .and()
                        .step()
                        .sendEvent("E1")
                        .expectStateChanged(1)
                        .expectStates("S1")
                        .expectVariable("key1")
                        .expectVariable("key1", "value1")
                        .expectVariableWith(hasKey("key1"))
                        .expectVariableWith(hasValue("value1"))
                        .expectVariableWith(hasEntry("key1", "value1"))
                        .expectVariableWith(not(hasKey("key2")))
                        .and()
                        .build();
        plan.test();
    }

}
