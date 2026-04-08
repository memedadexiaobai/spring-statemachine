package com.bj.PersistingStateMachine;

/**
 * Using StateMachineRuntimePersister
 * StateMachineRuntimePersister is a simple extension to StateMachinePersist that
 *   adds an interface-level method to get StateMachineInterceptor associated with it.
 * This interceptor is then required to persist a machine during state changes without needing to stop and start a machine.
 *
 * Currently, there are implementations for this interface for the supported Spring Data Repositories.
 * These implementations are JpaPersistingStateMachineInterceptor, MongoDbPersistingStateMachineInterceptor,
 *   and RedisPersistingStateMachineInterceptor.
 */
public class UsingStateMachineRuntimePersister {
}
