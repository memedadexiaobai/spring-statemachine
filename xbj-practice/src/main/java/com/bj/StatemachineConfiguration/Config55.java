package com.bj.StatemachineConfiguration;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;

/**
 * State Action Error Handling
 */
@Configuration
@EnableStateMachine
public class Config55
		extends EnumStateMachineConfigurerAdapter<States, Events> {

	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states)
			throws Exception {
		states
			.withStates()
				.initial(States.S1)
				.stateEntry(States.S2, action(), errorAction())
				.stateDo(States.S2, action(), errorAction())
				.stateExit(States.S2, action(), errorAction())
				.state(States.S3);
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

