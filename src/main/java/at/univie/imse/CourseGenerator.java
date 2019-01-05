package at.univie.imse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class CourseGenerator {

	public CourseGenerator() {}

	private String name;
	private String language;
	private String cr_type;
	private BigDecimal price;
	private String notes;

	private Random rand = new Random();
	private String[] lan = {"English", "German", "French", "Dutch", "Chinese", "Japan", "Russian", "Slovak", "Spanish", "Portuguese", "Swedish", "Italian"};
	private String[] type = {"Beginner", "Intermediate", "Advanced", "Expert", "Language Cert.", "Youth"};
	private String[] note = {"Discount for large groups or students", "Still free places available",
			"Group full. If enough applicant register, second class will be opened", "Bonus: 5 conversation classes for free."};
	private String vat = "All prices includes VAT";
	private String test = "Price Includes Examination";

	public void generateCourse() {
		language = lan[rand.nextInt(lan.length)];
		cr_type = type[rand.nextInt(type.length)];
		name = language.substring(0, 3) + "_" + cr_type.substring(0, 3) + rand.nextInt(3);
		notes = vat;
		if (cr_type.equals("Language Cert.")) {
			notes += " " + test;
			//rand.nextInt((max - min) + 1) + min
			int pr = rand.nextInt((3000 - 700) + 1) + 700;
			price = new BigDecimal(pr).setScale(0, RoundingMode.HALF_UP);
		}
		notes += " " + note[rand.nextInt(note.length)];
		//rand.nextInt((max - min) + 1) + min
		int pr = rand.nextInt((1500 - 150) + 1) + 150;
		price = new BigDecimal(pr).setScale(0, RoundingMode.HALF_UP);
	}

	public String getName() {
		return name;
	}

	public String getLanguage() {
		return language;
	}

	public String getCr_type() {
		return cr_type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getNotes() {
		return notes;
	}
}
