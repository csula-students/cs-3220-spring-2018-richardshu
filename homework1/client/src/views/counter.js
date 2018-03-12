export default function (store) {
	return class CounterComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			// TODO: render counter inner HTML based on the store state
			this.render();

			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			console.log('CounterComponent = ' + this.store.state.counter);
			// TODO: update inner HTML based on the new state
			this.render();
		}

		connectedCallback () {
			console.log('CounterComponent#onConnectedCallback', this);
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			console.log('CounterComponent#onDisconnectedCallback', this);
			this.store.unsubscribe(this.onStateChange);
		}

		render () {
			this.innerHTML = `<h2>${this.store.state.counter}</h2>`;
		}
	};
}

