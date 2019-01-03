package at.univie.cc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the assignment database table.
 * 
 */
@Embeddable
public class AssignmentPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "id_course", insertable = false, updatable = false, unique = true, nullable = false, length = 45)
	private String idCourse;

	@Column(name = "id_student", insertable = false, updatable = false, unique = true, nullable = false, length = 45)
	private String idStudent;

	public AssignmentPK() {
	}

	public String getIdCourse() {
		return this.idCourse;
	}

	public void setIdCourse(String idCourse) {
		this.idCourse = idCourse;
	}

	public String getIdStudent() {
		return this.idStudent;
	}

	public void setIdStudent(String idStudent) {
		this.idStudent = idStudent;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AssignmentPK)) {
			return false;
		}
		AssignmentPK castOther = (AssignmentPK) other;
		return this.idCourse.equals(castOther.idCourse) && this.idStudent.equals(castOther.idStudent);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCourse.hashCode();
		hash = hash * prime + this.idStudent.hashCode();

		return hash;
	}
}