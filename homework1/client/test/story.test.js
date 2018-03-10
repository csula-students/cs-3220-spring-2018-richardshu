/*eslint-env node, jest */
import Story from '../src/models/story';
import * as mock from './mock';

describe('Story model', () => {
	test('Story should be able to check if itself is unlocked yet', () => {
		let counter = 0;
		const story = new Story(mock.story);
		expect(story.isUnlockYet(counter)).toEqual(false);
		counter = 11;
		expect(story.isUnlockYet(counter)).toEqual(true);
	});

	test('Story should be able to unlock itself', () => {
		const story = new Story(mock.story);
		expect(story.state).toEqual('hidden');
		story.unlock();
		expect(story.state).Equal('visible');
	});
});
