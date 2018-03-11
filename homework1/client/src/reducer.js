import constants from './constants.js';
import Generator from './models/generator.js';
import Story from './models/story.js';

export default function reducer (state, action) {
	switch (action.type) {

		case constants.actions.EXAMPLE:
			state.example = action.payload;
			return state;

		case constants.actions.BUTTON_CLICK:
			// Rounding is necessary to avoid floating point approximations
			state.counter = +(state.counter + 1).toFixed(2);
			return state;

		case constants.actions.BUY_GENERATOR:
			for (var i = 0; i < state.generators.length; i++) {
				const generator = new Generator(state.generators[i]);
				if (generator.name === action.payload.name) {
					// Subtract the generator cost from the total # of strawberries
					state.counter = +(state.counter - generator.getCost()).toFixed(2);
					// Update the generator quantity
					state.generators[i].quantity = action.payload.quantity;
					break;
				}
			}
			return state;

		case constants.actions.CHECK_STORY:
			for (var i = 0; i < state.story.length; i++) {
				const story = new Story(state.story[i]);
				if (story.isUnlockYet(state.counter)) {
					story.unlock(); // Set story state to "visible"
					state.story[i].state = story.state;
					break;
				}
			}
			return state;

		case constants.actions.INCREMENT:
			state.counter = +(state.counter + action.payload).toFixed(2);
			return state;
		
		default:
			return state;
	}
}