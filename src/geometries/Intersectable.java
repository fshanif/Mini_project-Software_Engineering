/**
 * 
 */
package geometries;

import java.util.List;
//import static geometries.Intersectable.GeoPoint;

import primitives.Point3D;
import primitives.Ray;

/**
 * @author elina and shani 
 * Intersectable is a common interface for all geometries that are able
 * to intersect from a ray to their entity (Shape)
 * findIntsersections- list of Intsersections points
 */
public interface Intersectable {
	
	List<GeoPoint> findIntsersections(Ray ray);

	/**
     * GeoPoint is inner class
     * references to a specific point and a specific geometry
     */
	public static class GeoPoint {
	    public Geometry geometry;
	    public Point3D point;
	    
	    /**
	     * constructor with two parameters
	     * @param _geometry- geometry
	     * @param pt
	     */
	    public GeoPoint(Geometry _geometry, Point3D pt) {
            this.geometry = _geometry;
            point = pt;
        }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((geometry == null) ? 0 : geometry.hashCode());
			result = prime * result + ((point == null) ? 0 : point.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			if (geometry == null) {
				if (other.geometry != null)
					return false;
			} else if (!geometry.equals(other.geometry))
				return false;
			if (point == null) {
				if (other.point != null)
					return false;
			} else if (!point.equals(other.point))
				return false;
			return true;
		}

	
	 
	}

}

