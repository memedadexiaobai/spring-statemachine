package com.bj.ListeningStateMachineEvents;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

/**
 * Using StateMachineListener
 * By using StateMachineListener, you can either extend it and implement all callback methods or use the StateMachineListenerAdapter class,
 * which contains stub method implementations and choose which ones to override.
 * The following example uses the latter approach:
 */
public class StateMachineEventListener
		extends StateMachineListenerAdapter<States, Events> {

	@Override
	public void stateChanged(State<States, Events> from, State<States, Events> to) {
	}

	@Override
	public void stateEntered(State<States, Events> state) {
	}

	@Override
	public void stateExited(State<States, Events> state) {
	}

	@Override
	public void transition(Transition<States, Events> transition) {
	}

	@Override
	public void transitionStarted(Transition<States, Events> transition) {
	}

	@Override
	public void transitionEnded(Transition<States, Events> transition) {
	}

	@Override
	public void stateMachineStarted(StateMachine<States, Events> stateMachine) {
	}

	@Override
	public void stateMachineStopped(StateMachine<States, Events> stateMachine) {
	}

	@Override
	public void eventNotAccepted(Message<Events> event) {
	}

	@Override
	public void extendedStateChanged(Object key, Object value) {
	}

	@Override
	public void stateMachineError(StateMachine<States, Events> stateMachine, Exception exception) {
	}

	@Override
	public void stateContext(StateContext<States, Events> stateContext) {
	}

	@Autowired
	StateMachine<States, Events> stateMachine;

	/**
	 * Once you have defined your own listener, you can registered it in a state machine by using the addStateListener method.
	 * It is a matter of(大约，左右) flavor(风味，特色) whether to hook it up within a spring configuration or do it manually at any time during the application life-cycle.
	 * The following example shows how to attach a listener:
	 * @return
	 */
	@Bean
	public StateMachineEventListener stateMachineEventListener() {
		StateMachineEventListener listener = new StateMachineEventListener();
		stateMachine.addStateListener(listener);
		return listener;
	}
}

