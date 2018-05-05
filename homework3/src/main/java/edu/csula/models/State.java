package edu.csula.models;

import java.util.Collection;

import edu.csula.models.Event;
import edu.csula.models.Generator;

public class State {
	private Collection<Event> events;
	private Collection<Generator> generators;

	public State(Collection<Event> events, Collection<Generator> generators) {
		this.events = events;
		this.generators = generators;
	}
}
