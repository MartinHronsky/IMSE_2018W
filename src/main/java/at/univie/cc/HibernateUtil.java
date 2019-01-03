package at.univie.cc;

import java.io.FileInputStream;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import at.univie.cc.model.Assignment;
import at.univie.cc.model.AssignmentPK;
import at.univie.cc.model.BankAccount;
import at.univie.cc.model.Course;
import at.univie.cc.model.Location;
import at.univie.cc.model.Login;
import at.univie.cc.model.Organization;
import at.univie.cc.model.Schedule;
import at.univie.cc.model.SchedulePK;
import at.univie.cc.model.Student;
import at.univie.cc.model.Teacher;
import at.univie.cc.model.Transaction;

/**
 * Utility class that takes care of creating the session factory. This involves
 * reading the configuration file from the application parameters.
 * 
 * @author MartinHronsky
 *
 */
public class HibernateUtil {

	// Array of annotated classes
	private static final Class<?>[] supportedClasses = { Assignment.class, AssignmentPK.class, BankAccount.class,
			Course.class, Location.class, Login.class, Organization.class, Schedule.class, SchedulePK.class,
			Student.class, Teacher.class, Transaction.class };

	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	/**
	 * Creates the {@link SessionFactory}
	 * 
	 * @param propertyFileLocation
	 *            path to the configuration file
	 * @return configured {@link SessionFactory}
	 */
	public static SessionFactory getSessionFactory(String propertyFileLocation) {
		if (sessionFactory == null) {
			try {
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

				Properties configuration = new Properties();
				configuration.load(new FileInputStream(propertyFileLocation));

				registryBuilder.applySettings(configuration);

				registry = registryBuilder.build();

				MetadataSources sources = new MetadataSources(registry);

				for (Class<?> clazz : supportedClasses) {
					sources.addAnnotatedClass(clazz);
				}

				Metadata metadata = sources.getMetadataBuilder().build();

				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				System.out.println("SessionFactory creation failed");
				System.out.println(e.getMessage());
				shutdown();
			}
		}
		return sessionFactory;
	}

	/**
	 * Deletes the registry
	 */
	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}