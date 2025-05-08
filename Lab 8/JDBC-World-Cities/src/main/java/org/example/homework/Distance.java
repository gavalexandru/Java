package org.example.homework;

public class Distance {
    private double answer;
    public Distance(City city1, City city2) {
        calculateDistance(city1.getLatitude(), city1.getLongitude(), city2.getLatitude(), city2.getLongitude());
    }
    public double toRadians(double degree) {
        double one_deg = (Math.PI / 180);
        return (degree*one_deg);
    }
    public void calculateDistance(double lat1, double long1, double lat2, double long2) {
        lat1 = toRadians(lat1);
        long1 = toRadians(long1);
        lat2 = toRadians(lat2);
        long2 = toRadians(long2);
        double dlong = long2 - long1;
                double dlat = lat2 - lat1;
        answer = Math.pow(Math.sin(dlat/2),2)+Math.cos(lat1)*Math.cos(lat2)*Math.pow(Math.sin(dlong/2),2);
        answer = 2*Math.asin(Math.sqrt(answer));
        double r = 6371;
        answer = answer * r;
    }
    public double getAnswer() {
        return answer;
    }
}
