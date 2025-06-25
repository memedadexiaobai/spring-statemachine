package com.bj.StatemachineConfiguration;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.action.Actions;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

/**
 * Transition Action Error Handling
 */
@Configuration
@EnableStateMachine
public class Config53
		extends EnumStateMachineConfigurerAdapter<States, Events> {

	@Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
			throws Exception {
		transitions
			.withExternal()
				.source(States.S1)
				.target(States.S2)
				.event(Events.E1)
				.action(action(), errorAction())
				.action(Actions.errorCallingAction(action(), errorAction()));
	}

	@Bean
	public Action<States, Events> action() {
		return context -> {
			throw new RuntimeException("MyError");
		};
	}

	@Bean
	public Action<States, Events> errorAction() {
		return context -> {
			// RuntimeException("MyError") added to context
			Exception exception = context.getException();
			exception.getMessage();
		};
	}

}

