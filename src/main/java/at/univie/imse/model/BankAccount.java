package at.univie.imse.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

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

	@Column(precision = 16)
	private BigInteger card_number;

	// bi-directional one-to-one association to Login
	@OneToOne
	@JoinColumn(name = "user_name", nullable = false, insertable = false, updatable = false)
	private Login login;

	public BankAccount() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigInteger getCard_number() {
		return card_number;
	}

	public void setCard_number(BigInteger card_number) {
		this.card_number = card_number;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}