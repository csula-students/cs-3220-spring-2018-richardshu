import constants from '../constants'; // 

export default function (store) {
	return class ButtonComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			this.onStateChange = this.handleStateChange.bind(this);

			// TODO: add click event to increment counter
			// hint: use "store.dispatch" method (see example component)

		}

		handleStateChange (newState) {
			this.innerHTML = `Strawberries: ${newState.counter}`;
		}
		
		// InnerHTML rendering, this code executes once when you create the object in HTML 
		connectedCallback () {
			this.innerHTML = '<button>Click me</button>';
		}
		
		disconnectedCallback () {

		}
	};
}
