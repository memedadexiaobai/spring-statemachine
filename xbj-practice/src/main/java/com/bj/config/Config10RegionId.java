package com.bj.config;

import com.bj.enums.Events;
import com.bj.enums.States2;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;

/**
 * Configuring Regions
 */
@Configuration
@EnableStateMachine
public class Config10RegionId
		extends EnumStateMachineConfigurerAdapter<States2, Events> {

	@Override
	public void configure(StateMachineStateConfigurer<States2, Events> states)
			throws Exception {
		states
			.withStates()
				.initial(States2.S1)
				.state(States2.S2)
				.and()
				.withStates()
					.parent(States2.S2)
					.region("R1")
					.initial(States2.S2I)
					.state(States2.S21)
					.end(States2.S2F)
					.and()
				.withStates()
					.parent(States2.S2)
					.region("R2")
					.initial(States2.S3I)
					.state(States2.S31)
					.end(States2.S3F);
	}

}

