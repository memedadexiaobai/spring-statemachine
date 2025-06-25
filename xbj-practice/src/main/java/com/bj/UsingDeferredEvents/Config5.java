package com.bj.UsingDeferredEvents;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
public class Config5 extends StateMachineConfigurerAdapter<String, String> {

	/**
	 * In the preceding example,
	 * the state machine has a state of READY, which indicates that the machine is ready to process events that would take it into a DEPLOY state,
	 * where the actual deployment would happen. After a deploy action has been run, the machine is returned back to the READY state.
	 * Sending multiple events in a READY state does not cause any trouble if the machine is using synchronous executors,
	 * because event sending would block between event calls. However, if the executor uses threads, other events may get lost,
	 * because the machine is no longer in a state where events can be processed.
	 * Thus, deferring some of these events lets the machine preserve them. The following example shows how to configure such an arrangement:
	 * @param states the {@link StateMachineStateConfigurer}
	 * @throws Exception
	 */
	@Override
	public void configure(StateMachineStateConfigurer<String, String> states)
			throws Exception {
		states
			.withStates()
				.initial("READY")
				.state("DEPLOYPREPARE", "DEPLOY")
				.state("DEPLOYEXECUTE", "DEPLOY");
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<String, String> transitions)
			throws Exception {
		transitions
			.withExternal()
				.source("READY").target("DEPLOYPREPARE")
				.event("DEPLOY")
				.and()
			.withExternal()
				.source("DEPLOYPREPARE").target("DEPLOYEXECUTE")
				.and()
			.withExternal()
				.source("DEPLOYEXECUTE").target("READY");
	}

}

