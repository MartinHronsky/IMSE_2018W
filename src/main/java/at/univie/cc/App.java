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

		Location location = new Location();
		location.setNote("Test");
		location.setIdLocation("Test");
		location.setRoomCapacity(50);

		Session session = HibernateUtil.getSessionFactory(args[0]).openSession();
		Transaction transaction = session.beginTransaction();

		session.save(location);

		transaction.commit();

	}

	private static void usage() {
		System.out.println("Please specify full path to the configuration file.");
	}
}
