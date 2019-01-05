package at.univie.imse.generators;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//streets adresses from https://www.briandunning.com/sample-data/
//used UK and US 500 free dataset, combined 250 from UK and 250 from US data
public class LocationGenerator {

	public LocationGenerator() {
		try {
			loc = Files.readAllLines(Paths.get("src/main/resources/adress.csv"), StandardCharsets.UTF_8);
			for (int i = 0; i < 500; i++) {
				address.add(loc.get(i));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private Random rand = new Random();
	private List<String> loc = Collections.emptyList();
	private List<String> address = new ArrayList<>();
	private String[] notes = { "Accessible by public transport", "Paid parking available", "Free parking available",
			"Refreshments available" };

	private String loc_id;
	private int capacity;
	private String note;

	public void generateLocation() {
		String temp = address.get(rand.nextInt(address.size()));
		String[] arrAdress = temp.split(",");
		loc_id = arrAdress[1] + arrAdress[2] + rand.nextInt(Integer.MAX_VALUE);
		note = arrAdress[0] + "; " + notes[rand.nextInt(notes.length)];
		// rand.nextInt((max - min) + 1) + min
		capacity = rand.nextInt((50 - 10) + 1) + 10;
	}

	public String getLoc_id() {
		return loc_id;
	}

	public int getCapacity() {
		return capacity;
	}

	public String getNote() {
		return note;
	}
}
