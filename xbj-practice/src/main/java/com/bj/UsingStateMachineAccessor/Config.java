package com.bj.UsingStateMachineAccessor;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;

/**
 * 修改状态机
 */
public class Config {

    @Autowired
    private StateMachine<String, String> stateMachine;

    /**
     * The doWithAllRegions method gives access to all Region instances in a state machine. The following example shows how to use it:
     */
    public void doWithAllRegions() {
        stateMachine.getStateMachineAccessor().doWithAllRegions(function -> function.setRelay(stateMachine));

        stateMachine.getStateMachineAccessor()
                .doWithAllRegions(access -> access.setRelay(stateMachine));
    }

    /**
     * The doWithRegion method gives access to single Region instance in a state machine. The following example shows how to use it:
     */
    public void doWithRegion() {
        stateMachine.getStateMachineAccessor().doWithRegion(function -> function.setRelay(stateMachine));

        stateMachine.getStateMachineAccessor()
                .doWithRegion(access -> access.setRelay(stateMachine));
    }

    /**
     * The withAllRegions method gives access to all of the Region instances in a state machine. The following example shows how to use it:
     */
    public void withAllRegions() {
        for (StateMachineAccess<String, String> access : stateMachine.getStateMachineAccessor().withAllRegions()) {
            access.setRelay(stateMachine);
        }

        stateMachine.getStateMachineAccessor().withAllRegions()
                .stream().forEach(access -> access.setRelay(stateMachine));
    }

    /**
     * The withRegion method gives access to single Region instance in a state machine. The following example shows how to use it:
     */
    public void withRegion() {
        stateMachine.getStateMachineAccessor()
                .withRegion().setRelay(stateMachine);
    }

}
