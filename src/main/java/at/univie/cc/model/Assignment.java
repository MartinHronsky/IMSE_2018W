package at.univie.cc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the assignment database table.
 * 
 */
@Entity
@Table(name = "assignment")
@NamedQuery(name = "Assignment.findAll", query = "SELECT a FROM Assignment a")
public class Assignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AssignmentPK id;

	@Column(name = "id_transaction", nullable = false)
	private Integer idTransaction;

	// bi-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name = "id_course", nullable = false, insertable = false, updatable = false)
	private Course course;

	// bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name = "id_student", nullable = false, insertable = false, updatable = false)
	private Student student;

	public Assignment() {
	}

	public AssignmentPK getId() {
		return this.id;
	}

	public void setId(AssignmentPK id) {
		this.id = id;
	}

	public Integer getIdTransaction() {
		return this.idTransaction;
	}

	public void setIdTransaction(Integer idTransaction) {
		this.idTransaction = idTransaction;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}