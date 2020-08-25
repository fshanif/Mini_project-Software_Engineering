/**
 * 
 */
package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author shani and elina
 * class of geometry that contains the color and the material
 *_emmission- color of geometry
 *_material- material of geometry
 */
public abstract class Geometry implements  Intersectable  {
	
	protected Color _emmission;
	protected Material _material;
	
	/**
	 * GET FUNCTION
	 * @return the _emmission
	 */
	public Color getEmmission() {
		return _emmission;
	}

	
	/**
	 * GET FUNCTION
	 * @return the _material
	 */
	public Material get_material() {
		return _material;
	}


	abstract public Vector getNormal (Point3D p);

	

	/**
	 * constructor with two parameters
	 * @param _emmission
	 * @param _material
	 */
	public Geometry(Color _emmission, Material _material) {
		this._emmission = new Color(_emmission);
		this._material = new Material(_material.get_kD(),_material.get_kS(),_material.get_nShininess(),_material.get_kT(),_material.get_kr());
	}


	/**
	 * constructor with one parameter 
	 * @param _emmission
	 */
	public Geometry(Color _emmission) {
		this(_emmission,new Material(0, 0, 0));
	}


	/**
	 * empty constructor
	 */
	public Geometry() {
		this( Color.BLACK,new Material(0, 0, 0));
		this._emmission = Color.BLACK;
		
	}
	
}

