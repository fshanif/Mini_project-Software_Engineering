/**
 * 
 */
package elements;

import primitives.Color;

/**
 * @author shani and elina
 * abstract class for light 
 *
 */
public abstract class Light {

	/**
	 * value of intensity 
	 */
	protected Color _intensity;

	/**
	 * constructor with one parameter: _intensity 
	 * @param _intensity - intensity of light
	 */
	public Light(Color _intensity) {
		this._intensity = _intensity;
	}

	/**
	 * get function for :_intensity
	 * @return the _intensity
	 */
	public Color get_intensity() {
		return _intensity;
	}

	
	
}
