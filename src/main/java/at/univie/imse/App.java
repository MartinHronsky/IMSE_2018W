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

		Session session = HibernateUtil.getSessionFactory("src/main/resources/configuration.properties").openSession();

		Map<Teacher, Login> teachersWithLogins = AbstractGenerator.generateTeachersWithLogins(5000);
		Map<Student, Login> studentsWithLogins = AbstractGenerator.generateStudentsWithLogins(10000);
		Map<Organization, Login> organizationsWithLogins = AbstractGenerator.generateOrganizationsWithLogins(150);
		List<BankAccount> bankAccounts = AbstractGenerator.generateBankAccounts(studentsWithLogins.values());
		List<Student> students = new ArrayList<Student>(studentsWithLogins.keySet());
		List<Teacher> teachers = new ArrayList<Teacher>(teachersWithLogins.keySet());
		List<at.univie.imse.model.Transaction> transactions = AbstractGenerator.generateTransactions(students, teachers,
				1400);
		List<Course> courses = AbstractGenerator.generateCourses(teachers, 360);
		List<Assignment> assignments = AbstractGenerator.generateAssignments(transactions, courses);
		List<Location> locations = AbstractGenerator.generateLocations(150);
		List<Schedule> schedules = AbstractGenerator.generateSchedules(courses, locations, 4000);

		Transaction transaction = session.beginTransaction();

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

		for (at.univie.imse.model.Transaction transaction_ : transactions) {
			session.save(transaction_);
		}

		for (Course course : courses) {
			session.save(course);
		}

		for (Assignment assignment : assignments) {
			session.save(assignment);
		}

		for (Location location : locations) {
			session.save(location);
		}

		for (Schedule schedule : schedules) {
			session.save(schedule);
		}

		transaction.commit();
	}
}
