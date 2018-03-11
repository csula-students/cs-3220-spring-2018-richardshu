export default function (store) {
	return class StoryBookComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			// TODO: initial DOM rendering of story itself
			this.render();

			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			// TODO: display story based on the state "resource" and "stories"
			this.render(); // Update the story
		}

		connectedCallback () {
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}

		render () {
			this.innerHTML = `
			<div id="story_book">
				<h4>You are an aspiring strawberry farmer who wants to create a giant strawberry farm.</h4>
			</div>`
		}
	};
}

