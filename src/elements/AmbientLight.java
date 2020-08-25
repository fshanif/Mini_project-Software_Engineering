/**
 * 
 */
package elements;

import primitives.Color;

/**
 * @author shani and elina
 * class AmbientLight that calculates the _intensity
 *
 */
public class AmbientLight extends  Light {

	/**
	 * calculates the ambient light
	 * @param _intensity - intensity of light
	 * @param KA - the Exclusion factor
	 */
	public AmbientLight(Color _intensity,double KA) {
		super(_intensity.scale(KA));
	}


}
