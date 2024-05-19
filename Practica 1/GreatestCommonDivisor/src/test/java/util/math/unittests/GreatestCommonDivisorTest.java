package util.math.unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import util.math.GreatestCommonDivisor;

/**
 * Checks properties of the greatest common divisor algorithms.
 * It is implemented using jUnit's parameterized tests.
 * 
 * @author scilingo
 *
 */

@RunWith(Parameterized.class)
public class GreatestCommonDivisorTest {

	private int m;
	private int n;

	/**
	 * Default constructor for parameterized tests.
	 * Sets elements from Parameters into the variables for input.
	 * @param m is the first input value for gcd algorithm.
	 * @param n is the second input value for gcd algorithm.
	 */
	public GreatestCommonDivisorTest(int m, int n){
		this.m = m;
		this.n = n;
	}
	
	/**
	 * Contains the integer values to use for tests.
	 * This method contains the inputs to be used for testing.
	 * New tests are added by adding new inputs here.
	 * See test methods in this class, for actual properties
	 * being checked.
	 * @return an array of integer arrays, used as parameters for tests.
	 */
	@Parameters
	public static Collection<Object[]> parameters(){
		return Arrays.asList(new Object[][] {
			{ 4, 3 },
			{ 73, 37},
			{ 21, 12},
			{ 3, 7},
			{ 100, 7},
			{ 0 , 39},
			{ 39 , 0},
			{ 60, 20},
			{ 76059, 8451},
			{ 71419401, 8451},
			{ 8451, 71419401},
			{ 60398451, 70398451},
			{ 100328451, 70398451},
		});
	}

	/**
	 * Checks whether definition based algorithm correctly compute gcd.
	 * This test uses differential testing, comparing with the output of
	 * euclid algorithm.
	 * This is a parameterized unit test. See parameters elsewhere in this class.
	 */
	@Test
	public void test(){
		int gcd = GreatestCommonDivisor.euclidAlgorithm(m,n);
		assertTrue(gcd == GreatestCommonDivisor.definitionBasedAlgorithm(m,n));
	}
	
	/**
	 *  Checks whether middle school algorithm correctly compute gcd.
	 * This test uses differential testing, comparing with the output of
	 * euclid's algorithm.
	 * This is a parameterized unit test. See parameters elsewhere in this class.
	 */
	@Test
	public void middleSchoolTest(){
		int gcd = GreatestCommonDivisor.euclidAlgorithm(m,n);
		int mShoolGcd = GreatestCommonDivisor.middleSchoolAlgorithm(m,n);
		assertTrue("euclid's Algorithm of "+m+","+n+" : "+gcd+" middle School :"+mShoolGcd ,gcd == mShoolGcd);
	}

	/**
	 * Checks whether euclid algorithm throws the correct exception when is invoked
	 * with wrong arguments.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentException(){
		int gcd = GreatestCommonDivisor.euclidAlgorithm(-1,9);
	}	

	/**
	 * Checks whether euclid algorithm throws the correct exception when is invoked
	 * with wrong arguments.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentException2(){
		int gcd = GreatestCommonDivisor.euclidAlgorithm(0,0);
	}

	/**
	 * Checks whether euclid's algorithm throws the correct exception when is invoked
	 * with wrong arguments.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionDefinitionBasedAlgorithm(){
		int gcd = GreatestCommonDivisor.definitionBasedAlgorithm(-1,9);
	}	

	/**
	 * Checks whether euclid's algorithm throws the correct exception when is invoked
	 * with wrong arguments.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionDefinitionBasedAlgorithm2(){
		int gcd = GreatestCommonDivisor.definitionBasedAlgorithm(0,0);
	}
	
	/**
	 * Checks whether euclid's algorithm throws the correct exception when is invoked
	 * with wrong arguments.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionMiddleSchoolAlgorithm(){
		int gcd = GreatestCommonDivisor.middleSchoolAlgorithm(-1,9);
	}	

	/**
	 * Checks whether euclid's algorithm throws the correct exception when is invoked
	 * with wrong arguments.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionMiddleSchoolAlgorithm2(){
		int gcd = GreatestCommonDivisor.middleSchoolAlgorithm(0,0);
	}

}