package com.bj.StatemachineConfiguration.pseudoStates;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;

import java.util.EnumSet;

/**
 * Initial State
 */
@Configuration
@EnableStateMachine
public class Config11
		extends EnumStateMachineConfigurerAdapter<States, Events> {

	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states)
			throws Exception {
		states
			.withStates()
				.initial(States.S1, initialAction())
				.end(States.SF)
				.states(EnumSet.allOf(States.class));
	}

	@Bean
	public Action<States, Events> initialAction() {
		return context -> {
			// do something initially
		};
	}

}

