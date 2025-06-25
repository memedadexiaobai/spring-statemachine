package com.bj.ContextIntegration;

import org.springframework.statemachine.annotation.OnStateMachineError;
import org.springframework.statemachine.annotation.OnStateMachineStart;
import org.springframework.statemachine.annotation.OnStateMachineStop;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * The following annotations are available for a state machine: @OnStateMachineStart, @OnStateMachineStop, and @OnStateMachineError.
 */
public class StateMachineAnnotations {

    /**
     * During a state machine’s start and stop, lifecycle methods are called.
     * The following example shows how to use @OnStateMachineStart and @OnStateMachineStop to listen to these events:
     */
    @WithStateMachine
    public class Bean13 {

        @OnStateMachineStart
        public void onStateMachineStart() {
        }

        @OnStateMachineStop
        public void onStateMachineStop() {
        }
    }

    /**
     * If a state machine goes into an error with exception, @OnStateMachineStop annotation is called.
     * The following example shows how to use it:
     */
    @WithStateMachine
    public class Bean14 {

        @OnStateMachineError
        public void onStateMachineError() {
        }
    }



}
