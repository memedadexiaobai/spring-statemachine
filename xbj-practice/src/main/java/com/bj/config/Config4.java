package com.bj.config;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

/**
 * Configuring Guards
 */
@Configuration
@EnableStateMachine
public class Config4
		extends EnumStateMachineConfigurerAdapter<States, Events> {

	@Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
			throws Exception {
		transitions
			.withExternal()
				.source(States.S1).target(States.S2)
				.event(Events.E1)
				.guard(guard())
				.and()
			.withExternal()
				.source(States.S2).target(States.S3)
				.event(Events.E2)
				.guardExpression("true");

	}

	@Bean
	public Guard<States, Events> guard() {
		return context -> true;
	}

}

