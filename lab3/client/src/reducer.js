import constants from './constants';

export default function reducer (state, action) {
	switch (action.type) {
	case constants.actions.EXAMPLE_MUTATION:
		state.example = action.payload;
		return state;
	case constants.actions.BUTTON_CLICK:
		state.counter++; // THIS IS WHERE THE COUNTER IS STORED
		return state;
	default:
		return state;
	}
}