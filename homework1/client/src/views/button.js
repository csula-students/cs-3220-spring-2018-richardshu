import constants from '../constants';

export default function (store) {
	return class ButtonComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			// TODO: render generator initial view
			this.innerHTML = '<button id="increment_button">Harvest Strawberry</button>';

			// TODO: add click event to increment counter
			this.addEventListener('click', () => {
				this.store.dispatch({
					type: constants.actions.BUTTON_CLICK
				});
			});
		}
	};
}
