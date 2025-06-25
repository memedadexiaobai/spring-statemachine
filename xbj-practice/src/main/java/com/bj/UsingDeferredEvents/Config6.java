package com.bj.UsingDeferredEvents;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
public class Config6 extends StateMachineConfigurerAdapter<String, String> {

	/**
	 * In the preceding example, the state machine uses nested states instead of a flat state model,
	 * so the DEPLOY event can be deferred directly in a substate.
	 * It also shows the concept of deferring the DONE event in a sub-state that would then override the anonymous transition between the DEPLOY and DONE states if the state machine happens to be in a DEPLOYPREPARE state when the DONE event is dispatched.
	 * In the DEPLOYEXECUTE state when the DONE event is not deferred, this event would be handled in a super state.
	 * @param states the {@link StateMachineStateConfigurer}
	 * @throws Exception
	 */
	@Override
	public void configure(StateMachineStateConfigurer<String, String> states)
			throws Exception {
		states
			.withStates()
				.initial("READY")
				.state("DEPLOY", "DEPLOY")
				.state("DONE")
				.and()
				.withStates()
					.parent("DEPLOY")
					.initial("DEPLOYPREPARE")
					.state("DEPLOYPREPARE", "DONE")
					.state("DEPLOYEXECUTE");
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<String, String> transitions)
			throws Exception {
		transitions
			.withExternal()
				.source("READY").target("DEPLOY")
				.event("DEPLOY")
				.and()
			.withExternal()
				.source("DEPLOYPREPARE").target("DEPLOYEXECUTE")
				.and()
			.withExternal()
				.source("DEPLOYEXECUTE").target("READY")
				.and()
			.withExternal()
				.source("READY").target("DONE")
				.event("DONE")
				.and()
			.withExternal()
				.source("DEPLOY").target("DONE")
				.event("DONE");
	}
}

