package at.univie.imse.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@Table(name = "transaction")
@NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_transaction", unique = true, nullable = false)
	private Integer idTransaction;

	@Column(precision = 10, scale = 2)
	private BigDecimal amount;

	@Column(nullable = false, length = 1)
	private String state;

	// bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name = "id_student", nullable = false)
	private Student student;

	// bi-directional many-to-one association to Teacher
	@ManyToOne
	@JoinColumn(name = "id_teacher", nullable = false)
	private Teacher teacher;

	public Transaction() {
	}

	public Integer getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(Integer idTransaction) {
		this.idTransaction = idTransaction;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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