package com.bj.StateMachineErrorHandling;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineException;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.event.OnStateMachineError;
import org.springframework.statemachine.event.StateMachineEvent;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 *
 */
public class Config {

    StateMachine<String, String> stateMachine;

    /**
     * Normally, you can use StateMachineInterceptor to intercept errors and the following listing shows an example of it:
     * When errors are detected, the normal event notify mechanism is executed.
     * This lets you use either a StateMachineListener or a Spring Application context event listener.
     * For more about these, see Listening to State Machine Events.
     */
    void addInterceptor() {
        stateMachine.getStateMachineAccessor()
                .doWithRegion(function ->
                        function.addStateMachineInterceptor(new StateMachineInterceptorAdapter<>() {
                            @Override
                            public Exception stateMachineError(StateMachine<String, String> stateMachine,
                                                               Exception exception) {
                                return exception;
                            }
                        })
                );

    }

    public class ErrorStateMachineListener
            extends StateMachineListenerAdapter<String, String> {

        @Override
        public void stateMachineError(StateMachine<String, String> stateMachine, Exception exception) {
            // do something with error
        }
    }

    public class GenericApplicationEventListener
            implements ApplicationListener<StateMachineEvent> {

        @Override
        public void onApplicationEvent(StateMachineEvent event) {
            if (event instanceof OnStateMachineError) {
                // do something with error
            }
        }
    }

    public class ErrorApplicationEventListener
            implements ApplicationListener<OnStateMachineError> {

        @Override
        public void onApplicationEvent(OnStateMachineError event) {
            // do something with error
        }
    }


    /**
     * With a reactive api’s it is possible to get Action execution error back from a StateMachineEventResult.
     * Having simple machine which errors within action transitioning into state S1.
     */
    @Configuration
    @EnableStateMachine
    static class Config1 extends StateMachineConfigurerAdapter<String, String> {

        @Override
        public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
            states
                    .withStates()
                    .initial("SI")
                    .stateEntry("S1", (context) -> {
                        throw new RuntimeException("example error");
                    });
        }

        @Override
        public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
            transitions
                    .withExternal()
                    .source("SI")
                    .target("S1")
                    .event("E1");
        }
    }

    @Autowired
    private StateMachine<String, String> machine;

    @Test
    public void testActionEntryErrorWithEvent() throws Exception {
        StepVerifier.create(machine.startReactively()).verifyComplete();
//        assertThat(machine.getState().getIds()).containsExactlyInAnyOrder("SI");

        StepVerifier.create(machine.sendEvent(Mono.just(MessageBuilder.withPayload("E1").build())))
                .consumeNextWith(result -> {
                    StepVerifier.create(result.complete()).consumeErrorWith(e -> {
//                        assertThat(e).isInstanceOf(StateMachineException.class).cause().hasMessageContaining("example error");
                    }).verify();
                })
                .verifyComplete();

//        assertThat(machine.getState().getIds()).containsExactlyInAnyOrder("S1");
    }




}
