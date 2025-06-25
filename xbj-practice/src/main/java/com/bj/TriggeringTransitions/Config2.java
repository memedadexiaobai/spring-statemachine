package com.bj.TriggeringTransitions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

/**
 * Using TimerTrigger
 * Currently, there are two types of supported timers,
 * one that fires continuously(连续的) and one that fires once a source state is entered.
 * The following example shows how to use the triggers:
 */
@Configuration
@EnableStateMachine
public class Config2 extends StateMachineConfigurerAdapter<String, String> {

    @Override
    public void configure(StateMachineStateConfigurer<String, String> states)
            throws Exception {
        states
                .withStates()
                .initial("S1")
                .state("S2")
                .state("S3");
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source("S1").target("S2").event("E1")
                .and()
                .withExternal()
                .source("S1").target("S3").event("E2")
                .and()
                .withInternal()
                .source("S2")
                .action(timerAction())
                .timer(1000)
                .and()
                .withInternal()
                .source("S3")
                .action(timerAction())
                .timerOnce(1000);
    }

    @Bean
    public TimerAction timerAction() {
        return new TimerAction();
    }

	static class TimerAction implements Action<String, String> {

		@Override
		public void execute(StateContext<String, String> context) {
			// do something in every 1 sec
		}
	}

}
