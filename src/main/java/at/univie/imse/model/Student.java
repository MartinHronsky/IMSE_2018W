package at.univie.imse.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@Table(name = "student")
@NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_student", unique = true, nullable = false, length = 255)
	private String idStudent;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;

	@Column(nullable = false, length = 255)
	private String name;

	// bi-directional many-to-one association to Assignment
	@OneToMany(mappedBy = "student")
	private List<Assignment> assignments;

	// bi-directional one-to-one association to Login
	@OneToOne
	@JoinColumn(name = "id_student", nullable = false, insertable = false, updatable = false)
	private Login login;

	// bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy = "student")
	private List<Transaction> transactions;

	// bi-directional many-to-many association to Schedule
	@ManyToMany
	@JoinTable(name = "attends", joinColumns = {
			@JoinColumn(name = "id_student", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "date_from", referencedColumnName = "date_from", nullable = false),
					@JoinColumn(name = "date_to", referencedColumnName = "date_to", nullable = false),
					@JoinColumn(name = "id_course", referencedColumnName = "id_course", nullable = false),
					@JoinColumn(name = "id_location", referencedColumnName = "id_location", nullable = false) })
	private List<Schedule> schedules;

	public Student() {
		this.schedules = new ArrayList<Schedule>();
	}

	public String getIdStudent() {
		return this.idStudent;
	}

	public void setIdStudent(String idStudent) {
		this.idStudent = idStudent;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Assignment> getAssignments() {
		return this.assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public Assignment addAssignment(Assignment assignment) {
		getAssignments().add(assignment);
		assignment.setStudent(this);

		return assignment;
	}

	public Assignment removeAssignment(Assignment assignment) {
		getAssignments().remove(assignment);
		assignment.setStudent(null);

		return assignment;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setStudent(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setStudent(null);

		return transaction;
	}

	public List<Schedule> getSchedules() {
		return this.schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

}