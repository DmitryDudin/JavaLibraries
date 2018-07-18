import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class HaversineTest {

    //Returns the distance in kilometers on the Earth between two known points of latitude and longitude
    public static Double haversine(double lat1, double lng1,
                                   double lat2, double lng2) {
        return 111.045 * Math.toDegrees(Math.acos(
                Math.cos(Math.toRadians(lat1)) *
                        Math.cos(Math.toRadians(lat2)) *
                        Math.cos(Math.toRadians(lng2) - Math.toRadians(lng1)) +
                        Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))));
    }

    @Test
    public void simpleCalc() {
        //google = 44m
//point1
        double lat1 = 50.55735;
        double lng1 = 30.30445;
//point2
        double lat2 = 50.55723;
        double lng2 = 30.30386;
        Double haversine = HaversineTest.haversine(lat1, lng1, lat2, lng2);
        System.out.println("haversine= " + haversine);
    }

    @Test
    public void simpleCalcWithRadius() {

        //google = 110m
//point1
        double lat1 = 50.55711;
        double lng1 = 30.30313;
//point2
        double lat2 = 50.55727;
        double lng2 = 30.30181;
        Double haversine = HaversineTest.haversine(lat1, lng1, lat2, lng2);
        System.out.println("haversine= " + haversine);

        List<Point> points = Arrays.asList(new Point(50.55711, 30.30313),
                new Point(50.55699, 30.30236),
                new Point(50.55707, 30.30217),
                new Point(50.55727, 30.30181)
        );
        Double haversine1 = 0d;
        for (int i = 0; i < points.size() - 1; i++) {
            haversine1 = haversine1 + haversine(points.get(i).getLat(), points.get(i).getLng(),
                    points.get(i + 1).getLat(), points.get(i + 1).getLng());
        }
        System.out.println("haversine1= " + haversine1);
    }

    class Point {
        double lat;
        double lng;

        public double getLat() {
            return lat;
        }

        public Point(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }
}
