export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			// TODO: render generator initial view
			this.innerHTML = this.render();


			// TODO: subscribe to store on change event

			// TODO: add click event
			this.addEventListener('click', () => {
				this.store.dispatch({
					type: 'BUY_GENERATOR',
					payload: {
						name: this.store.state.generators[this.dataset.id].name,
						count: this.store.state.generators[this.dataset.id].quantity
					}
				})
			})

		}

		connectedCallback () {
			console.log(this, this.dataset.id);
			this.id = this.dataset.id;
			this.innerHTML = this.render();
		}

		render () {
			return `
			<div class="generator-box">
				<div class="generator-header">
					<h3>${this.store.state.generators[this.dataset.id].name}</h3>
					<h3>0</h3>
				</div>
				<div class="generator-description">
					<h4>Harvests ${this.store.state.generators[this.dataset.id].rate} strawberries per minute</h4>
				</div>
				<div class="generator-footer">
					<h4>${this.store.state.generators[this.dataset.id].rate}/60</h4>
					<button class="generator-button">${this.store.state.generators[this.dataset.id].cost} Strawberries</button>
				</div>
			</div>
			`
		}
	};
}