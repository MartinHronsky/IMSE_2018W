package at.univie.imse.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the schedule database table.
 * 
 */
@Entity
@Table(name="schedule")
@NamedQuery(name="Schedule.findAll", query="SELECT s FROM Schedule s")
public class Schedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SchedulePK id;

	@Column(length=100)
	private String note;

	//bi-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name="id_course", nullable=false, insertable=false, updatable=false)
	private Course course;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="id_location", nullable=false, insertable=false, updatable=false)
	private Location location;

	public Schedule() {
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

}