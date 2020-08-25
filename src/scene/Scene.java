/**
 * 
 */
package scene;

import java.util.LinkedList;
import java.util.List;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

/**
 * @author shani and elina
 * @param _name- name of the scene
 * @param _background- color of the _background of the scene
 * @param _ambientLight -of the scene
 * @param _geometries- the geometries that we have in the scene
 * @param _camera-the camera that includes all the parametrs of the camera
 * @param _distance- from the camera to the scene
 * @param _lights- list of lights
 * 
 */
public class Scene {

	/**
	 * 
	 */
	
	
	private String _name;
	private Color _background;
	private AmbientLight _ambientLight;
	private Geometries _geometries= new Geometries();
	private Camera _camera;
	private double _distance;
	private List<LightSource> _lights=new LinkedList<LightSource>();
	private int _numOfRays;
	


	/**
	 * @return the _numOfRays
	 */
	public int get_numOfRays() {
		return _numOfRays;
	}



	/**
	 * @param _numOfRays the _numOfRays to set
	 */
	public void set_numOfRays(int _numOfRays) {
		this._numOfRays = _numOfRays;
	}



	/**
	 * @param _name - the name of the scene
	 */
	public Scene(String name) {
		this._name = name;
	}
//	

	

	/**
	 * @return the _name
	 */
	public String get_name() {
		return _name;
	}


	/**
	 * get function
	 * @return the _lights
	 */
	public List<LightSource> get_lights() {
		return _lights;
	}




	/**
	 * @return the _background
	 */
	public Color get_background() {
		return _background;
	}


	/**
	 * @param _background the _background to set
	 */
	public void setBackground(Color _background) {
		this._background = _background;
	}


	/**
	 * @return the _ambientLight
	 */
	public AmbientLight get_ambientLight() {
		return _ambientLight;
	}


	/**
	 * @param _ambientLight the _ambientLight to set
	 */
	public void setAmbientLight(AmbientLight _ambientLight) {
		this._ambientLight = _ambientLight;
	}


	/**
	 * @return the _geometries
	 */
	public Geometries get_geometries() {
		return _geometries;
	}


	


	/**
	 * @return the _camera
	 */
	public Camera get_camera() {
		return _camera;
	}


	/**
	 * @param _camera the _camera to set
	 */
	public void setCamera(Camera _camera) {
		this._camera = _camera;
	}


	/**
	 * @return the _distance
	 */
	public double get_distance() {
		return _distance;
	}


	/**
	 * @param _distance the _distance to set
	 */
	public void setDistance(double _distance) {
		this._distance = _distance;
	}
	
	
	public void addGeometries(Intersectable... intersectables) {
        for (Intersectable i : intersectables) {
            _geometries.add(i);   }		
        }
	/**
	 * function that adds lights to the list of lights
	 * @param lights -to add to the list of lights
	 */
	public void addLights(LightSource... lights) { 
		
		for (LightSource i : lights) {
            _lights.add(i);   }
	}
}
