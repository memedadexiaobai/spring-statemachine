package com.bj.ListeningStateMachineEvents;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;

/**
 * You basically have two options: listen to Spring application context events or directly attach a listener to a state machine.
 * Both of these basically provide the same information.
 * One produces events as event classes, and the other produces callbacks via a listener interface.
 * Both of these have pros and cons(各有利弊), which we discuss later.
 */
public class Config {

    /**
     * Spring application context is not the fastest event bus out there,
     * so we advise giving some thought to the rate of events the state machine sends.
     * For better performance, it may be better to use the StateMachineListener interface.
     * For this specific reason, you can use the contextEvents flag with @EnableStateMachine
     *   and @EnableStateMachineFactory to disable Spring application context events,
     * as shown in the preceding section.
     * The following example shows how to disable Spring application context events:
     */
    @Configuration
    @EnableStateMachine(contextEvents = false)
    public class Config8
            extends EnumStateMachineConfigurerAdapter<States, Events> {
    }

    @Configuration
    @EnableStateMachineFactory(contextEvents = false)
    public class Config9
            extends EnumStateMachineConfigurerAdapter<States, Events> {
    }


}
