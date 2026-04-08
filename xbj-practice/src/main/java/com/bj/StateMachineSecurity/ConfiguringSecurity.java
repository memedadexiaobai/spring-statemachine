package com.bj.StateMachineSecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;

public class ConfiguringSecurity {

    /**
     * All generic configurations for security are done in SecurityConfigurer, which is obtained from StateMachineConfigurationConfigurer.
     * By default, security is disabled, even if Spring Security classes are present.
     * The following example shows how to enable security:
     *
     * If you absolutely need to, you can customize AccessDecisionManager for both events and transitions.
     * If you do not define decision managers or set them to null, default managers are created internally.
     */
    @Configuration
    @EnableStateMachine
    static class Config4 extends StateMachineConfigurerAdapter<String, String> {

        @Override
        public void configure(StateMachineConfigurationConfigurer<String, String> config)
                throws Exception {
//            config
//                    .withSecurity()
//                    .enabled(true)
//                    .transitionAccessDecisionManager(null)
//                    .eventAccessDecisionManager(null);
        }
    }



}
