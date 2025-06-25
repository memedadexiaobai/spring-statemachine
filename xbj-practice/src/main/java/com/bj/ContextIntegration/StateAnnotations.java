package com.bj.ContextIntegration;

import com.bj.enums.States;
import org.springframework.statemachine.annotation.OnStateChanged;
import org.springframework.statemachine.annotation.OnStateEntry;
import org.springframework.statemachine.annotation.OnStateExit;
import org.springframework.statemachine.annotation.WithStateMachine;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The following annotations for states are available: @OnStateChanged, @OnStateEntry, and @OnStateExit.
 * The following example shows how to use OnStateChanged annotation (the other two work the same way):
 */
public class StateAnnotations {

    @WithStateMachine
    public class Bean8 {

        @OnStateChanged
        public void anyStateChange() {
        }
    }

    @WithStateMachine
    public class Bean9 {

        @OnStateChanged(source = "S1", target = "S2")
        public void stateChangeFromS1toS2() {
        }

    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @OnStateChanged
    public @interface StatesOnStates {

        States[] source() default {};

        States[] target() default {};
    }

    @WithStateMachine
    public class Bean10 {

        @StatesOnStates(source = States.S1, target = States.S2)
        public void fromS1ToS2() {
        }
    }

    @WithStateMachine
    public class Bean11 {

        @OnStateEntry
        public void anyStateEntry() {
        }

        @OnStateExit
        public void anyStateExit() {
        }
    }




}
