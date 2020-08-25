/**
 * 
 */
package geometries;

import java.util.ArrayList;
import java.util.List;

//import primitives.Point3D;
import primitives.Ray;

/**
 * @author shani and elina
 * class of geometries that contains the list Intersectable geometries
 *listOfGeometries- list of geometries
 */
public class Geometries implements Intersectable {

	private List<Intersectable> listOfGeometries;
	/**
	 * 
	 */
	public Geometries() {
		listOfGeometries=new ArrayList<Intersectable>();
	}
	/**
	 * constructor that receives the geometries
	 * @param listOfGeometries
	 */
	public Geometries(Intersectable... geometries) 
	{
		listOfGeometries=new ArrayList<Intersectable>();
		for (int i = 0; i < geometries.length; i++)
		{
			listOfGeometries.add(geometries[i]);
		}
		
	
	}
	/**
	 * function that adds geometries to the list of geometries
	 * @param geometries
	 */
	public void add(Intersectable... geometries)
	{
		for (int i = 0; i < geometries.length; i++)
		{
			listOfGeometries.add(geometries[i]);
		}
	}
	
	 /**
     * function that calculates the intersection with geometry
     * @return the list of Intsersections points
     */
	public List<GeoPoint> findIntsersections (Ray ray){
		List<GeoPoint> intersection=null;
		if(this.listOfGeometries.isEmpty()==true)
			return null;
		 intersection=new ArrayList<GeoPoint>();
		for (int i = 0; i < listOfGeometries.size(); i++) {
			List<GeoPoint> help=listOfGeometries.get(i).findIntsersections(ray);
			if(help!=null)
				for (int j = 0; j < help.size(); j++) {
					if(intersection.contains(help.get(j))==false)
					    intersection.add(help.get(j));
				}	
		}
		if(intersection.isEmpty())
			return null;
		return intersection;

	}

	

}
