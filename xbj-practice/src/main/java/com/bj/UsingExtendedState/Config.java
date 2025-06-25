package com.bj.UsingExtendedState;

import org.springframework.context.ApplicationListener;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.event.OnExtendedStateChanged;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;

public class Config {

    /**
     * StateMachine has a method called getExtendedState().
     * It returns an interface called ExtendedState, which gives access to extended state variables.
     * You can access these variables directly through a state machine or through StateContext during a callback from actions or transitions.
     * The following example shows how to do so:
     * @return
     */
    public Action<String, String> myVariableAction() {
        return context -> context.getExtendedState()
                .getVariables().put("mykey", "myvalue");
    }

    /**
     * If you need to get notified for extended state variable changes, you have two options:
     *  either use StateMachineListener or listen for extendedStateChanged(key, value) callbacks.
     *  The following example uses the extendedStateChanged method:
     */
    public class ExtendedStateVariableListener
            extends StateMachineListenerAdapter<String, String> {

        @Override
        public void extendedStateChanged(Object key, Object value) {
            // do something with changed variable
        }
    }

    /**
     * Alternatively, you can implement a Spring Application context listener for OnExtendedStateChanged.
     * As mentioned in Listening to State Machine Events, you can also listen all StateMachineEvent events.
     * The following example uses onApplicationEvent to listen for state changes:
     */
    public class ExtendedStateVariableEventListener
            implements ApplicationListener<OnExtendedStateChanged> {

        @Override
        public void onApplicationEvent(OnExtendedStateChanged event) {
            // do something with changed variable
        }
    }




}
