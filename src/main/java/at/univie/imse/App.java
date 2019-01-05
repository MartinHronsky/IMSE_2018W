package at.univie.imse;

import at.univie.imse.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.time.ZoneId;
import java.util.Date;

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

		File file = new File("src/main/resources/configuration.properties");
		Session session = HibernateUtil.getSessionFactory(file.getAbsolutePath()).openSession();
		Transaction transaction = session.beginTransaction();

		Teacher t = new Teacher();
		Login tl = new Login();
		cd.generatePerson();
		tl.setUserName(cd.getLogin());
		tl.setPassword(cd.getPassword());
		t.setIdTeacher(cd.getLogin());
		t.setLogin(tl);
		t.setName(cd.getName());
		t.setDateOfBirth(Date.from(cd.getDoB().atStartOfDay(ZoneId.systemDefault()).toInstant()));

		Student s = new Student();
		Login sl = new Login();
		cd.generatePerson();
		sl.setUserName(cd.getLogin());
		sl.setPassword(cd.getPassword());
		s.setLogin(sl);
		s.setIdStudent(cd.getLogin());
		s.setName(cd.getName());
		s.setDateOfBirth(Date.from(cd.getDoB().atStartOfDay(ZoneId.systemDefault()).toInstant()));

		Organization o = new Organization();
		Login ol = new Login();
		og.generateOrg();
		ol.setUserName(og.getLogin());
		ol.setPassword(og.getPassword());
		o.setLogin(ol);
		o.setIdOrganization(og.getLogin());
		o.setName(og.getName());

		BankAccount bA = new BankAccount();
		bA.setUserName(s.getIdStudent());
		bA.setCard_number(bag.getRandomCreditCardNumber());
		bA.setAmount(bag.getRandomAmount());
		bA.setLogin(sl);

		at.univie.imse.model.Transaction tran = new at.univie.imse.model.Transaction();
		tran.setStudent(s);
		tran.setTeacher(t);
		tran.setAmount(bA.getAmount());
		//"P" as passed, "F" as failed
		tran.setState("P");
		tran.setIdTransaction(0);

		Course c = new Course();
		crgen.generateCourse();
		c.setIdCourse(crgen.getName());
		c.setTeacher(t);
		c.setLanguage(crgen.getLanguage());
		c.setTypeOfTheCourse(crgen.getCr_type());
		c.setPrice(crgen.getPrice());
		c.setNote(crgen.getNotes());

		Assignment a = new Assignment();
		AssignmentPK apk = new AssignmentPK();
		apk.setIdCourse(c.getIdCourse());
		apk.setIdStudent(s.getIdStudent());
		a.setId(apk);
		a.setCourse(c);
		a.setStudent(s);
		a.setIdTransaction(tran.getIdTransaction());

		Location location = new Location();
		lg.generateLocation();
		location.setIdLocation(lg.getLoc_id());
		location.setRoomCapacity(lg.getCapacity());
		location.setNote(lg.getNote());

		SchedulePK sch = new SchedulePK();
		Schedule schedule = new Schedule();
		sg.generateSchedule();
		sch.setIdCourse(c.getIdCourse());
		sch.setIdLocation(location.getIdLocation());
		sch.setDateFrom(Date.from(sg.getFrom().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		sch.setDateTo(Date.from(sg.getTo().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		schedule.setId(sch);
		schedule.setLocation(location);
		schedule.setCourse(c);
		schedule.setNote(sg.getNote());

		session.save(tl);
		session.save(t);
		session.save(sl);
		session.save(s);
		session.save(ol);
		session.save(o);
		session.save(bA);
		session.save(tran);
		session.save(c);
		session.save(a);
		session.save(location);
		session.save(schedule);

		try {
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

	/*private static void usage() {
		System.out.println("Please specify full path to the configuration file.");
	}*/
}
