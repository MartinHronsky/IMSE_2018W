package at.univie.imse.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the bank_account database table.
 * 
 */
@Entity
@Table(name="bank_account")
@NamedQuery(name="BankAccount.findAll", query="SELECT b FROM BankAccount b")
public class BankAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_name", unique=true, nullable=false, length=255)
	private String userName;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal amount;

	@Column(name="card_number", nullable=false, precision=16)
	private BigDecimal cardNumber;

	//bi-directional one-to-one association to Login
	@OneToOne
	@JoinColumn(name="user_name", nullable=false, insertable=false, updatable=false)
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

	public BigDecimal getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(BigDecimal cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}