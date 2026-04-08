package com.bj.UsingDistributedStates;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.ensemble.StateMachineEnsemble;
import org.springframework.statemachine.zookeeper.ZookeeperStateMachineEnsemble;

/**
 * A distributed state machine is implemented through a DistributedStateMachine class
 *   that wraps an actual instance of a StateMachine.
 * DistributedStateMachine intercepts communication with a StateMachine instance and works with distributed state abstractions handled
 *   through the StateMachineEnsemble interface.
 * Depending on the actual implementation, you can also use the StateMachinePersist interface to serialize a StateMachineContext,
 * which contains enough information to reset a StateMachine.
 *
 * The following example shows how to configure a Zookeeper-based distributed state machine`: 目前只支持这一个实现
 *
 * Using ZookeeperStateMachineEnsemble
 *  ZookeeperStateMachineEnsemble itself needs two mandatory settings, an instance of curatorClient and a basePath.
 *  The client is a CuratorFramework, and the path is the root of a tree in a Zookeeper instance.
 *
 *  Optionally, you can set cleanState, which defaults to TRUE and clears existing data if no members exists in an ensemble.
 *  You can set it to FALSE if you want to preserve distributed state within application restarts.
 *
 *  Optionally, you can set the size of a logSize (defaults to 32) to a keep history of state changes.
 *  The value of this setting must be a power of two. 32 is generally a good default value.
 *  If a particular state machine is left behind by more than the size of the log, it is put into an error state and disconnected from the ensemble, indicating it has lost its history and its ability to fully reconstruct the synchronized status.
 */
@Configuration
@EnableStateMachine
public class Config
		extends StateMachineConfigurerAdapter<String, String> {

	@Override
	public void configure(StateMachineConfigurationConfigurer<String, String> config)
			throws Exception {
		config
			.withDistributed()
				.ensemble(stateMachineEnsemble())
				.and()
			.withConfiguration()
				.autoStartup(true);
	}

	@Override
	public void configure(StateMachineStateConfigurer<String, String> states)
			throws Exception {
		// config states
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<String, String> transitions)
			throws Exception {
		// config transitions
	}

	@Bean
	public StateMachineEnsemble<String, String> stateMachineEnsemble()
			throws Exception {
		return new ZookeeperStateMachineEnsemble<String, String>(curatorClient(), "/zkpath");
	}

	@Bean
	public CuratorFramework curatorClient()
			throws Exception {
		CuratorFramework client = CuratorFrameworkFactory
				.builder()
				.defaultData(new byte[0])
				.connectString("localhost:2181").build();
		client.start();
		return client;
	}

}

