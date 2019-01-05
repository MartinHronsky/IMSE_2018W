package at.univie.imse.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the location database table.
 * 
 */
@Entity
@Table(name = "location")
@NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_location", unique = true, nullable = false, length = 45)
	private String idLocation;

	@Column(length = 100)
	private String note;

	@Column(name = "room_capacity", nullable = false)
	private Integer roomCapacity;

	// bi-directional many-to-one association to Schedule
	@OneToMany(mappedBy = "location")
	private List<Schedule> schedules;

	public Location() {
	}

	public String getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(String idLocation) {
		this.idLocation = idLocation;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getRoomCapacity() {
		return roomCapacity;
	}

	public void setRoomCapacity(Integer roomCapacity) {
		this.roomCapacity = roomCapacity;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public Schedule addSchedule(Schedule schedule) {
		getSchedules().add(schedule);
		schedule.setLocation(this);

		return schedule;
	}

	public Schedule removeSchedule(Schedule schedule) {
		getSchedules().remove(schedule);
		schedule.setLocation(null);

		return schedule;
	}

}