package com.bj.UsingStateContext;

/**
 * You can use StateContext to get access to the following:
 *  The current Message or Event (or their MessageHeaders, if known).
 *  The state machine’s Extended State.
 *  The StateMachine itself.
 *  To possible state machine errors.
 *  To the current Transition, if applicable.
 *  The source state of the state machine.
 *  The target state of the state machine.
 *  The current Stage, as described in Stages.
 *
 * StateContext is passed into various components, such as Action and Guard.
 *
 * Stages
 * Stage is a representation(表现形式) of a stage on which a state machine is currently interacting with a user.
 * The currently available stages are EVENT_NOT_ACCEPTED, EXTENDED_STATE_CHANGED, STATE_CHANGED, STATE_ENTRY, STATE_EXIT,
 * STATEMACHINE_ERROR, STATEMACHINE_START, STATEMACHINE_STOP, TRANSITION, TRANSITION_START, and TRANSITION_END.
 * These states may look familiar, as they match how you can interact with listeners (as described in Listening to State Machine Events).
 */
public class Config {
}
