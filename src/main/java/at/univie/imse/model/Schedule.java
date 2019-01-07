package at.univie.imse.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the schedule database table.
 * 
 */
@Entity
@Table(name = "schedule")
@NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s")
public class Schedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SchedulePK id;

	@Column(length = 255)
	private String note;

	// bi-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name = "id_course", nullable = false, insertable = false, updatable = false)
	private Course course;

	// bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name = "id_location", nullable = false, insertable = false, updatable = false)
	private Location location;

	// bi-directional many-to-many association to Student
	@ManyToMany(mappedBy = "schedules")
	private List<Student> students;

	public Schedule() {
		this.students = new ArrayList<Student>();
	}

	public SchedulePK getId() {
		return this.id;
	}

	public void setId(SchedulePK id) {
		this.id = id;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

}