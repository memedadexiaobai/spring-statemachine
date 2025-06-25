package com.bj.TriggeringTransitions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineEventResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Config {
    @Autowired
    StateMachine<String, String> stateMachine;

    /**
     * Using EventTrigger
     * EventTrigger is the most useful trigger, because it lets you directly interact with a state machine by sending events to it.
     * These events are also called signals.
     * You can add a trigger to a transition by associating a state with it during configuration.
     * The following example shows how to do so:
     */
    void signalMachine() {
        stateMachine
                .sendEvent(Mono.just(MessageBuilder.withPayload("E1").build()))
                .subscribe();

        Message<String> message = MessageBuilder
                .withPayload("E2")
                .setHeader("foo", "bar")
                .build();
        stateMachine.sendEvent(Mono.just(message)).subscribe();

        Message<String> message1 = MessageBuilder
                .withPayload("E1")
                .build();

        //Whether you send one event or multiple events, result is always a sequence of results.
        // This is so because in a presence multiple reqions, results will come back from multiple machines in those regions.
        // This is shown with method sendEventCollect which gives a list of results.
        // Method itself is a just a syntactic sugar collecting Flux as list. If there is just one region, this list contains one result.
        Mono<List<StateMachineEventResult<String, String>>> results =
                stateMachine.sendEventCollect(Mono.just(message1));
        results.subscribe();

        Message<String> message2 = MessageBuilder
                .withPayload("E2").build();

        Flux<StateMachineEventResult<String, String>> results2 =
                stateMachine.sendEvents(Flux.just(message1, message2));
        results2.subscribe();

    }


}
