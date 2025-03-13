package homework;
/**
 * Clasa Solution este utilizată pentru afișarea numelor persoanelor dintr-un array de obiecte de tip Person.
 *
 * @author Alexandru Gavril
 */
public class Solution {
    /**
     * Constructorul clasei Solution.
     * Inițializează obiectul Solution și apelează metoda Show pentru afișarea numelor persoanelor.
     *
     * @param persons Un array de obiecte de tip Person.
     * @param pSize Dimensiunea array-ului de persoane.
     */
    public Solution(Person[] persons, int pSize){
        Show(persons, pSize);
    }
    /**
     * Metoda Show afișează numele tuturor persoanelor din array-ul dat.
     *
     * @param persons Un array de obiecte de tip Person.
     * @param pSize Dimensiunea array-ului de persoane.
     */
    public void Show(Person[] persons, int pSize){
        for(int i=0;i<pSize;i++){
            System.out.println(persons[i].getName() + " ");
        }
    }
}
