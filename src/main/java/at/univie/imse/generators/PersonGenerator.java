package at.univie.imse.generators;

import java.time.LocalDate;
import java.util.Random;

public class PersonGenerator {

	private String login;
	private String password;
	private String name;
	private LocalDate DoB;

	private PersonInfoGenerator pG = new PersonInfoGenerator();
	private Random random = new Random();

	public PersonGenerator() {
	}

	public void generatePerson() {
		String fN = pG.getFirstNames();
		String lN = pG.getLastNames();
		login = fN.substring(0, random.nextInt(fN.length())) + lN.substring(0, random.nextInt(lN.length()))
				+ random.nextInt(Integer.MAX_VALUE);
		name = fN + " " + lN;
		password = pG.getPassword();
		DoB = pG.getRandomDoB();
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDoB() {
		return DoB;
	}
}
