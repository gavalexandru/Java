package homework;
/**
 * Clasa Teacher reprezintă un profesor cu un nume, o dată de naștere și o listă de proiecte propuse.
 * Această clasă extinde clasa {@link Person}.
 *
 * @author Alexandru Gavril
 */
public class Teacher extends Person {
    private Project[] projectList;
    /**
     * Construiește un nou obiect Teacher.
     *
     * @param name        Numele profesorului.
     * @param dateOfBirth Data de naștere a profesorului.
     * @param projectList Lista de proiecte propuse de profesor.
     */
    public Teacher(String name, String dateOfBirth, Project[] projectList) {
        super(name,dateOfBirth);
        this.projectList = projectList;
    }
    /**
     * Returnează lista de proiecte propuse de profesor.
     *
     * @return Lista de proiecte.
     */
    public Project[] getProjectList() {
        return projectList;
    }
    /**
     * Verifică dacă acest profesor este egal cu un alt obiect.
     * Doi profesori sunt considerați egali dacă au același nume și aceeași dată de naștere.
     *
     * @param obj Obiectul cu care se compară.
     * @return `true` dacă obiectele sunt egale, `false` în caz contrar.
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || this.getClass() != obj.getClass()) return false;
        Teacher t = (Teacher) obj;
        if(this.getName() == t.getName() && this.getDateOfBirth() == t.getDateOfBirth()) return true;
        return false;
    }
}
