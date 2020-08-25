/**
 * 
 */
package elements;
import java.util.ArrayList;

//import primitives.Util;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/**
 * @author shani and elina
 * class that has information about the camera
 * _p0- the starting point of camera
 * _vUp- the vector the points to upwards
 * _vTo - vector that point towards
 * _vRight- the vector that points to the right side of camera
 */
public class Camera {


	private Point3D  _p0;
	private Vector _vUp;
	private Vector _vTo;
	private Vector _vRight;

	/**
	 * @return the _p0
	 */
	public Point3D get_p0() {
		return new Point3D(_p0);
	}

	/**
	 * @return the _vUp
	 */
	public Vector get_vUp() {
		return new Vector(_vUp);
	}

	/**
	 * @return the _vTo
	 */
	public Vector get_vTo() {
		return new Vector(_vTo);
	}

	/**
	 * @param _p0
	 * @param _vUp
	 * @param _vTo
	 */
	public Camera(Point3D p0, Vector vTo, Vector vUp) {
		super();
		this._p0 = new Point3D(p0);
		this._vUp = vUp.normalize();
		this._vTo = vTo.normalize();
		this._vRight=(_vTo.crossProduct(_vUp)).normalize();
	}


	/**
	 * function before improvement
	 * @param nX - number of pixels on the The x-axis
	 * @param nY -number of pixels on the The y-axis
	 * @param j-goes over the y-axis
	 * @param i -goes over the y-axis
	 * @param screenDistance - the distance from the camera to the screen
	 * @param screenWidth- the width of the screen
	 * @param screenHeight- the hight of the screen
	 * @return the ray from the camera to the wanted pixel
	 */

	public Ray constructRayThroughPixel (int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight) throws IllegalArgumentException 

	{
		 if (screenDistance==0) {
	            throw new IllegalArgumentException("distance cannot be 0");
	        }

       double Ry=screenHeight/nY;
       double Rx=screenWidth/nX;
       Point3D Pij;

       double Yi=(i-nY/2d)*Ry+(Ry/2d);
       double Xj=(j-nX/2d)*Rx+(Rx/2d); 

       Pij=_p0.add(_vTo.scale(screenDistance));
       if (Xj != 0) 
    	   Pij = Pij.add(this._vRight.scale(Xj));
       if (Yi != 0) 
    	   Pij = Pij.add(this._vUp.scale(-Yi));

       return new Ray(this._p0,Pij.subtract(_p0));
	}

	/**
	 * function after improvement
	 * @param raysToHeight- number of rays in pixel in y-axis
	 * @param raysToWidth- number of rays in pixel in x-axis
	 * @param nX - number of pixels on the The x-axis
	 * @param nY -number of pixels on the The y-axis
	 * @param j-goes over the y-axis
	 * @param i -goes over the y-axis
	 * @param screenDistance - the distance from the camera to the screen
	 * @param screenWidth- the width of the screen
	 * @param screenHeight- the hight of the screen
	 * @return the list of rays from the camera to the wanted pixel
	 */
	public ArrayList<Ray> _constructRayThroughPixel (int raysToHeight,int raysToWidth, int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight) throws IllegalArgumentException 
	{


		if (screenDistance==0) {
			throw new IllegalArgumentException("distance cannot be 0");
		}

		double Ry=screenHeight/nY;//pixel height
		double Rx=screenWidth/nX;// pixel's width
		Point3D Pij;
		ArrayList<Ray> theArray=new ArrayList<Ray>();
		if(raysToHeight==1&&raysToWidth==1)
		{
			double Yi=(i-nY/2d)*Ry+(Ry/2d);
			double Xj=(j-nX/2d)*Rx+(Rx/2d); 

			Pij=_p0.add(_vTo.scale(screenDistance));
			//if (Xj != 0) 
			if(!Util.isZero(Xj))
				Pij = Pij.add(this._vRight.scale(Xj));
			//if (Yi != 0) 
			if(!Util.isZero(Yi))
				Pij = Pij.add(this._vUp.scale(-Yi));
			theArray.add(new Ray(this._p0,Pij.subtract(_p0)));
			return theArray;
		}
		else
		{
			double scaleToHeight=Ry/raysToHeight;
			double scaleToWidth=Ry/raysToWidth;

			for(int x=0;x<raysToWidth;x++) {
				for(int y=0;y<raysToHeight;y++) {

					double Yi=(i-nY/2d)*Ry+scaleToHeight*y;
					double Xj=(j-nX/2d)*Rx+scaleToWidth*x; 

					Pij=_p0.add(_vTo.scale(screenDistance));
					if (Xj != 0) 
						Pij = Pij.add(this._vRight.scale(Xj));
					if (Yi != 0) 
						Pij = Pij.add(this._vUp.scale(-Yi));
					theArray.add(new Ray(this._p0,Pij.subtract(_p0)));
				} 		   
			}

			return theArray;
		}
	}

	

	/**
	 * @return the _vRight
	 */
	public Vector get_vRight() {
		return new Vector(_vRight);
	}


}
