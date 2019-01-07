package at.univie.imse.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the course database table.
 * 
 */
@Entity
@Table(name="course")
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_course", unique=true, nullable=false, length=255)
	private String idCourse;

	@Column(nullable=false, length=255)
	private String language;

	@Column(nullable=false, length=255)
	private String note;

	@Column(precision=10, scale=2)
	private BigDecimal price;

	@Column(name="type_of_the_course", nullable=false, length=255)
	private String typeOfTheCourse;

	//bi-directional many-to-one association to Assignment
	@OneToMany(mappedBy="course")
	private List<Assignment> assignments;

	//bi-directional many-to-one association to Teacher
	@ManyToOne
	@JoinColumn(name="id_teacher", nullable=false)
	private Teacher teacher;

	//bi-directional many-to-one association to Schedule
	@OneToMany(mappedBy="course")
	private List<Schedule> schedules;

	public Course() {
	}

	public String getIdCourse() {
		return this.idCourse;
	}

	public void setIdCourse(String idCourse) {
		this.idCourse = idCourse;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTypeOfTheCourse() {
		return this.typeOfTheCourse;
	}

	public void setTypeOfTheCourse(String typeOfTheCourse) {
		this.typeOfTheCourse = typeOfTheCourse;
	}

	public List<Assignment> getAssignments() {
		return this.assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public Assignment addAssignment(Assignment assignment) {
		getAssignments().add(assignment);
		assignment.setCourse(this);

		return assignment;
	}

	public Assignment removeAssignment(Assignment assignment) {
		getAssignments().remove(assignment);
		assignment.setCourse(null);

		return assignment;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Schedule> getSchedules() {
		return this.schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public Schedule addSchedule(Schedule schedule) {
		getSchedules().add(schedule);
		schedule.setCourse(this);

		return schedule;
	}

	public Schedule removeSchedule(Schedule schedule) {
		getSchedules().remove(schedule);
		schedule.setCourse(null);

		return schedule;
	}

}