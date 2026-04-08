package com.bj.PersistingStateMachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import reactor.core.publisher.Mono;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * We can show how to use a StateMachinePersister by following a snippets(一小段) from tests.
 * We start by creating two similar configurations (machine1 and machine2) for a state machine.
 * Note that we could build different machines for this demonstration in other ways but this way works for this case.
 * The following example configures the two state machines:
 */
public class UsingStateMachinePersister {

    @Configuration
    @EnableStateMachine(name = "machine1")
    static class Config1 extends Config {
    }

    @Configuration
    @EnableStateMachine(name = "machine2")
    static class Config2 extends Config {
    }

    static class Config extends StateMachineConfigurerAdapter<String, String> {

        @Override
        public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
            states
                    .withStates()
                    .initial("S1")
                    .state("S1")
                    .state("S2");
        }

        @Override
        public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
            transitions
                    .withExternal()
                    .source("S1")
                    .target("S2")
                    .event("E1");
        }
    }

    static class InMemoryStateMachinePersist implements StateMachinePersist<String, String, String> {

        private final HashMap<String, StateMachineContext<String, String>> contexts = new HashMap<>();

        @Override
        public void write(StateMachineContext<String, String> context, String contextObj) throws Exception {
            contexts.put(contextObj, context);
        }

        @Override
        public StateMachineContext<String, String> read(String contextObj) throws Exception {
            return contexts.get(contextObj);
        }
    }

    static ApplicationContext applicationContext;

    public static void main(String[] args) throws Exception {
        InMemoryStateMachinePersist stateMachinePersist = new InMemoryStateMachinePersist();
        StateMachinePersister<String, String, String> persister = new DefaultStateMachinePersister<>(stateMachinePersist);

        StateMachine<String, String> stateMachine1 = applicationContext.getBean("machine1", StateMachine.class);
        StateMachine<String, String> stateMachine2 = applicationContext.getBean("machine2", StateMachine.class);
        stateMachine1.startReactively().block();

        stateMachine1
                .sendEvent(Mono.just(MessageBuilder.withPayload("E1").build()))
                .blockLast();
//        assertThat(stateMachine1.getState().getIds()).containsExactly("S2");

        persister.persist(stateMachine1, "myid");
        persister.restore(stateMachine2, "myid");
//        assertThat(stateMachine2.getState().getIds()).containsExactly("S2");


    }


}
