import constants from './constants.js';

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
				const generator = state.generators[i];
				if (generator.name === action.payload.name) {
					state.counter = state.counter - generator.baseCost; // Subtract the generator cost from the total # of strawberries
					generator.quantity = action.payload.quantity; // Update the generator quantity
					return state;
				}
			}
		
		default:
			return state;
	}
}