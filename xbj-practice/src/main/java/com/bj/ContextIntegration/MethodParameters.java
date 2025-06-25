package com.bj.ContextIntegration;

import org.springframework.messaging.Message;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.EventHeader;
import org.springframework.statemachine.annotation.EventHeaders;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

import java.util.Map;

/**
 * @Author: xingbinjie
 * @Desc:
 * @Version: 0.0.1
 * @Date: 2025/6/25
 */
public class MethodParameters {

    /**
     * Effectively, all annotated methods are called by using Spring SPel expressions,
     * which are built dynamically during the process.
     * To make this work, these expressions needs to have a root object (against which they evaluate).
     * This root object is a StateContext.
     * We have also made some tweaks internally so that it is possible to access StateContext methods directly
     *   without going through the context handle.
     *
     * The simplest method parameter is a StateContext itself. The following example shows how to use it:
     */
    @WithStateMachine
    public class Bean3 {

        @OnTransition
        public void anyTransition(StateContext<String, String> stateContext) {
        }
    }

    /**
     * You can access the rest of the StateContext content. The number and order of the parameters does not matter.
     * The following example shows how to access the various parts of the StateContext content:
     */
    @WithStateMachine
    public class Bean4 {

        @OnTransition
        public void anyTransition(
                @EventHeaders Map<String, Object> headers,
                @EventHeader("myheader1") Object myheader1,
                @EventHeader(name = "myheader2", required = false) String myheader2,
                ExtendedState extendedState,
                StateMachine<String, String> stateMachine,
                Message<String> message,
                Exception e) {
        }
    }



}
