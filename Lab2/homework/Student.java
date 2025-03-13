package homework;
/**
 * Clasa Student reprezintă un student cu un nume, o dată de naștere, un număr de înregistrare și un proiect asociat.
 * Această clasă extinde clasa {@link Person}.
 *
 * @author Alexandru Gavril
 */
public class Student extends Person {
    private int registrationNumber;
    private Project project;
    /**
     * Construiește un nou obiect Student.
     *
     * @param name               Numele studentului.
     * @param dateOfBirth        Data de naștere a studentului.
     * @param registrationNumber Numărul de înregistrare al studentului.
     */
    public Student(String name, String dateOfBirth, int registrationNumber) {
        super(name,dateOfBirth);
        this.registrationNumber = registrationNumber;
    }
    /**
     * Returnează numărul de înregistrare al studentului.
     *
     * @return Numărul de înregistrare al studentului.
     */
    public int getRegistrationNumber() {
        return registrationNumber;
    }
    /**
     * Asociază un proiect studentului.
     *
     * @param project Proiectul care va fi asociat studentului.
     */
    public void setProject(Project project) {
        this.project = project;
    }
    /**
     * Returnează proiectul asociat studentului.
     *
     * @return Proiectul asociat studentului.
     */
    public Project getProject() {
        return project;
    }
    /**
     * Verifică dacă acest student este egal cu un alt obiect.
     * Doi studenți sunt considerați egali dacă au același număr de înregistrare
     * sau același nume și aceeași dată de naștere.
     *
     * @param obj Obiectul cu care se compară.
     * @return `true` dacă obiectele sunt egale, `false` în caz contrar.
     */
    @Override
    public boolean equals(Object obj) {
      if(this == obj) return true;
      if(obj == null || this.getClass() != obj.getClass()) return false;
      Student s = (Student) obj;
      if(this.getRegistrationNumber() == s.getRegistrationNumber()) return true;
      if(this.getName() == s.getName() && this.getDateOfBirth() == s.getDateOfBirth()) return true;
      return false;
    }
}
