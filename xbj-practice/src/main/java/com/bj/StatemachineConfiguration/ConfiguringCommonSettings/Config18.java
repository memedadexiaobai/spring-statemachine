package com.bj.StatemachineConfiguration.ConfiguringCommonSettings;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.ensemble.StateMachineEnsemble;

/**
 * You can use withDistributed() to configure DistributedStateMachine.
 * It lets you set a StateMachineEnsemble, which (if it exists) automatically wraps any created StateMachine with DistributedStateMachine and enables distributed mode.
 * The following example shows how to use it:
 */
@Configuration
@EnableStateMachine
public class Config18
		extends EnumStateMachineConfigurerAdapter<States, Events> {

	@Override
	public void configure(StateMachineConfigurationConfigurer<States, Events> config)
			throws Exception {
		config
			.withDistributed()
				.ensemble(stateMachineEnsemble());
	}

	@Bean
	public StateMachineEnsemble<States, Events> stateMachineEnsemble()
			throws Exception {
		// naturally not null but should return ensemble instance
		return null;
	}

}

