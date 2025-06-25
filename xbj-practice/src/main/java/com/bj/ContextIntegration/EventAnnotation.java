package com.bj.ContextIntegration;

import org.springframework.statemachine.annotation.OnEventNotAccepted;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * There is one event-related annotation. It is named @OnEventNotAccepted.
 * If you specify the event property, you can listen for a specific event not being accepted. If you do not specify an event,
 * you can list for any event not being accepted. The following example shows both ways to use the @OnEventNotAccepted annotation:
 */
public class EventAnnotation {

    @WithStateMachine
    public class Bean12 {

        @OnEventNotAccepted
        public void anyEventNotAccepted() {
        }

        @OnEventNotAccepted(event = "E1")
        public void e1EventNotAccepted() {
        }
    }



}
