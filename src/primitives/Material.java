/**
 * 
 */
package primitives;

/**
 * @author shani and elina
 * class for the material of geometry it holds the information of the attenuation and 
 * the shining factors of geometry
 * data for  attenuation(_kD,_kS)
 * Shininess(_nShininess)
 * transparency-_kT
 * Reflects reflection-_kr
 */
public class Material {

	private final  double _kD;
	private final  double _kS;
	private final  int _nShininess;
	private final  double _kT ;
    private final  double _kr;

    
    public Material(Material material) {
        this(material._kD, material._kS, material._nShininess, material._kT, material._kr);
    }
	/**
	 * constructor with three parameters
	 * @param _kD-data for  attenuation
	 * @param _kS-data for  attenuation
	 * @param _nShininess
	 */
	public Material(double _kD, double _kS, int _nShininess) {
		this(_kD,_kS,_nShininess,0,0);
	}


	/**
	 * @param _kD-data for  attenuation
	 * @param _kS-data for  attenuation
	 * @param _nShininess-Shininess
	 * @param _kT-transparency
	 * @param _kr-Reflects reflection
	 * constructor with three parameters

	 */
	public Material(double _kD, double _kS, int _nShininess, double _kT, double _kr) {
		this._kD = _kD;
		this._kS = _kS;
		this._nShininess = _nShininess;
		this._kT = _kT;
		this._kr = _kr;
	}


	/**
	 * @return the _kD
	 * get function
	 */
	public double get_kD() {
		return _kD;
	}

	/**
	 * @return the _kS
	 * get function
	 */
	public double get_kS() {
		return _kS;
	}


	/**
	 * @return the _kT
	 * get function
	 */
	public double get_kT() {
		return _kT;
	}


	/**
	 * @return the _kr
	 * get function
	 */
	public double get_kr() {
		return _kr;
	}


	/**
	 * @return the _nShininess
	 * get function
	 */
	public int get_nShininess() {
		return _nShininess;
	}


	
	


}
