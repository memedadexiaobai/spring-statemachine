package com.bj.MonitoringStateMachine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.monitor.AbstractStateMachineMonitor;
import org.springframework.statemachine.monitor.StateMachineMonitor;
import org.springframework.statemachine.transition.Transition;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class TestStateMachineMonitor extends AbstractStateMachineMonitor<String, String> {

	@Override
	public void transition(StateMachine<String, String> stateMachine, Transition<String, String> transition,
						   long duration) {
	}

	@Override
	public void action(StateMachine<String, String> stateMachine,
					   Function<StateContext<String, String>, Mono<Void>> action, long duration) {
	}

	@Configuration
	@EnableStateMachine
	public class Config1 extends StateMachineConfigurerAdapter<String, String> {

		@Override
		public void configure(StateMachineConfigurationConfigurer<String, String> config)
				throws Exception {
			config
					.withMonitoring()
					.monitor(stateMachineMonitor());
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

		@Bean
		public StateMachineMonitor<String, String> stateMachineMonitor() {
			return new TestStateMachineMonitor();
		}

	}



}

