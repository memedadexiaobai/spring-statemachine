package com.bj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineMessageHeaders;
import org.springframework.statemachine.action.StateDoActionPolicy;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * State Actions
 */
@Configuration
@EnableStateMachine
public class Config1 extends StateMachineConfigurerAdapter<String, String> {

	@Override
	public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
		config
			.withConfiguration()
				.stateDoActionPolicy(StateDoActionPolicy.IMMEDIATE_CANCEL)
				.stateDoActionPolicy(StateDoActionPolicy.TIMEOUT_CANCEL)
				.stateDoActionPolicyTimeout(10, TimeUnit.SECONDS);
	}

	@Override
	public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
		states
			.withStates()
				.initial("S1")
				.state("S2", context -> {})
				.state("S3");
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
		transitions
			.withExternal()
				.source("S1")
				.target("S2")
				.event("E1")
				.and()
			.withExternal()
				.source("S2")
				.target("S3")
				.event("E2");
	}

	@Autowired
	StateMachine<String, String> stateMachine;

	void sendEventUsingTimeout() {
		stateMachine
				.sendEvent(Mono.just(MessageBuilder
						.withPayload("E1")
						.setHeader(StateMachineMessageHeaders.HEADER_DO_ACTION_TIMEOUT, 5000)
						.build()))
				.subscribe();

	}


}

