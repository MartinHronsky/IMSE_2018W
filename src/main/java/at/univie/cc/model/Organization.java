package at.univie.cc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the organization database table.
 * 
 */
@Entity
@Table(name = "organization")
@NamedQuery(name = "Organization.findAll", query = "SELECT o FROM Organization o")
public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_organization", unique = true, nullable = false, length = 45)
	private String idOrganization;

	@Column(nullable = false, length = 45)
	private String name;

	// bi-directional one-to-one association to Login
	@OneToOne
	@JoinColumn(name = "id_organization", nullable = false, insertable = false, updatable = false)
	private Login login;

	public Organization() {
	}

	public String getIdOrganization() {
		return this.idOrganization;
	}

	public void setIdOrganization(String idOrganization) {
		this.idOrganization = idOrganization;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}