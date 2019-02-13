package preRefactor.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import preRefactor.DNA;

public class DNATestOpeningMethod extends DNA {
	
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

}
