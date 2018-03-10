import '@webcomponents/webcomponentsjs';

import {loop} from './game';
import Store from './store';
import reducer from './reducer';

import ButtonComponent from './views/button';
import CounterComponent from './views/counter';
import ExampleComponent from './views/example';
import GeneratorComponent from './views/generator';
import StoryBookComponent from './views/story-book';

/**
 * Data flow diagram
 +----------------------------------------------------+
 | +------------------+          +------------------+ |
 | |                  |          |                  | |
++-|       Loop       |<---------|    Generator     | |
|| |                  |          |                  | |
|| +------------------+          +------------------+ |
||G          ^                                        |
||a          +-----------------------------+          |
||m                                        |          |
||e                                        |          |
||                               +------------------+ |
||                               |                  | |
||                               |     Stories      | |
||                               |                  | |
||                               +------------------+ |
|+----------------------------------------------------+
+------------------------------------------------------------+
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|       +----------------------------------------------------+----------+
|       | +------------------+                     +------------------+ |
|       | |                  |        Mutates      |                  | |
|       | |     Reducer      |-------------------->|      State       | |
|       | |                  |                     |                  | |
|       | +------------------+                     +------------------+ |
|       |S          ^                                        |          |
|       |t          |                                        |          |
|       |o          |                                        |          |
|       |r          | Triggers                     Notifies  |          |
|       |e          |                                        |          |
|       |           |                                        v          |
|       | +------------------+                     +------------------+ |
|       | |                  |                     |                  | |
+-------+>|      Action      |                     |    Listeners     | |
        | |                  |                     |                  | |
        | +------------------+                     +------------------+ |
        +-----------^----------------------------------------+----------+
                    |                                        |
                    |                                        |
                    |                                        |
                    |                                        |
                    | Dispatches                             |
                    |                                        |
                    |                                        |
          +------------------+                               |
          |                  |                               |
          |      Views       |              Notifies changes |
          |    Components    |<------------------------------+
          |                  |
          +------------------+
 */
main();

// main function wraps everything at top level
function main () {
	// TODO: fill the blank based on the theme you have choosen
	const initialState = {
		example: 'Hello custom element',
		counter: 0,
		generators: [
			{
				type: 'autonomous',
				name: 'Clicker',
				description: 'Click click click! Harvest strawberries one click at a time',
				rate: 5,
				quantity: 0,
				baseCost: 10,
				unlockValue: 1
			},
			{
				type: 'autonomous',
				name: 'Farmer',
				description: 'Old McDonald had a strawberry farm...',
				rate: 10,
				quantity: 0,
				baseCost: 100,
				unlockValue: 10
			},
			{
				type: 'autonomous',
				name: 'Tractor',
				description: 'Vroom vroom...tractors coming through!',
				rate: 20,
				quantity: 0,
				baseCost: 1000,
				unlockValue: 100
			},
			{
				type: 'autonomous',
				name: 'Mine',
				description: 'Unlock a strawberry mine!',
				rate: 30,
				quantity: 0,
				baseCost: 10000,
				unlockValue: 1000
			},
			{
				type: 'autonomous',
				name: 'Factory',
				description: 'Strawberry mass production galore',
				rate: 60,
				quantity: 0,
				baseCost: 100000,
				unlockValue: 10000
			},
			{
				type: 'autonomous',
				name: 'Nuclear Bomb',
				description: 'A massive strawberry explosion',
				rate: 120,
				quantity: 0,
				baseCost: 1000000,
				unlockValue: 100000
			}
		],
		story: []
	};

	// initialize store
	const store = new Store(reducer, initialState);
	console.log(ExampleComponent(store));

	// define web components
	window.customElements.define('component-example', ExampleComponent(store));
	// no longer used
	window.customElements.define('game-button', ButtonComponent(store));
	window.customElements.define('game-counter', CounterComponent(store));
	// lab 3
	window.customElements.define('game-generator', GeneratorComponent(store));
	// homework 1
	window.customElements.define('game-story-book', StoryBookComponent(store));

	// For ease of debugging purpose, we will expose the critical store under window
	// ps: window is global
	window.store = store;

	// start game loop
	loop(store);
}
