package at.univie.imse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.hibernate.Transaction;

import at.univie.imse.generators.AbstractGenerator;
import at.univie.imse.model.Assignment;
import at.univie.imse.model.BankAccount;
import at.univie.imse.model.Course;
import at.univie.imse.model.Location;
import at.univie.imse.model.Login;
import at.univie.imse.model.Organization;
import at.univie.imse.model.Schedule;
import at.univie.imse.model.Student;
import at.univie.imse.model.Teacher;

/**
 * Driver for the program.
 * 
 * @author MartinHronsky
 *
 */
public class App {

	public static void main(String[] args) {

		boolean generatedSuccessfully = false;

		while (!generatedSuccessfully) {
			try {
				initializeDatabase();
				generatedSuccessfully = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void initializeDatabase() throws Exception {
		// Get the hibernate session
		Session session = HibernateUtil.getSessionFactory("src/main/resources/configuration.properties").openSession();

		// Generate logins
		Map<Teacher, Login> teachersWithLogins = AbstractGenerator.generateTeachersWithLogins(500);
		Map<Student, Login> studentsWithLogins = AbstractGenerator.generateStudentsWithLogins(500);
		Map<Organization, Login> organizationsWithLogins = AbstractGenerator.generateOrganizationsWithLogins(500);

		// Generate bank accounts for all logins
		List<Login> allLogins = new ArrayList<Login>();
		allLogins.addAll(teachersWithLogins.values());
		allLogins.addAll(studentsWithLogins.values());
		allLogins.addAll(organizationsWithLogins.values());
		List<BankAccount> bankAccounts = AbstractGenerator.generateBankAccounts(allLogins);

		// Generate courses
		List<Teacher> teachers = new ArrayList<Teacher>(teachersWithLogins.keySet());
		List<Course> courses = AbstractGenerator.generateCourses(teachers, 500);

		// Generate transactions
		List<Student> students = new ArrayList<Student>(studentsWithLogins.keySet());
		Map<at.univie.imse.model.Transaction, Assignment> transactions = AbstractGenerator
				.generateTransactions(students, courses);

		// Generate locations and schedules
		List<Location> locations = AbstractGenerator.generateLocations(500);
		List<Schedule> schedules = AbstractGenerator.generateSchedules(transactions, students, locations, courses);

		Transaction transaction = session.beginTransaction();

		// Persist all data
		for (Entry<Teacher, Login> entry : teachersWithLogins.entrySet()) {
			session.save(entry.getValue());
			session.save(entry.getKey());
		}

		for (Entry<Student, Login> entry : studentsWithLogins.entrySet()) {
			session.save(entry.getValue());
			session.save(entry.getKey());
		}

		for (Entry<Organization, Login> entry : organizationsWithLogins.entrySet()) {
			session.save(entry.getValue());
			session.save(entry.getKey());
		}

		for (BankAccount bankAccount : bankAccounts) {
			session.save(bankAccount);
		}

		for (at.univie.imse.model.Transaction transaction_ : transactions.keySet()) {
			session.save(transaction_);
		}

		for (Course course : courses) {
			session.save(course);
		}

		for (Assignment assignment : transactions.values()) {
			session.save(assignment);
		}

		for (Location location : locations) {
			session.save(location);
		}

		for (Schedule schedule : schedules) {
			session.save(schedule);
		}

		// Commit the tranasction
		transaction.commit();
	}
}
