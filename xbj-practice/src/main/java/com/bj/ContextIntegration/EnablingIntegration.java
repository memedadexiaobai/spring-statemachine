package com.bj.ContextIntegration;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnStateChanged;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

/**
 * You can enable all the features of @WithStateMachine by using the @EnableWithStateMachine annotation,
 * which imports the needed configuration into the Spring Application Context.
 * Both @EnableStateMachine and @EnableStateMachineFactory are already annotated with this annotation,
 * so there is no need to add it again. However, if a machine is built and configured without configuration adapters,
 * you must use @EnableWithStateMachine to use these features with @WithStateMachine. The following example shows how to do so:
 */
public class EnablingIntegration {
    public static StateMachine<String, String> buildMachine(BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<String, String> builder = StateMachineBuilder.builder();

        builder.configureConfiguration()
                .withConfiguration()
                .machineId("myMachineId")
                .beanFactory(beanFactory);

        builder.configureStates()
                .withStates()
                .initial("S1")
                .state("S2");

        builder.configureTransitions()
                .withExternal()
                .source("S1")
                .target("S2")
                .event("E1");

        return builder.build();
    }

    @WithStateMachine(id = "myMachineId")
    static class Bean17 {

        @OnStateChanged
        public void onStateChanged() {

        }
    }


}
