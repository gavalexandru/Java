package Compulsory;
/**
 * Autor: Alexandru Gavril
 */
public enum projectType {
    theoretical,practical;
}
class Project{
     private String name;
     private projectType type;
     public Project(String name, projectType type) {
        this.name = name;
        this.type = type;
     }
     public String getName() {
        return name;
     }
     public void setName(String name) {
         this.name = name;
     }
     public projectType getType() {
        return type;
     }
     public void setType(projectType type) {
         this.type = type;
     }
     @Override
     public String toString() {
         return "Numele proiectului este: " + name + ", iar tipul acestuia este: " + type;
     }
}

