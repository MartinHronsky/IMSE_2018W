package at.univie.imse.generators;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import at.univie.imse.model.Assignment;
import at.univie.imse.model.AssignmentPK;
import at.univie.imse.model.BankAccount;
import at.univie.imse.model.Course;
import at.univie.imse.model.Location;
import at.univie.imse.model.Login;
import at.univie.imse.model.Organization;
import at.univie.imse.model.Schedule;
import at.univie.imse.model.SchedulePK;
import at.univie.imse.model.Student;
import at.univie.imse.model.Teacher;
import at.univie.imse.model.Transaction;

/**
 * Class uniting the generators into one class.
 * 
 * @author MartinHronsky
 *
 */
public class AbstractGenerator {

	private static PersonGenerator personGenerator = new PersonGenerator();
	private static OrganizationGenerator organizationGenerator = new OrganizationGenerator();
	private static BankAccountGenerator bankAccountGenerator = new BankAccountGenerator();
	private static CourseGenerator courseGenerator = new CourseGenerator();
	private static LocationGenerator locationGenerator = new LocationGenerator();
	private static ScheduleGenerator scheduleGenerator = new ScheduleGenerator();
	private static Random random = new Random();

	public static Map<Student, Login> generateStudentsWithLogins(int howMany) {

		Map<Student, Login> studentsWithLogins = new HashMap<Student, Login>();

		for (int i = 0; i < howMany; i++) {
			Student student = new Student();
			Login studentLogin = new Login();
			personGenerator.generatePerson();

			studentLogin.setUserName(personGenerator.getLogin());
			studentLogin.setPassword(personGenerator.getPassword());

			student.setName(personGenerator.getName());
			student.setIdStudent(personGenerator.getLogin());
			student.setDateOfBirth(
					Date.from(personGenerator.getDoB().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			student.setLogin(studentLogin);
			studentsWithLogins.put(student, studentLogin);
		}

		return studentsWithLogins;
	}

	public static Map<Teacher, Login> generateTeachersWithLogins(int howMany) {
		Map<Teacher, Login> teachersWithLogins = new HashMap<Teacher, Login>();

		for (int i = 0; i < howMany; i++) {
			Teacher teacher = new Teacher();
			Login teacherLogin = new Login();
			personGenerator.generatePerson();

			teacherLogin.setUserName(personGenerator.getLogin());
			teacherLogin.setPassword(personGenerator.getPassword());

			teacher.setName(personGenerator.getName());
			teacher.setIdTeacher(personGenerator.getLogin());
			teacher.setDateOfBirth(
					Date.from(personGenerator.getDoB().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			teacher.setLogin(teacherLogin);
			teachersWithLogins.put(teacher, teacherLogin);
		}

		return teachersWithLogins;
	}

	public static Map<Organization, Login> generateOrganizationsWithLogins(int howMany) {
		Map<Organization, Login> organizationsWithLogins = new HashMap<Organization, Login>();

		for (int i = 0; i < howMany; i++) {
			Organization organization = new Organization();
			Login organizationLogin = new Login();
			organizationGenerator.generateOrg();

			organizationLogin.setUserName(organizationGenerator.getLogin());
			organizationLogin.setPassword(organizationGenerator.getPassword());

			organization.setLogin(organizationLogin);
			organization.setIdOrganization(organizationGenerator.getLogin());
			organization.setName(organizationGenerator.getName());

			organizationsWithLogins.put(organization, organizationLogin);
		}

		return organizationsWithLogins;
	}

	public static List<BankAccount> generateBankAccounts(Collection<Login> collection) {
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		for (Login login : collection) {
			BankAccount bankAccount = new BankAccount();
			bankAccount.setUserName(login.getUserName());
			bankAccount.setCardNumber(bankAccountGenerator.getRandomCreditCardNumber());
			bankAccount.setAmount(bankAccountGenerator.getRandomAmount());
			bankAccount.setLogin(login);
			bankAccounts.add(bankAccount);
		}
		return bankAccounts;
	}

	public static Map<Transaction, Assignment> generateTransactions(List<Student> students, List<Course> courses) {
		Map<Transaction, Assignment> transactionsMap = new HashMap<Transaction, Assignment>();
		for (Course course : courses) {
			Collections.shuffle(students);
			List<Student> randomStudents = students.subList(0, random.nextInt(students.size()));

			for (Student student : randomStudents) {
				Transaction transaction = new Transaction();
				Teacher teacher = course.getTeacher();
				transaction.setStudent(student);
				transaction.setTeacher(teacher);
				transaction.setIdTransaction(UUID.randomUUID().toString());
				transaction.setState("P");
				transaction.setAmount(new BigDecimal(random.nextInt(50)));

				Assignment assignment = new Assignment();
				AssignmentPK assignmentPK = new AssignmentPK();
				assignmentPK.setIdCourse(course.getIdCourse());
				assignmentPK.setIdStudent(student.getIdStudent());
				assignment.setId(assignmentPK);
				assignment.setCourse(course);
				assignment.setStudent(student);
				assignment.setIdTransaction(transaction.getIdTransaction());
				transactionsMap.put(transaction, assignment);
			}
		}
		return transactionsMap;
	}

	public static List<Course> generateCourses(List<Teacher> teachers, int howMany) {
		List<Course> courses = new ArrayList<Course>();
		for (int i = 0; i < howMany; i++) {
			courseGenerator.generateCourse();
			Course course = new Course();
			course.setIdCourse(courseGenerator.getName());
			course.setLanguage(courseGenerator.getLanguage());
			course.setTypeOfTheCourse(courseGenerator.getCr_type());
			course.setPrice(courseGenerator.getPrice());
			course.setNote(courseGenerator.getNotes());
			course.setTeacher(teachers.get(random.nextInt(teachers.size())));
			courses.add(course);
		}
		return courses;
	}

	public static List<Location> generateLocations(int howMany) {
		List<Location> locations = new ArrayList<Location>();

		for (int i = 0; i < howMany; i++) {
			Location location = new Location();
			locationGenerator.generateLocation();
			location.setIdLocation(locationGenerator.getLoc_id());
			location.setRoomCapacity(locationGenerator.getCapacity());
			location.setNote(locationGenerator.getNote());
			locations.add(location);
		}
		return locations;
	}

	public static List<Schedule> generateSchedules(Map<Transaction, Assignment> transactions, List<Student> students,
			List<Location> locations, List<Course> courses) {

		List<Schedule> schedules = new ArrayList<Schedule>();
		for (Course course : courses) {
			List<Student> studentsThatPaidForThisCourse = new ArrayList<Student>();
			Collection<Assignment> assignments = transactions.values();
			for (Assignment assignment : assignments) {
				if (course.equals(assignment.getCourse())) {
					studentsThatPaidForThisCourse.add(assignment.getStudent());
				}
			}
			int amountOfSchedules = random.nextInt(5) + 1;
			for (int i = 0; i < amountOfSchedules; i++) {
				Collections.shuffle(studentsThatPaidForThisCourse);
				scheduleGenerator.generateSchedule();
				Location randomLocation = locations.get(random.nextInt(locations.size()));
				SchedulePK schedulePK = new SchedulePK();
				Schedule schedule = new Schedule();
				schedulePK.setIdCourse(course.getIdCourse());
				schedulePK.setIdLocation(randomLocation.getIdLocation());
				schedulePK.setDateFrom(
						Date.from(scheduleGenerator.getFrom().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				schedulePK.setDateTo(
						Date.from(scheduleGenerator.getTo().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				schedule.setId(schedulePK);
				schedule.setLocation(randomLocation);
				schedule.setCourse(course);
				schedule.setNote(scheduleGenerator.getNote());
				List<Student> assignedStudents = studentsThatPaidForThisCourse.subList(0,
						random.nextInt(studentsThatPaidForThisCourse.size()));
				schedule.setStudents(assignedStudents);
				for (Student student : assignedStudents) {
					int index = students.indexOf(student);
					Student studentToGetAssigned = students.get(index);
					List<Schedule> hisCurrentSchedules = studentToGetAssigned.getSchedules();
					hisCurrentSchedules.add(schedule);
					studentToGetAssigned.setSchedules(hisCurrentSchedules);
					students.set(index, studentToGetAssigned);
				}
				schedules.add(schedule);

			}
		}
		return schedules;
	}
}
