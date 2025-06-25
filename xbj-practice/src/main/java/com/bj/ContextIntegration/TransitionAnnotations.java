package com.bj.ContextIntegration;

import com.bj.enums.States;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.annotation.EventHeaders;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

/**
 * The annotations for transitions are @OnTransition, @OnTransitionStart, and @OnTransitionEnd.
 */
public class TransitionAnnotations {

    /**
     * These annotations behave exactly the same. T
     * o show how they work, we show how @OnTransition is used.
     * Within this annotation, a property’s you can use source and target to qualify a transition.
     * If source and target are left empty, any transition is matched.
     * The following example shows how to use the @OnTransition annotation
     *   (remember that @OnTransitionStart and @OnTransitionEnd work the same way):
     */
    @WithStateMachine
    public class Bean5 {

        @OnTransition(source = "S1", target = "S2")
        public void fromS1ToS2() {
        }

        @OnTransition
        public void anyTransition() {
        }
    }

    /**
     * Additionally, you can access Event Headers and ExtendedState by adding the needed arguments to a method.
     * The method is then called automatically with these arguments.
     * The following example shows how to do so:
     */
    @WithStateMachine
    public class Bean6 {

        @StatesOnTransition(source = States.S1, target = States.S2)
        public void fromS1ToS2(@EventHeaders Map<String, Object> headers, ExtendedState extendedState) {
        }
    }

    /**
     * However, if you want to have a type-safe annotation, you can create a new annotation and use @OnTransition as a meta-annotation.
     * This user-level annotation can make references to actual states and events enumerations,
     * and the framework tries to match these in the same way. The following example shows how to do so:
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @OnTransition
    public @interface StatesOnTransition {

        States[] source() default {};

        States[] target() default {};
    }

    @WithStateMachine
    public class Bean7 {

        @StatesOnTransition(source = States.S1, target = States.S2)
        public void fromS1ToS2() {
        }
    }




}