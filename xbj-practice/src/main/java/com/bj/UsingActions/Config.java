package com.bj.UsingActions;

import com.bj.enums.Events;
import com.bj.enums.States;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.action.SpelExpressionAction;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.expression.Expression;

/**
 * Actions are one of the most useful components that you can use to interact(互动) and collaborate with(词组：与..合作，交互) a state machine.
 * You can run actions in various places in a state machine and its states lifecycle
 * for example, entering or exiting states or during transitions. The following example shows how to use actions in a state machine:
 *
 *
 * 有个 Reactive Actions 的类似优化吧
 */
public class Config extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.SI)
                .state(States.S1, action1(), action2())
                .state(States.S2, action1(), action2())
                .state(States.S3, action1(), action3());
    }

    @Bean
    public Action<States, Events> action1() {
        return context -> {};
    }

    @Bean
    public BaseAction action2() {
        return new BaseAction();
    }

    @Bean
    public SpelAction action3() {
        ExpressionParser parser = new SpelExpressionParser();
        return new SpelAction(
                parser.parseExpression("stateMachine.sendEvent(T(org.springframework.statemachine.docs.Events).E1)"));
    }

     static class BaseAction implements Action<States, Events> {

        @Override
        public void execute(StateContext<States, Events> context) {
        }
    }

     static class SpelAction extends SpelExpressionAction<States, Events> {
        public SpelAction(Expression expression) {
            super(expression);
        }
    }

}