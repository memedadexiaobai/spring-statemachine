package com.bj.StateMachineFactories;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;

/**
 * Actually creating a state machine by using @EnableStateMachine works through a factory,
 * so @EnableStateMachineFactory merely exposes that factory through its interface.
 * The following example uses @EnableStateMachineFactory:
 */
@Configuration
@EnableStateMachineFactory
public class Config6
		extends EnumStateMachineConfigurerAdapter<States, Events> {

	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states)
			throws Exception {
		states
			.withStates()
				.initial(States.S1)
				.end(States.SF)
				.states(EnumSet.allOf(States.class));
	}

	@Autowired
	StateMachineFactory<States, Events> factory;

	void method() {
		StateMachine<States,Events> stateMachine = factory.getStateMachine();
		stateMachine.startReactively().subscribe();
	}

	/**
	 * State Machine through a Builder
	 * 不依赖spring来进行构建
	 */
	StateMachine<String, String> buildMachine1() throws Exception {
		StateMachineBuilder.Builder<String, String> builder = StateMachineBuilder.builder();
		builder.configureStates()
				.withStates()
				.initial("S1")
				.end("SF")
				.states(new HashSet<>(Arrays.asList("S1", "S2", "S3", "S4")));
		return builder.build();
	}

	/**
	 * You need to understand when common configuration needs to be used with machines instantiated from a builder.
	 * You can use a configurer returned from a withConfiguration() to setup autoStart and BeanFactory.
	 * You can also use one to register a StateMachineListener.
	 * If a StateMachine instance returned from a builder is registered as a bean by using @Bean, BeanFactory is attached automatically.
	 * If you use instances outside of a spring application context, you must use these methods to set up the needed facilities.
	 * @return
	 * @throws Exception
	 */
	StateMachine<String, String> buildMachine2() throws Exception {
		StateMachineBuilder.Builder<String, String> builder = StateMachineBuilder.builder();
		builder.configureConfiguration()
				.withConfiguration()
				.autoStartup(false)
				.beanFactory(null)
				.listener(null);
		return builder.build();
	}



}