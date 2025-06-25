package com.bj.StatemachineConfiguration.ConfiguringCommonSettings;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.model.StateMachineModel;
import org.springframework.statemachine.config.model.verifier.StateMachineModelVerifier;

/**
 * The StateMachineModelVerifier interface is used internally to do some sanity checks for a state machine’s structure.
 * Its purpose is to fail fast early instead of letting common configuration errors into a state machine.
 * By default, a verifier is automatically enabled and the DefaultStateMachineModelVerifier implementation is used.
 *
 * With withVerifier(), you can disable verifier or set a custom one if needed. The following example shows how to do so:
 */
@Configuration
@EnableStateMachine
public class Config19
		extends EnumStateMachineConfigurerAdapter<States, Events> {

	@Override
	public void configure(StateMachineConfigurationConfigurer<States, Events> config)
			throws Exception {
		config
			.withVerifier()
				.enabled(true)
				.verifier(verifier());
	}

	@Bean
	public StateMachineModelVerifier<States, Events> verifier() {
		return new StateMachineModelVerifier<States, Events>() {

			@Override
			public void verify(StateMachineModel<States, Events> model) {
				// throw exception indicating malformed model
			}
		};
	}
}

