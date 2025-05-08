package org.example.homework;

public class Main {
    public static void main(String[] args) {
        String path ="C:\\Users\\Mesul\\IdeaProjects\\JDBC-World-Cities\\data.txt";
        DatabaseConnectionPool dataSource = new DatabaseConnectionPool();
        City city1 = new City("Iasi",1,false,53.32055555555556,-1.7297222222222221);
        City city2 = new City("Bucuresti",2,true,53.31861111111111,-1.6997222222222223);
        Distance d = new Distance(city1,city2);
        double answer = d.getAnswer();
        System.out.println("Distance: " + answer);
        Import importt = new Import(path, dataSource);
        dataSource.closePool();
    }
}