package com.bj.ContextIntegration;

import org.springframework.statemachine.annotation.OnExtendedStateChanged;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * There is one extended state-related annotation.
 * It is named @OnExtendedStateChanged. You can also listen to changes only for specific key changes.
 * The following example shows how to use the @OnExtendedStateChanged, both with and without a key property:
 */
public class ExtendedStateAnnotation {
    @WithStateMachine
    public class Bean15 {

        @OnExtendedStateChanged
        public void anyStateChange() {
        }

        @OnExtendedStateChanged(key = "key1")
        public void key1Changed() {
        }
    }


}
