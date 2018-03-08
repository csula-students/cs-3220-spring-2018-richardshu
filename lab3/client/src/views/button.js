import constants from '../constants';

export default function (store) {
	return class ButtonComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			this.onStateChange = this.handleStateChange.bind(this);

			// TODO: render generator initial view
			this.innerHTML = '<button id="increment_button">Harvest Strawberry</button>';

			// TODO: add click event to increment counter
			this.addEventListener('click', () => {
				this.store.dispatch({
					type: constants.actions.BUTTON_CLICK
				});
			});
		}

		handleStateChange (newState) {
			const strawberry_count = document.getElementById('strawberry_count');
			strawberry_count.innerHTML = newState.counter;
			console.log(this.store.state.counter);
		}
		
		// InnerHTML rendering, this code executes once when you create the object in HTML 
		connectedCallback () {
			console.log(this);
			console.log('ButtonComponent#onConnectedCallback', this);
			this.store.subscribe(this.onStateChange);
		}
		
		disconnectedCallback () {
			console.log('ButtonComponent#onDisconnectedCallback', this);
			this.store.unsubscribe(this.onStateChange);
		}
	};
}
