package edu.csula.models;

import java.util.Collection;

import edu.csula.models.Event;
import edu.csula.models.Generator;

public class State {
	private int counter = 0;
	private Collection<Event> story;
	private Collection<Generator> generators;

	public State(Collection<Event> events, Collection<Generator> generators) {
		this.story = events;
		this.generators = generators;
	}
}
