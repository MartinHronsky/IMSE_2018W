package at.univie.cc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the bank_account database table.
 * 
 */
@Entity
@Table(name = "bank_account")
@NamedQuery(name = "BankAccount.findAll", query = "SELECT b FROM BankAccount b")
public class BankAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_name", unique = true, nullable = false, length = 45)
	private String userName;

	@Column(precision = 10, scale = 2)
	private BigDecimal amount;

	// bi-directional one-to-one association to Login
	@OneToOne
	@JoinColumn(name = "user_name", nullable = false, insertable = false, updatable = false)
	private Login login;

	public BankAccount() {
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}