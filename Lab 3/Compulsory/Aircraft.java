package Compulsory;

public class Aircraft {
    private String model;
    private String name;
    private String tailNumber;
    private int id;
    public Aircraft(String model, String name, String tailNumber, int id) {
        this.model = model;
        this.name = name;
        this.tailNumber = tailNumber;
        this.id = id;
    }
    public String getModel() {
        return model;
    }
    public String getName() {
        return name;
    }
    public String getTailNumber() {
        return tailNumber;
    }
    public int getId() {
        return id;
    }
}
