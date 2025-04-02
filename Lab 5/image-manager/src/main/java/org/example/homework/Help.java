package org.example.homework;

import org.example.compulsory.Repository;

public class Help extends Command {
    public Help(Repository repository, String[] args) {
        super(repository, args);
        execute();
    }
    @Override
    public void execute() {
        System.out.println();
        System.out.println();
        System.out.println("Meniu");
        System.out.println();
        System.out.println();
        System.out.println("Add <name> <time> <tag_list> <img_path> - Adauga imaginea in repository.");
        System.out.println("Remove <img_path> - Elimina imaginea din repository.");
        System.out.println("Update <img_path> <tag_list> - Actualizeaza atributele unei imagini existente.");
        System.out.println("Load <file_path> - Incarca imagini dintr-un fisier extern.");
        System.out.println("Save <file_path> - Salveaza toate imaginile din repository intr-un fisier extern.");
        System.out.println("Report <output_file.html> - Genereaza un raport HTML cu toate imaginile din repository si il deschide in browser.");
        System.out.println("Help - Pentru a primi informatii despre comenzile disponibile.");
        System.out.println("Exit - Iesire din program.");
        System.out.println();
        System.out.println();
    }
}
