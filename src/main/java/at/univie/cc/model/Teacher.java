package at.univie.cc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the teacher database table.
 * 
 */
@Entity
@Table(name = "teacher")
@NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t")
public class Teacher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_teacher", unique = true, nullable = false, length = 45)
	private String idTeacher;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;

	@Column(nullable = false, length = 45)
	private String name;

	// bi-directional many-to-one association to Course
	@OneToMany(mappedBy = "teacher")
	private List<Course> courses;

	// bi-directional one-to-one association to Login
	@OneToOne
	@JoinColumn(name = "id_teacher", nullable = false, insertable = false, updatable = false)
	private Login login;

	// bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy = "teacher")
	private List<Transaction> transactions;

	public Teacher() {
	}

	public String getIdTeacher() {
		return this.idTeacher;
	}

	public void setIdTeacher(String idTeacher) {
		this.idTeacher = idTeacher;
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

	public List<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course addCours(Course cours) {
		getCourses().add(cours);
		cours.setTeacher(this);

		return cours;
	}

	public Course removeCours(Course cours) {
		getCourses().remove(cours);
		cours.setTeacher(null);

		return cours;
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
		transaction.setTeacher(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setTeacher(null);

		return transaction;
	}

}