import constants from '../constants.js';
import Story from '../models/story.js';

export default function (store) {
	return class StoryBookComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			// TODO: initial DOM rendering of story itself
			this.render();

			// Create a variable to hold the storyline
			this.storyline = "You are an aspiring strawberry farmer who wants to create a giant strawberry farm.";

			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			// TODO: display story based on the state "resource" and "stories"
			this.render(); // Update the story
		}

		connectedCallback () {
			console.log('StoryBookComponent#onConnectedCallback', this);
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			console.log('StoryBookComponent#onDisconnectedCallback', this);
			this.store.unsubscribe(this.onStateChange);
		}

		addStoryLine (description) {
			this.storyline = this.storyline + '<br>' + description;
		}

		render () {
			// Check to see if the storyline should be updated
			for (var i = 0; i < this.store.state.story.length; i++) {
				const story = this.store.state.story[i];
				if (story.state === 'visible' && !this.storyline.includes(story.description)) {
					this.addStoryLine(story.description);
				}
			}

			this.innerHTML = `
			<div id="story_book">
				<h4>${this.storyline}</h4>
			</div>`
		}
	};
}

