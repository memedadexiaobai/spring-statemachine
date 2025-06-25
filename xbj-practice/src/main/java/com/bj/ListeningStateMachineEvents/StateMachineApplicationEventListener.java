package com.bj.ListeningStateMachineEvents;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.event.StateMachineEvent;

/**
 * Application Context Events
 * Application context events classes are OnTransitionStartEvent, OnTransitionEvent,
 * OnTransitionEndEvent, OnStateExitEvent, OnStateEntryEvent, OnStateChangedEvent,
 * OnStateMachineStart, OnStateMachineStop,
 * and others that extend the base event class, StateMachineEvent.
 *
 * These can be used as is with a Spring ApplicationListener.
 * StateMachine sends context events through StateMachineEventPublisher.
 * The default implementation is automatically created if a @Configuration class is annotated with @EnableStateMachine.
 * The following example gets a StateMachineApplicationEventListener from a bean defined in a @Configuration class:
 */
public class StateMachineApplicationEventListener
		implements ApplicationListener<StateMachineEvent> {

	@Override
	public void onApplicationEvent(StateMachineEvent event) {
	}

	@Configuration
	public class ListenerConfig {

		@Bean
		public StateMachineApplicationEventListener contextListener() {
			return new StateMachineApplicationEventListener();
		}
	}

	/**
	 * Context events are also automatically enabled by using @EnableStateMachine,
	 * with StateMachine used to build a machine and registered as a bean, as the following example shows:
	 */
	@Configuration
	@EnableStateMachine
	public class ManualBuilderConfig {

		@Bean
		public StateMachine<String, String> stateMachine() throws Exception {
			StateMachineBuilder.Builder<String, String> builder = StateMachineBuilder.builder();
			builder.configureStates()
					.withStates()
					.initial("S1")
					.state("S2");
			builder.configureTransitions()
					.withExternal()
					.source("S1")
					.target("S2")
					.event("E1");
			return builder.build();
		}
	}

}
