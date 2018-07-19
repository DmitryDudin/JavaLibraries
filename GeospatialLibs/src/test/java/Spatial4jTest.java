import org.junit.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.context.SpatialContextFactory;
import org.locationtech.spatial4j.context.jts.JtsSpatialContext;
import org.locationtech.spatial4j.distance.DistanceCalculator;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.ShapeFactory;

import static org.junit.Assert.assertTrue;

public class Spatial4jTest {

    @Test
    public void simple() {
//        JtsSpatialContext.GEO
        //spatial4j= 43,76m

        SpatialContext spatialContext = SpatialContext.GEO;
        DistanceCalculator distCalc = spatialContext.getDistCalc();
        ShapeFactory shapeFactory = spatialContext.getShapeFactory();

        //point1
        double lat1 = 50.55735;
        double lon1 = 30.30445;
//point2
        double lat2 = 50.55723;
        double lon2 = 30.30386;

        Point point0 = spatialContext.makePoint(lon1, lat1);
        Point point1 = shapeFactory.pointXY(lon1, lat1);
        Point point2 = shapeFactory.pointXY(lon2, lat2);

//        Point point1_1 = shapeFactory.pointXY(lat1, lon1);//worng !! , right is ->(lon, lat)
//        Point point2_1 = shapeFactory.pointXY(lat2, lon2);

        System.out.println("isGeo= " + spatialContext.isGeo());
        System.out.println(point1.equals(point0));
        assertTrue(point1.equals(point0));

        double distance = distCalc.distance(point1, point2);
        System.out.println("degree distance= " + distance);
        System.out.println("km distance= " + distance * DistanceUtils.DEG_TO_KM);//43.7m

        double distance1 = spatialContext.calcDistance(point1, point2);
        System.out.println("degree distance1= " + distance1);
        System.out.println("km distance1= " + distance1 * DistanceUtils.DEG_TO_KM);

//        double distance_1 = distCalc.distance(point1_1, point2_1);
//        System.out.println("degree distance_1= " + distance_1);
//        System.out.println("km distance_1= " + distance_1 * DistanceUtils.DEG_TO_KM);


//        DistanceUtils.dist2Degrees()

    }

}
