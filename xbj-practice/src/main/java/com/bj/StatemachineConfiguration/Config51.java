package com.bj.StatemachineConfiguration;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

/**
 * Configuring Actions
 */
@Configuration
@EnableStateMachine
public class Config51
		extends EnumStateMachineConfigurerAdapter<States, Events> {

	@Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
			throws Exception {
		transitions
			.withExternal()
				.source(States.S1)
				.target(States.S2)
				.event(Events.E1)
				.action(action());
	}

	@Bean
	public Action<States, Events> action() {
		return context -> {
			// do something
		};
	}

}

