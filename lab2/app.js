
// PubSub is a single object for publishing data to multiple subscribers.
class PubSub {
	constructor () {
		this.subscribers = [];
	}

	// Adds a new function to the list of existing functions.
	subscribe (fn) {
		this.subscribers.push(fn);
	}

	// Calls the functions that the object, "data", is subscribed to.
	publish (data) {
		this.subscribers.forEach(subscriber => {
			subscriber(data);
		});
	}
}

// Create a PubSub object
const pubSub = new PubSub();


let stawberry_count = document.getElementById("strawberry_count");

// Define an increment function
function increment () {
	incrementalGame.state.counter++;
	strawberry_count.innerHTML = "Strawberries " + incrementalGame.state.counter;
}

// Subscribe
pubSub.subscribe(increment);


// Clicking the increment button triggers the increment function
const incrementButton = document.getElementById("increment_button");
incrementButton.addEventListener('click', () => {
	pubSub.publish(window.incrementalGame.state.counter);
});