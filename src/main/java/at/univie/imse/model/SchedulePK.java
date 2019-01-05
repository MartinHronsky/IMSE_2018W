package at.univie.imse.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;

/**
 * The primary key class for the schedule database table.
 * 
 */
@Embeddable
public class SchedulePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "id_course", insertable = false, updatable = false, unique = true, nullable = false, length = 45)
	private String idCourse;

	@Column(name = "id_location", insertable = false, updatable = false, unique = true, nullable = false, length = 45)
	private String idLocation;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_from", unique = true, nullable = false)
	private java.util.Date dateFrom;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_to", unique = true, nullable = false)
	private java.util.Date dateTo;

	public SchedulePK() {
	}

	public String getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(String idCourse) {
		this.idCourse = idCourse;
	}

	public String getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(String idLocation) {
		this.idLocation = idLocation;
	}

	public java.util.Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(java.util.Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public java.util.Date getDateTo() {
		return dateTo;
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
		SchedulePK castOther = (SchedulePK) other;
		return idCourse.equals(castOther.idCourse) && idLocation.equals(castOther.idLocation)
				&& dateFrom.equals(castOther.dateFrom) && dateTo.equals(castOther.dateTo);
	}

	public int hashCode() {
		int prime = 31;
		int hash = 17;
		hash = hash * prime + idCourse.hashCode();
		hash = hash * prime + idLocation.hashCode();
		hash = hash * prime + dateFrom.hashCode();
		hash = hash * prime + dateTo.hashCode();

		return hash;
	}
}