package at.univie.imse.model;

import javax.persistence.*;
import java.io.Serializable;

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
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}