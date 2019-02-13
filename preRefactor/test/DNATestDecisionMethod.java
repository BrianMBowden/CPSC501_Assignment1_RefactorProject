package preRefactor.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import preRefactor.DNA;

public class DNATestDecisionMethod {
	
	private char good;
	private char bad;

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
	}

	@After
	public void tearDown() throws Exception {
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
