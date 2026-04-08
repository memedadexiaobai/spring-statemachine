package com.bj.StateMachineSecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.security.SecurityRule;

public class SecuringTransitions {

    /**
     * You can define transition security globally, as the following example shows.
     */
    @Configuration
    @EnableStateMachine
    static class Config6 extends StateMachineConfigurerAdapter<String, String> {

        @Override
        public void configure(StateMachineConfigurationConfigurer<String, String> config)
                throws Exception {
            config
                    .withSecurity()
                    .enabled(true)
                    .transition("true")
                    .transition("ROLE_ANONYMOUS", SecurityRule.ComparisonType.ANY);
        }
    }

    /**
     * If security is defined in a transition itself, it override any globally set security. The following example shows how to do so:
     */
    @Configuration
    @EnableStateMachine
    static class Config2 extends StateMachineConfigurerAdapter<String, String> {

        @Override
        public void configure(StateMachineTransitionConfigurer<String, String> transitions)
                throws Exception {
            transitions
                    .withExternal()
                    .source("S0")
                    .target("S1")
                    .event("A")
                    .secured("ROLE_ANONYMOUS", SecurityRule.ComparisonType.ANY)
                    .secured("hasTarget('S1')");
        }
    }



}