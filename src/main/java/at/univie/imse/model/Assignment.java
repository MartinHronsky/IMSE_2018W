package at.univie.imse.model;

import javax.persistence.*;
import java.io.Serializable;

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
		return id;
	}

	public void setId(AssignmentPK id) {
		this.id = id;
	}

	public Integer getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(Integer idTransaction) {
		this.idTransaction = idTransaction;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}