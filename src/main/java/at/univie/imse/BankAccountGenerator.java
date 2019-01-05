package at.univie.imse;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//Credit Card numbers from https://ccardgenerator.com/credit-card-number-list.php
public class BankAccountGenerator {

	public BankAccountGenerator() {
		try {
			cc = Files.readAllLines(Paths.get("src/main/resources/credit_cards.txt"), StandardCharsets.UTF_8);
			for (int i = 0; i < 250; i++) {
				creditCards.add(new BigInteger(cc.get(i)));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private Random rand = new Random();
	private List<String> cc = Collections.emptyList();
	private List<BigInteger> creditCards = new ArrayList<>();

	public BigInteger getRandomCreditCardNumber() {
		return creditCards.get(rand.nextInt(creditCards.size()));
	}

	public BigDecimal getRandomAmount() {
		Double amount = rand.nextDouble() * (3000 - 150) + 150;
		return new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
	}


}
