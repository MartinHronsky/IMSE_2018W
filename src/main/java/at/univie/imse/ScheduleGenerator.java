package at.univie.imse;

import java.time.LocalDate;
import java.util.Random;

public class ScheduleGenerator {

	public ScheduleGenerator() {}

	private LocalDate from;
	private LocalDate to;
	private String note;

	private Random rand = new Random();
	private String[] notes = {"Morning schedule", "Evening schedule", "Afternoon schedule", "Time upon agreement"};

	public LocalDate randomDate() {
		int minD = (int) LocalDate.of(2019, 1, 1).toEpochDay();
		int maxD = (int) LocalDate.of(2019, 12, 31).toEpochDay();
		long rDay = minD + rand.nextInt(maxD - minD);
		return LocalDate.ofEpochDay(rDay);
	}

	public void generateSchedule() {
		from = randomDate();
		do {
			to = randomDate();
		} while (to.compareTo(from) < 0 );
		note = notes[rand.nextInt(notes.length)];
	}

	public LocalDate getFrom() {
		return from;
	}

	public LocalDate getTo() {
		return to;
	}

	public String getNote() {
		return note;
	}
}
