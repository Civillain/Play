package see.playback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import see.actions.Action;

public class Recorder implements Iterator<Action> {

	private List<Action> record;
	protected Logger logger = Logger.getLogger("see");
	
	public Recorder() {
		this.record = new ArrayList<>();
	}
	
	public void add(Action action) {
		logger.info("Recording: " + action.toString());
		record.add(action);
	}
	
	@Override
	public boolean hasNext() {
		return !record.isEmpty();
	}

	@Override
	public Action next() {
		return record.remove(0);
	}

	public List<Action> getRecord() {
		List<Action> clone = Collections.unmodifiableList(record);
		return clone;
	}
}