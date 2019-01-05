package at.univie.imse;

import at.univie.imse.model.Location;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;

/**
 * Driver for the program.
 * 
 * @author MartinHronsky
 *
 */
public class App {
	public static void main(String[] args) {

		/*if (args.length != 1) {
			usage();
		}*/

		//Testing generators------------------------------
		GeneratePerson cd = new GeneratePerson();
		cd.generatePerson();
		System.out.println(cd.getName() + " " + cd.getLogin() + " " + cd.getPassword() + " " + cd.getDoB());
		cd.generatePerson();
		System.out.println(cd.getName() + " " + cd.getLogin() + " " + cd.getPassword() + " " + cd.getDoB());

		OrganizationGenerator og = new OrganizationGenerator();
		og.generateOrg();
		System.out.println(og.getName() + " " + og.getLogin() + " " + og.getPassword() + " " + og.getSup());
		og.generateOrg();
		System.out.println(og.getName() + " " + og.getLogin() + " " + og.getPassword() + " " + og.getSup());

		CourseGenerator crgen = new CourseGenerator();
		crgen.generateCourse();
		System.out.println(crgen.getName() + " " + crgen.getCr_type() + " " + crgen.getLanguage() + " " + crgen.getPrice() + " " + crgen.getNotes());
		crgen.generateCourse();
		System.out.println(crgen.getName() + " " + crgen.getCr_type() + " " + crgen.getLanguage() + " " + crgen.getPrice() + " " + crgen.getNotes());

		LocationGenerator lg = new LocationGenerator();
		lg.generateLocation();
		System.out.println(lg.getLoc_id() + ", " + lg.getCapacity() + ", " + lg.getNote());
		lg.generateLocation();
		System.out.println(lg.getLoc_id() + ", " + lg.getCapacity() + ", " + lg.getNote());

		ScheduleGenerator sg = new ScheduleGenerator();
		sg.generateSchedule();
		System.out.println(sg.getFrom() + " " + sg.getTo() + " " + sg.getNote());
		sg.generateSchedule();
		System.out.println(sg.getFrom() + " " + sg.getTo() + " " + sg.getNote());

		BankAccountGenerator bag = new BankAccountGenerator();
		System.out.println(bag.getRandomCreditCardNumber() + " " + bag.getRandomAmount());
		System.out.println(bag.getRandomCreditCardNumber() + " " + bag.getRandomAmount());

		//-------------------------------------
		Location location = new Location();
		location.setNote("Test");
		location.setIdLocation("Test");
		location.setRoomCapacity(50);

		File file = new File("src/main/resources/configuration.properties");
		Session session = HibernateUtil.getSessionFactory(file.getAbsolutePath()).openSession();
		Transaction transaction = session.beginTransaction();

		session.save(location);

		transaction.commit();

	}

	/*private static void usage() {
		System.out.println("Please specify full path to the configuration file.");
	}*/
}
