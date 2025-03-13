package homework;
/**
 * Clasa person reprezinta o persoana cu un nume si o data de nastere.
 * Aceasta este o clasa de baza pentru alte clase cum ar fi: Student si Teacher.
 *
 * @author Alexandru Gavril
 */
public class Person {
    private String name;
    private String dateOfBirth;
    /**
     * Construiește un nou obiect Person.
     *
     * @param name        Numele persoanei.
     * @param dateOfBirth Data de naștere a persoanei.
     */
    public Person(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }
    /**
     * Returnează numele persoanei.
     *
     * @return Numele persoanei.
     */
    public String getName() {
        return name;
    }
    /**
     * Returnează data de naștere a persoanei.
     *
     * @return Data de naștere a persoanei.
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
