package com.bj.UsingGuards;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.action.SpelExpressionAction;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

/**
 * SpEL Expressions with Guards
 * You can also use a SpEL expression as a replacement for a full Guard implementation. The only requirement is that the expression needs to return a Boolean value to satisfy the Guard implementation.
 * This can be demonstrated with a guardExpression() function that takes an expression as an argument
 *
 * Reactive Guards
 */
public class Config extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.SI).target(States.S1)
                .event(Events.E1)
                .guard(guard1())
                .and()
                .withExternal()
                .source(States.S1).target(States.S2)
                .event(Events.E1)
                .guard(guard2())
                .and()
                .withExternal()
                .source(States.S2).target(States.S3)
                .event(Events.E2)
                .guardExpression("extendedState.variables.get('myvar')");
    }

    @Bean
    public Guard<States, Events> guard1() {
        return context -> true;
    }

    @Bean
    public BaseGuard guard2() {
        return new BaseGuard();
    }

    public class BaseGuard implements Guard<States, Events> {

        @Override
        public boolean evaluate(StateContext<States, Events> context) {
            return false;
        }
    }



}