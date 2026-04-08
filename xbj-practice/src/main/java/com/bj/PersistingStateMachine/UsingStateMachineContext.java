package com.bj.PersistingStateMachine;

/**
 * Using StateMachineContext
 * You cannot persist a StateMachine by using normal java serialization,
 *   as the object graph is too rich and contains too many dependencies on other Spring context classes.
 * StateMachineContext is a runtime representation of a state machine that you can use to restore an existing machine
 *   into a state represented by a particular StateMachineContext object.
 *
 * StateMachineContext contains two different ways to include information for a child context.
 *   These are generally used when a machine contains orthogonal regions.
 *   First, a context can have a list of child contexts that can be used as is if they exist.
 *   Second, you can include a list of references that are used if raw context children are not in place.
 *   These child references are really the only way to persist a machine where multiple parallel regions are running independently.
 */
public class UsingStateMachineContext {
}
