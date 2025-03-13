package homework;
/**
 * Clasa Main este punctul de intrare in aplicatie.
 *
 * @author Alexandru Gavril
 */
public class Main {
    public static void main(String[] args) {
        //Initializare studenti
        Student[] students = new Student[10];
        students[0] = new Student("Alice", "01-10-2002", 1);
        students[1] = new Student("Bob", "01-10-2002", 2);
        students[2] = new Student("Carl", "01-10-2002", 3);
        students[3] = new Student("Dan", "01-10-2002", 4);
        students[4] = new Student("Dan", "01-10-2002", 5);
        students[5] = new Student("Mark", "01-10-2002", 6);
        //Initializare proiecte
        Project[][] projects = new Project[10][10];
        projects[0][0] = new Project("Programare avansata", ProjectType.practical);
        projects[0][1] = new Project("Programare orientata obiect", ProjectType.practical);
        projects[1][0] = new Project("Structuri de date", ProjectType.theoretical);
        projects[1][1] = new Project("Teoria numerelor", ProjectType.theoretical);
        projects[2][0] = new Project("Programare avansata", ProjectType.practical);
        //Initializare profesori
        Teacher[] teachers = new Teacher[10];
        teachers[0] = new Teacher("Andrei","01-02-1974", projects[0]);
        teachers[1] = new Teacher("Maria","01-02-1974", projects[1]);
        teachers[2] = new Teacher("Carina","01-02-1974", projects[2]);
        teachers[3] = new Teacher("Andrei","01-02-1974", projects[0]);
        teachers[4] = new Teacher("Dana","01-02-1974", projects[0]);
        //Crearea unei probleme si a unei solutii
        Problem p = new Problem(students,teachers);
        Solution s = new Solution(p.getPersons(),p.getPersonsSize());
    }
}
