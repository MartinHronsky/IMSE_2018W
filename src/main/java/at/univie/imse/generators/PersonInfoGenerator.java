package at.univie.imse.generators;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//Person names from http://listofrandomnames.com/
public class PersonInfoGenerator {

	private Random rand = new Random();

	private List<String> names = Collections.emptyList();
	private List<String> firstNames = new ArrayList<>();
	private List<String> lastNames = new ArrayList<>();

	public PersonInfoGenerator() {
		try {
			names = Files.readAllLines(Paths.get("src/main/resources/names.txt"), StandardCharsets.UTF_8);
			for (int i = 0; i < 1000; i++) {
				firstNames.add(names.get(i).split(" ")[0]);
				lastNames.add(names.get(i).split(" ")[1]);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// loads backup list
		firstNames.addAll(Arrays.asList(firstNamesB));
		lastNames.addAll(Arrays.asList(lastNamesB));
	}

	// -------Date of Birth------------
	public LocalDate getRandomDoB() {
		int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(1999, 1, 1).toEpochDay();
		long randomDay = minDay + rand.nextInt(maxDay - minDay);
		return LocalDate.ofEpochDay(randomDay);
	}

	// -----Random String of letters-
	public String getPassword() {
		char[] word = new char[rand.nextInt(10) + 3];
		for (int j = 0; j < word.length; j++) {
			word[j] = (char) ('a' + rand.nextInt(26));
		}
		return new String(word);
	}

	// -----Random First Name--------
	public String getFirstNames() {
		return firstNames.get(rand.nextInt(firstNames.size()));
	}

	// -----Random Last Name---------
	public String getLastNames() {
		return lastNames.get(rand.nextInt(lastNames.size()));
	}

	// backup list
	// -------------firstname--
	private String[] firstNamesB = { "Adela", "Jacquline", "Towanda", "Ethan", "Enriqueta", "Millicent", "Willena",
			"Marlena", "Suzi", "Rodney", "Dung", "Agnus", "Sachiko", "Shaunna", "Dara", "Madaline", "Jamika",
			"Columbus", "Denis", "Eula", "Thea", "Vance", "Danna", "Lavonna", "Kina", "Mallory", "Cody", "Elton",
			"Marvella", "Maryland", "Tommy", "Kera", "Page", "Suzy", "Elsie", "Kandice", "Jeremy", "Corine", "Basil",
			"Henry", "Janeen", "Fermin", "Karissa", "Rubye", "Blaine", "Antonio", "Russel", "Tyesha", "Kathlyn",
			"Max" };
	// ------------lastname--
	private String[] lastNamesB = { "Everette", "Parten", "Yadon", "Mickle", "Mcnab", "Varner", "Pearse", "Jobin",
			"Chard", "Mccaleb", "Greenfield", "Tilton", "Coletta", "Nida", "Fortner", "Whipkey", "Abate", "Bluhm",
			"Newbrough", "Beamon", "Platter", "Sword", "Feltz", "Seegmiller", "Sperry", "Harte", "Victory", "Richman",
			"Vorpahl", "Shin", "Dorfman", "Covell", "Aitchison", "Fannin", "Wax", "Pascal", "Bruening", "Stander",
			"Kung", "Mcmiller", "Tortora", "Dunmore", "Covarrubias", "Alicea", "Level", "Canary", "Kuntz", "Holzinger",
			"Struble", "Wootton" };

}
