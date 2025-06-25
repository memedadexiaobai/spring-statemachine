package com.bj.ContextIntegration;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Context Integration
 */
public class ContextIntegration {

    /**
     * You can use the @WithStateMachine annotation to associate a state machine with an existing bean.
     * Then you can start adding supported annotations to the methods of that bean. The following example shows how to do so:
     */
    @WithStateMachine
    public class Bean1 {

        @OnTransition
        public void anyTransition() {
        }
    }

    /**
     * You can also attach any other state machine from an application context by using the annotation name field.
     * The following example shows how to do so:
     */
    @WithStateMachine(name = "myMachineBeanName")
    public class Bean2 {

        @OnTransition
        public void anyTransition() {
        }
    }

    /**
     * Sometimes, it is more convenient to use machine id, which is something you can set to better identify multiple instances.
     * This ID maps to the getId() method in the StateMachine interface.
     * The following example shows how to use it:
     */
    @WithStateMachine(id = "myMachineId")
    public class Bean16 {

        @OnTransition
        public void anyTransition() {
        }
    }

    /**
     * You can also use @WithStateMachine as a meta-annotation, as shown in the preceding example.
     * In this case, you could annotate your bean with WithMyBean. The following example shows how to do so:
     */
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @WithStateMachine(name = "myMachineBeanName")
    public @interface WithMyBean {
    }



}
