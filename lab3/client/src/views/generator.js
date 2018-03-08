import constants from '../constants.js';
import Generator from '../models/generator.js';

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
					payload: {
						name: this.store.state.generators[this.dataset.id].name,
						quantity: this.store.state.generators[this.dataset.id].quantity + 1
					}
				});
			});

		}

		handleStateChange (newState) {
			this.innerHTML = this.render(); // Refresh the HTML content
			console.log(this.store.state);
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
			const generator = new Generator(this.store.state.generators[this.dataset.id]);
			return `
			<div class="generator-box">
				<div class="generator-header">
					<h3>${generator.name}</h3>
					<h3>${generator.quantity}</h3>
				</div>
				<div class="generator-description">
					<h4>${generator.description}</h4>
				</div>
				<div class="generator-footer">
					<h4>${generator.generate()}/60</h4>
					<button class="generator-button">${generator.getCost()} Strawberries</button>
				</div>
			</div>
			`
		}
	};
}