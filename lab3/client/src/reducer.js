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
			state.generators[action.payload].quantity++; // action.payload is the generator's id
			return state;

		default:
			return state;
	}
}