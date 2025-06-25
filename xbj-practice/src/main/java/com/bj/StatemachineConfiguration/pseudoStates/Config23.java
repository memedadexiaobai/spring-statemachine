package com.bj.StatemachineConfiguration.pseudoStates;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class Config23
		extends EnumStateMachineConfigurerAdapter<States, Events> {

	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states)
			throws Exception {
		states
			.withStates()
				.initial(States.SI)
				.choice(States.S1)
				.end(States.SF)
				.states(EnumSet.allOf(States.class));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
			throws Exception {
		transitions
			.withExternal()
				.source(States.SI)
				.action(c -> {
						// action with SI-S1
					})
				.target(States.S1)
				.and()
			.withChoice()
				.source(States.S1)
				.first(States.S2, c -> {
						return true;
					})
				.last(States.S3,
						c -> {
						// action with S1-S3
					},
						c -> {
						// error callback for action S1-S3
					});
	}
}

