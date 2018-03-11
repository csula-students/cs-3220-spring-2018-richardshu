import constants from './constants.js';
import Generator from './models/generator.js';
import Story from './models/story.js';

export default function reducer (state, action) {
	switch (action.type) {

		case constants.actions.EXAMPLE:
			state.example = action.payload;
			return state;

		case constants.actions.BUTTON_CLICK:
			state.counter++;
			return state;

		case constants.actions.BUY_GENERATOR:
			for (var i = 0; i < state.generators.length; i++) {
				const generator = new Generator(state.generators[i]);
				if (generator.name === action.payload.name) {
					state.counter = state.counter - generator.getCost(); // Subtract the generator cost from the total # of strawberries
					state.generators[i].quantity = action.payload.quantity; // Update the generator quantity
					return state;
				}
			}

		case constants.actions.CHECK_STORY:
			for (var i = 0; i < state.story.length; i++) {
				const story = new Story(state.story[i]);
				if (story.isUnlockYet(state.counter)) {
					story.unlock();
					state.story[i].state = story.state;
					return state;
				}
			}
		
		default:
			return state;
	}
}