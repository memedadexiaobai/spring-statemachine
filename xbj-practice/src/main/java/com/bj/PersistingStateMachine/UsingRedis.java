package com.bj.PersistingStateMachine;

/**
 * RepositoryStateMachinePersist (which implements StateMachinePersist) offers support for persisting a state machine into Redis.
 * The specific implementation is a RedisStateMachineContextRepository, which uses kryo serialization to persist a StateMachineContext into Redis.
 *
 * For StateMachinePersister, we have a Redis-related RedisStateMachinePersister implementation,
 * which takes an instance of a StateMachinePersist and uses String as its context object.
 */
public class UsingRedis {
}
