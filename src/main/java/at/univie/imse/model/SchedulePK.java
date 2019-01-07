package at.univie.imse.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the schedule database table.
 * 
 */
@Embeddable
public class SchedulePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_course", insertable=false, updatable=false, unique=true, nullable=false, length=255)
	private String idCourse;

	@Column(name="id_location", insertable=false, updatable=false, unique=true, nullable=false, length=255)
	private String idLocation;

	@Temporal(TemporalType.DATE)
	@Column(name="date_from", unique=true, nullable=false)
	private java.util.Date dateFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="date_to", unique=true, nullable=false)
	private java.util.Date dateTo;

	public SchedulePK() {
	}
	public String getIdCourse() {
		return this.idCourse;
	}
	public void setIdCourse(String idCourse) {
		this.idCourse = idCourse;
	}
	public String getIdLocation() {
		return this.idLocation;
	}
	public void setIdLocation(String idLocation) {
		this.idLocation = idLocation;
	}
	public java.util.Date getDateFrom() {
		return this.dateFrom;
	}
	public void setDateFrom(java.util.Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public java.util.Date getDateTo() {
		return this.dateTo;
	}
	public void setDateTo(java.util.Date dateTo) {
		this.dateTo = dateTo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SchedulePK)) {
			return false;
		}
		SchedulePK castOther = (SchedulePK)other;
		return 
			this.idCourse.equals(castOther.idCourse)
			&& this.idLocation.equals(castOther.idLocation)
			&& this.dateFrom.equals(castOther.dateFrom)
			&& this.dateTo.equals(castOther.dateTo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCourse.hashCode();
		hash = hash * prime + this.idLocation.hashCode();
		hash = hash * prime + this.dateFrom.hashCode();
		hash = hash * prime + this.dateTo.hashCode();
		
		return hash;
	}
}