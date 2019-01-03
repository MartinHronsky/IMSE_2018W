package at.univie.cc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the login database table.
 * 
 */
@Entity
@Table(name = "login")
@NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_name", unique = true, nullable = false, length = 45)
	private String userName;

	@Column(nullable = false, length = 30)
	private String password;

	// bi-directional one-to-one association to BankAccount
	@OneToOne(mappedBy = "login")
	private BankAccount bankAccount;

	// bi-directional one-to-one association to Organization
	@OneToOne(mappedBy = "login")
	private Organization organization;

	// bi-directional one-to-one association to Student
	@OneToOne(mappedBy = "login")
	private Student student;

	// bi-directional one-to-one association to Teacher
	@OneToOne(mappedBy = "login")
	private Teacher teacher;

	public Login() {
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BankAccount getBankAccount() {
		return this.bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}