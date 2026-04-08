package com.bj.StateMachineSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

/**
 * There are no dedicated(定义) security definitions for actions in a state machine,
 * but you can secure actions by using a global method security from Spring Security.
 * This requires that an Action be defined as a proxied @Bean and its execute method be annotated with @Secured.
 * The following example shows how to do so:
 */
public class SecuringActions {

    @Configuration
    @EnableStateMachine
    static class Config3 extends StateMachineConfigurerAdapter<String, String> {

        @Override
        public void configure(StateMachineConfigurationConfigurer<String, String> config)
                throws Exception {
            config
                    .withSecurity()
                    .enabled(true);
        }

        @Override
        public void configure(StateMachineStateConfigurer<String, String> states)
                throws Exception {
            states
                    .withStates()
                    .initial("S0")
                    .state("S1");
        }

        @Override
        public void configure(StateMachineTransitionConfigurer<String, String> transitions)
                throws Exception {
            transitions
                    .withExternal()
                    .source("S0")
                    .target("S1")
                    .action(securedAction())
                    .event("A");
        }

        @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
        @Bean
        public Action<String, String> securedAction() {
            return new Action<String, String>() {

                @Secured("ROLE_ANONYMOUS")
                @Override
                public void execute(StateContext<String, String> context) {
                }
            };
        }

    }

    /**
     * Global method security needs to be enabled with Spring Security. The following example shows how to do so:
     */
    @Configuration
    public static class Config5 {

        @Bean
        public InMemoryUserDetailsManager userDetailsService() {
            UserDetails user = User.withDefaultPasswordEncoder()
                    .username("user")
                    .password("password")
                    .roles("USER")
                    .build();
            return new InMemoryUserDetailsManager(user);
        }
    }


}