package preRefactor.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import preRefactor.DNA;

public class DNATestSequencerMethod extends DNA {
	
	private int[] goodInput;
	private int[] badInput;
	private int goodOut;
	private int badOut;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		goodInput = new int[] {3,4,5};
		badInput = new int[] {0,0,1};
		goodOut = 345;
		badOut = 001;
	}

	@After
	public void tearDown() throws Exception {
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

}
