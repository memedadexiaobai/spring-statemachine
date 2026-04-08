package com.bj.StateMachineSecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.security.SecurityRule;

public class SecuringEvents {

    /**
     * Event security is defined on a global level by a SecurityConfigurer. The following example shows how to enable event security:
     */
    @Configuration
    @EnableStateMachine
    static class Config1 extends StateMachineConfigurerAdapter<String, String> {

        @Override
        public void configure(StateMachineConfigurationConfigurer<String, String> config)
                throws Exception {
            config
                    .withSecurity()
                    .enabled(true)
                    .event("true")
                    .event("ROLE_ANONYMOUS", SecurityRule.ComparisonType.ANY);
        }
    }


}