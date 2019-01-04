package at.univie.cc;


import java.time.LocalDate;
import java.util.Random;

public class GeneratePerson {

    private String login;
    private String password;
    private String name;
    private LocalDate DoB;

    private PersonInfoGenerator pG = new PersonInfoGenerator();
    private Random random = new Random();

    public GeneratePerson() {}

    public void generatePerson() {
        String fN = pG.getFirstNames();
        String lN = pG.getLastNames();
        login = fN + lN.substring(0, 1) + random.nextInt();
        name = fN + " " + lN;
        password = pG.getPassword();
        DoB = pG.getRandomDoB();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDoB() {
        return DoB;
    }
}
