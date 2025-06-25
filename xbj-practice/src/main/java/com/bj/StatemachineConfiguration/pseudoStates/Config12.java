package com.bj.StatemachineConfiguration.pseudoStates;

import com.bj.enums.Events;
import com.bj.enums.States3;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.config.configurers.StateConfigurer;

/**
 * State History
 */
@Configuration
@EnableStateMachine
public class Config12
		extends EnumStateMachineConfigurerAdapter<States3, Events> {

	@Override
	public void configure(StateMachineStateConfigurer<States3, Events> states)
			throws Exception {
		states
			.withStates()
				.initial(States3.S1)
				.state(States3.S2)
				.and()
				.withStates()
					.parent(States3.S2)
					.initial(States3.S2I)
					.state(States3.S21)
					.state(States3.S22)
					.history(States3.SH, StateConfigurer.History.SHALLOW);
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<States3, Events> transitions)
			throws Exception {
		transitions
			.withHistory()
				.source(States3.SH)
				.target(States3.S22);
	}

}

