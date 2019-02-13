package preRefactor.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import preRefactor.DNA;

public class DNATestInquireMethod {
	
	private String validStringNoQ;
	private ByteArrayInputStream in;
	private int goodSize;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		validStringNoQ = "CTC";
		goodSize = 3;
		in = null;
	}

	@After
	public void tearDown() throws Exception {
		in = null;
	}

	
	// I can only test valid input at this point because the method is poorly written and will be refactored
	@Test
	public void TestValidQueryNoQ() {
		in = new ByteArrayInputStream(validStringNoQ.getBytes());
		System.setIn(in);
		
		String Result = DNA.inquire(goodSize);
		assertEquals(validStringNoQ, Result);
		System.setIn(System.in);
		
	}
	


}
