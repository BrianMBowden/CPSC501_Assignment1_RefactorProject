package refactor.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import preRefactor.DNA;

public class DNATest {

	private char good;
	private char bad;
	private int[] goodInput;
	private int[] badInput;
	private int goodOut;
	private int badOut;
	private String validFileName;
	private String invalidFileName;
	private BufferedReader fIn;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		good = 'G';
		bad = 'B';
		goodInput = new int[] {3,4,5};
		badInput = new int[] {0,0,1};
		goodOut = 345;
		badOut = 001;
		validFileName = new String("yeast1.fasta");
		invalidFileName = new String("invalid.file");
		fIn = null;
	}
	

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testValidFileName() {
		
		try {
			fIn = DNA.opening(validFileName);
		} catch (Exception e) {
			fail();
		}
		
		assertFalse(fIn == null);
		
	}
	
	@Test
	public void testInvalidFileName() {
		
		try {
			fIn = DNA.opening(invalidFileName);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testGoodInput() {
		int Result;
		Result = DNA.Sequencer(goodInput, goodInput.length);
		assertEquals(goodOut, Result);
	}
	
	@Test
	public void testBadInput() {
		int Result;
		Result = DNA.Sequencer(badInput, badInput.length);
		assertEquals(badOut, Result);	
	}

	
	@Test
	public void testGoodCharacter() {
		int result;
		int expected = 2;
		
		result = DNA.decision(good);
		assertEquals(expected, result);
	}

	@Test
	public void testBadCharacter() {
		int result;
		int expected = 0;
		
		result = DNA.decision(bad);
		assertEquals(expected, result);
	}

}
