package homework;
/**
 * Clasa Problem este utilizată pentru procesarea și gestionarea datelor despre studenți, profesori și proiecte.
 * Această clasă elimină dublurile din listele de studenți, profesori si proiecte.
 * De asemenea, asignează proiecte studenților și creează o listă de persoane
 * ce conține atât studenți cât și profesori.
 *
 * @author Alexandru Gavril
 */
public class Problem {
    private Student[] s; //Array de studenti
    private Teacher[] t; //Array de profesori
    private Person[] persons; //Array de persoane
    private int studentSize; //Dimensiunea array-ului de studenti
    private int tSize; //Dimensiunea array-ului de profesori
    private int pSize; //Dimensiunea array-ului de persoane
    /**
     * Constructorul clasei Problem.
     * Inițializează obiectul Problem și procesează listele de studenți și profesori.
     *
     * @param students Array de obiecte de tip Student.
     * @param teachers Array de obiecte de tip Teacher.
     */
    public Problem(Student[] students, Teacher[] teachers){
        Process(students,teachers);
    }
    /**
     * Metoda Process elimină dublurile din listele de studenți,profesori și proiecte.
     * De asemenea, asignează proiecte studenților și creează o listă de persoane.
     *
     * @param students Array de obiecte de tip Student.
     * @param teachers Array de obiecte de tip Teacher.
     */
    public void Process(Student[] students, Teacher[] teachers) {
        // Inițializare array-uri
         s = new Student[students.length];
         t = new Teacher[teachers.length];
         persons = new Person[students.length + teachers.length];
        Teacher[] aux = new Teacher[teachers.length];
        studentSize = 0;
        int teacherSize = 0;
        s[0] = students[0];
        studentSize++;
        // Eliminarea dublurilor din lista de studenți.
        for (int i = 1; i < students.length; i++) {
            int ok = 1;
            for (int j = 0; j < studentSize; j++) {
                if (students[i] != null && s[j].equals(students[i])) ok = 0;
            }
            if (ok == 1 && students[i] != null) {
                s[studentSize] = students[i];
                studentSize++;
            }
        }
        aux[0] = teachers[0];
        teacherSize++;
        // Eliminarea dublurilor din lista de profesori.
        for (int i = 1; i < teachers.length; i++) {
            int ok = 1;
            for (int j = 0; j < teacherSize; j++) {
                if (teachers[i] != null && aux[j].equals(teachers[i])) ok = 0;
            }
            if (ok == 1 && teachers[i] != null) {
                aux[teacherSize] = teachers[i];
                teacherSize++;
            }
        }
        Project[] list = aux[0].getProjectList();
        Project[] p = new Project[teacherSize * list.length];
        int projectSize = 0;
        // Crearea unei liste ce conține proiectele tuturor profesorilor.
        for (int i = 0; i < teacherSize; i++) {
            list = aux[i].getProjectList();
            for (int j = 0; j < list.length; j++) {
                p[projectSize] = list[j];
                projectSize++;
            }
        }
        // Eliminarea dublurilor din lista de proiecte.
        for (int i = 0; i < projectSize - 1; i++) {
            for (int j = i + 1; j < projectSize; j++) {
                if (p[i] != null && p[j] != null && p[i].equals(p[j])) p[j] = null;
            }
        }
        Project[] temp = new Project[list.length];
        tSize = 0;
        int index = 0;
        // Crearea listei finale de profesori.
        for (int i = 0; i < p.length; i++) {
            if (p[i] != null) {
                temp[index] = p[i];
                index++;
            }
            if ((i + 1) / list.length == tSize + 1) {
                t[tSize] = new Teacher(aux[tSize].getName(), aux[tSize].getDateOfBirth(), temp);
                index = 0;
                tSize++;
                temp = new Project[list.length];
            }
        }
        int i = 0;
        int j = 0;
        // Asignarea proiectelor studenților.
        while (i < studentSize && j < p.length) {
            while (j < p.length && p[j] == null) j++;
            if (j != p.length) s[i].setProject(p[j]);
            i++;
            j++;
        }
        pSize=0;
        // Crearea listei de persoane
        for(i = 0;i<studentSize;i++){
            if(s[i]!=null){
                persons[pSize] = s[i];
                pSize++;
            }
        }
        for(i = 0;i<tSize;i++){
            if(t[i]!=null){
                persons[pSize] = t[i];
                pSize++;
            }
        }
    }
    /**
     * Returnează array-ul de persoane.
     *
     * @return Array de obiecte de tip Person.
     */
    public Person[] getPersons() {
        return persons;
    }
    /**
     * Returnează dimensiunea array-ului de persoane.
     *
     * @return Dimensiunea array-ului de persoane.
     */
    public int getPersonsSize() {
        return pSize;
    }
}
