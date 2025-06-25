package com.bj.StatemachineConfiguration.ConfiguringCommonSettings;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.region.RegionExecutionPolicy;
import org.springframework.statemachine.transition.TransitionConflictPolicy;

/**
 * Configuring Common Settings
 *
 * You can set part of a common state machine configuration by using ConfigurationConfigurer.
 * With it you can set BeanFactory and an autostart flag for a state machine.
 * It also lets you register StateMachineListener instances, configure transition conflict policy and region execution policy.
 * The following example shows how to use ConfigurationConfigurer:
 */
@Configuration
@EnableStateMachine
public class Config17
		extends EnumStateMachineConfigurerAdapter<States, Events> {

	@Override
	public void configure(StateMachineConfigurationConfigurer<States, Events> config)
			throws Exception {
		config
			.withConfiguration()
				.autoStartup(true)
				.machineId("myMachineId")
				.beanFactory(new StaticListableBeanFactory())
				.listener(new StateMachineListenerAdapter<>())
				.transitionConflictPolicy(TransitionConflictPolicy.CHILD)
				.regionExecutionPolicy(RegionExecutionPolicy.PARALLEL);
	}

	// 根据machineId获取状态机
	public void getStateMachine() {
//		StateMachineFactory<String, String> factory = context.getBean(StateMachineFactory.class);
//		StateMachine<String, String> machine = factory.getStateMachine("mymachine");
	}

}

