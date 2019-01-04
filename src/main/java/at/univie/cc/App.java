package at.univie.cc;

import org.hibernate.Session;
import org.hibernate.Transaction;

import at.univie.cc.model.Location;

/**
 * Driver for the program.
 * 
 * @author MartinHronsky
 *
 */
public class App {
	public static void main(String[] args) {

		if (args.length != 1) {
			usage();
		}

		GeneratePerson cd = new GeneratePerson();

		Location location = new Location();
		location.setNote("Test");
		location.setIdLocation("Test");
		location.setRoomCapacity(50);

		Session session = HibernateUtil.getSessionFactory("C:\\Users\\romco\\Desktop\\UniProjects\\IMSE\\IMSE_2018W\\src\\main\\resources\\configuration.properties").openSession();
		Transaction transaction = session.beginTransaction();

		session.save(location);

		transaction.commit();

	}

	private static void usage() {
		System.out.println("Please specify full path to the configuration file.");
	}
}
