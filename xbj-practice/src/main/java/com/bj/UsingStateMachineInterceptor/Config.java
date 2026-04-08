package com.bj.UsingStateMachineInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptor;
import org.springframework.statemachine.transition.Transition;

/**
 * Instead of using a StateMachineListener interface,
 *   you can use a StateMachineInterceptor.
 * One conceptual(概念) difference is that you can use an interceptor to intercept and stop a current state change or change its transition logic.
 * Instead of implementing a full interface,
 *   you can use an adapter class called StateMachineInterceptorAdapter to override the default no-op methods.
 */
public class Config {
    @Autowired
    private StateMachine<String, String> stateMachine;

    /**
     * You can register an interceptor through StateMachineAccessor.
     */
    public void getStateMachineAccessor() {
        stateMachine.getStateMachineAccessor()
                .withRegion().addStateMachineInterceptor(new StateMachineInterceptor<String, String>() {

                    @Override
                    public Message<String> preEvent(Message<String> message, StateMachine<String, String> stateMachine) {
                        return message;
                    }

                    @Override
                    public StateContext<String, String> preTransition(StateContext<String, String> stateContext) {
                        return stateContext;
                    }

                    @Override
                    public void preStateChange(State<String, String> state, Message<String> message,
                                               Transition<String, String> transition, StateMachine<String, String> stateMachine,
                                               StateMachine<String, String> rootStateMachine) {
                    }

                    @Override
                    public StateContext<String, String> postTransition(StateContext<String, String> stateContext) {
                        return stateContext;
                    }

                    @Override
                    public void postStateChange(State<String, String> state, Message<String> message,
                                                Transition<String, String> transition, StateMachine<String, String> stateMachine,
                                                StateMachine<String, String> rootStateMachine) {
                    }

                    @Override
                    public Exception stateMachineError(StateMachine<String, String> stateMachine,
                                                       Exception exception) {
                        return exception;
                    }
                });


    }


}