package com.bj.StatemachineConfiguration;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;

/**
 * Configuring Actions
 */
@Configuration
@EnableStateMachine
public class Config52
		extends EnumStateMachineConfigurerAdapter<States, Events> {

	/**
	 * We defined an action for the initial state, S1.
	 * We defined an entry action for state S1 and left the exit action empty.
	 * We defined an exit action for state S2 and left the entry action empty.
	 * We defined a single state action for state S2.
	 * We defined both entry and exit actions for state S3.
	 * Note that state S1 is used twice with initial() and state() functions.
	 * 	 You need to do this only if you want to define entry or exit actions with initial state.
	 * @param states the {@link StateMachineStateConfigurer}
	 * @throws Exception
	 */
	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states)
			throws Exception {
		states
			.withStates()
				.initial(States.S1, action())
				.state(States.S1, action(), null)
				.state(States.S2, null, action())
				.state(States.S2, action())
				.state(States.S3, action(), action());
	}

	@Bean
	public Action<States, Events> action() {
		return context -> {
			// do something
		};
	}

}

