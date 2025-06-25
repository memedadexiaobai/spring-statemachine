package com.bj.UsingScopes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

/**
 * Support for scopes in a state machine is very limited, but you can enable session scope by using a normal Spring @Scope annotation in one of two ways:
 *
 * If the state machine is built manually by using a builder and returned into the context as a @Bean.
 *
 * Through a configuration adapter.
 *
 * Both of these need @Scope to be present, with scopeName set to session and proxyMode set to ScopedProxyMode.TARGET_CLASS. The following examples show both use cases:
 */
@Configuration
public class Config3 {

	@Bean
	@Scope(scopeName="session", proxyMode= ScopedProxyMode.TARGET_CLASS)
	StateMachine<String, String> stateMachine() throws Exception {
		StateMachineBuilder.Builder<String, String> builder = StateMachineBuilder.builder();
		builder.configureConfiguration()
			.withConfiguration()
				.autoStartup(true);
		builder.configureStates()
			.withStates()
				.initial("S1")
				.state("S2");
		builder.configureTransitions()
			.withExternal()
				.source("S1")
				.target("S2")
				.event("E1");
		StateMachine<String, String> stateMachine = builder.build();
		return stateMachine;
	}

	@Configuration
	@EnableStateMachine
	@Scope(scopeName="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public static class Config4 extends StateMachineConfigurerAdapter<String, String> {

		@Override
		public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
			config
					.withConfiguration()
					.autoStartup(true);
		}

		@Override
		public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
			states
					.withStates()
					.initial("S1")
					.state("S2");
		}

		@Override
		public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
			transitions
					.withExternal()
					.source("S1")
					.target("S2")
					.event("E1");
		}

	}



}

