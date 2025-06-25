package com.bj.UsingScopes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

/**
 * Once you have scoped a state machine into session, autowiring it into a @Controller gives a new state machine instance per session.
 * Each state machine is then destroyed when HttpSession is invalidated.
 * The following example shows how to use a state machine in a controller:
 */
@Controller
public class StateMachineController {

	@Autowired
	StateMachine<String, String> stateMachine;

	@RequestMapping(path="/state", method= RequestMethod.POST)
	public HttpEntity<Void> setState(@RequestParam("event") String event) {
		stateMachine
			.sendEvent(Mono.just(MessageBuilder.withPayload(event).build()))
			.subscribe();
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(path="/state", method=RequestMethod.GET)
	@ResponseBody
	public String getState() {
		return stateMachine.getState().getId();
	}

}

