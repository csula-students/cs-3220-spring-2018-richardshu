import constants from '../constants.js';

export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			// TODO: render generator initial view
			this.innerHTML = this.render();

			// TODO: subscribe to store on change event
			this.onStateChange = this.handleStateChange.bind(this);

			// TODO: add click event
			this.addEventListener('click', () => {
				this.store.dispatch({
					type: constants.actions.BUY_GENERATOR,
					payload: this.dataset.id
				})
			});

		}

		handleStateChange (newState) {
			this.store.state.generators[this.dataset.id].quantity = newState.generators[this.dataset.id].quantity; // Update the generator quantity
			this.innerHTML = this.render(); // Refresh the HTML content
		}

		connectedCallback () {
			console.log('GeneratorComponent#onConnectedCallback', this);
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			console.log('GeneratorComponent#onDisconnectedCallback', this);
			this.store.unsubscribe(this.onStageChange);
		}

		render () {
			return `
			<div class="generator-box">
				<div class="generator-header">
					<h3>${this.store.state.generators[this.dataset.id].name}</h3>
					<h3>${this.store.state.generators[this.dataset.id].quantity}</h3>
				</div>
				<div class="generator-description">
					<h4>${this.store.state.generators[this.dataset.id].description}</h4>
				</div>
				<div class="generator-footer">
					<h4>${this.store.state.generators[this.dataset.id].rate}/60</h4>
					<button class="generator-button">${this.store.state.generators[this.dataset.id].baseCost} STRAWBERRIES</button>
				</div>
			</div>
			`
		}
	};
}