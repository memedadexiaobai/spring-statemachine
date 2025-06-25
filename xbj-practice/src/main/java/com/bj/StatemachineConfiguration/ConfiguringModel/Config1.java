package com.bj.StatemachineConfiguration.ConfiguringModel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.model.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * StateMachineModelFactory is a hook that lets you configure a statemachine model without using a manual configuration.
 * Essentially, it is a third-party integration to integrate into a configuration model.
 * You can hook StateMachineModelFactory into a configuration model by using a StateMachineModelConfigurer.
 * The following example shows how to do so:
 */
@Configuration
@EnableStateMachine
public class Config1 extends StateMachineConfigurerAdapter<String, String> {

	@Override
	public void configure(StateMachineModelConfigurer<String, String> model) throws Exception {
		model
			.withModel()
				.factory(modelFactory());
	}

	@Bean
	public StateMachineModelFactory<String, String> modelFactory() {
		return new CustomStateMachineModelFactory();
	}

	public static class CustomStateMachineModelFactory implements StateMachineModelFactory<String, String> {

		@Override
		public StateMachineModel<String, String> build() {
			ConfigurationData<String, String> configurationData = new ConfigurationData<>();

			Collection<StateData<String, String>> stateData = new ArrayList<>();
			stateData.add(new StateData<>("S1", true));
			stateData.add(new StateData<>("S2"));
			StatesData<String, String> statesData = new StatesData<>(stateData);

			Collection<TransitionData<String, String>> transitionData = new ArrayList<>();
			transitionData.add(new TransitionData<>("S1", "S2", "E1"));
			TransitionsData<String, String> transitionsData = new TransitionsData<>(transitionData);

			StateMachineModel<String, String> stateMachineModel = new DefaultStateMachineModel<>(configurationData,
					statesData, transitionsData);
			return stateMachineModel;
		}

		@Override
		public StateMachineModel<String, String> build(String machineId) {
			return build();
		}
	}


}

