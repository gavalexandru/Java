package homework;
/**
 * Clasa Project reprezintă un proiect cu un nume și un tip.
 * Un proiect poate fi de tip practic sau teoretic, așa cum este definit în enum-ul {@link ProjectType}.
 *
 * @author Alexandru Gavril
 */
class Project {
    private String name;
    private ProjectType type;
    /**
     * Construiește un nou obiect Project.
     *
     * @param name Numele proiectului.
     * @param type Tipul proiectului.
     */
    public Project(String name, ProjectType type) {
        this.name = name;
        this.type = type;
    }
    /**
     * Returnează numele proiectului.
     *
     * @return Numele proiectului.
     */
    public String getName() {
        return name;
    }
    /**
     * Returnează tipul proiectului.
     *
     * @return Tipul proiectului.
     */
    public ProjectType getType() {
        return type;
    }
    /**
     * Verifică dacă acest proiect este egal cu un alt obiect.
     * Două proiecte sunt considerate egale dacă au același nume și același tip.
     *
     * @param obj Obiectul cu care se compară.
     * @return `true` dacă obiectele sunt egale, `false` în caz contrar.
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || this.getClass() != obj.getClass()) return false;
        Project project = (Project) obj;
        if(this.getName() == project.getName() && this.getType() == project.getType()) return true;
        return false;
    }
}
