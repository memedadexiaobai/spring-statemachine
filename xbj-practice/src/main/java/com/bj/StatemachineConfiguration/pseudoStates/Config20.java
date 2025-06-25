package com.bj.StatemachineConfiguration.pseudoStates;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import java.util.EnumSet;

/**
 * Junction State
 */
@Configuration
@EnableStateMachine
public class Config20
		extends EnumStateMachineConfigurerAdapter<States, Events> {

	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states)
			throws Exception {
		states
			.withStates()
				.initial(States.SI)
				.junction(States.S1)
				.end(States.SF)
				.states(EnumSet.allOf(States.class));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
			throws Exception {
		transitions
			.withJunction()
				.source(States.S1)
				.first(States.S2, s2Guard())
				.then(States.S3, s3Guard())
				.last(States.S4);
	}

	@Bean
	public Guard<States, Events> s2Guard() {
		return context -> false;
	}

	@Bean
	public Guard<States, Events> s3Guard() {
		return context -> true;
	}

}

