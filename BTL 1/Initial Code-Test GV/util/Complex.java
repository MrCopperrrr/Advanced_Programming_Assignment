/**
 * This class represents a complex number <code>z = x + y*i</code>
 */

public class Complex {
	private double mRe;
	private double mIm;
	
	/**
	 * Constructor 
	 * @param re real part
	 * @param im imaginary part
	 */
	public Complex(double re, double im){
		mRe = re;
		mIm = im;
	}
	
	/**
	 * Get the imaginary part
	 * @return value
	 */
	public double getIm() {
		return mIm;
	}
	
	/**
	 * Calculate the magnitude of the complex number. For number <code>z = x + y*i</code>, 
	 * <code>|z| = sqrt(x*x + y*y)</code>.
	 * @return the magnitude value.
	 */
	public double getMagnitude() {
		return Math.sqrt(mRe*mRe + mIm*mIm);
	}

	/**
	 * Get the real part
	 * @return value
	 */
	public double getRe() {
		return mRe;
	}
	
	/**
	 * Set the imaginary part
	 * @param im value
	 */
	public void setIm(double im) {
		mIm = im;
	}
	
	/**
	 * Set the real part
	 * @param re value
	 */
	public void setRe(double re) {
		mRe = re;
	}
	
	@Override
	public String toString() {
		return String.format("C{%f,%f}", mRe, mIm);
	}
}