package at.univie.imse.generators;

import java.util.Random;

public class OrganizationGenerator {

	public OrganizationGenerator() {
	}

	private String name;
	private String login;
	private String password;
	private String sup;

	private Random rand = new Random();
	private String[] org = { "Cambridge Assessment English", "GROPPE - School of English", "MONDIALE-Testing GmbH",
			"The New Language School GmbH", "The Global Language System", "Language Learning and testing Foundation",
			"ILTO", "Southern California School of Interpretation", "uOttawa", "Trinity college London", "LTTC GEPT",
			"University of Bedfordshire", "University of Chicago Language Center", "British Council", "TOEFL",
			"Oesterreich Institut", "Polski Institut", "Goethe Institut" };
	private String[] supervisor = { "International Language Testing Association", "British Council", "Certify" };

	public void generateOrg() {
		name = org[rand.nextInt(org.length)];
		sup = supervisor[rand.nextInt(supervisor.length)];
		login = name + "_" + rand.nextInt(Integer.MAX_VALUE);
		password = new PersonInfoGenerator().getPassword();
	}

	public String getName() {
		return name;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getSup() {
		return sup;
	}
}
