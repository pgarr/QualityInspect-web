package com.pgarr.qualityinspect.entity;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InspectionTest {

	private Inspection testInspection;
	private List<Result> testResults;

	@Before
	public void before() {

		testInspection = new Inspection();
		testResults = new ArrayList<Result>();

		testInspection.setResults(testResults);
	}

	@After
	public void after() {
		testInspection = null;
		testResults = null;
	}

	@Test
	public void testCalculateMainResult_EmptyResult() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateMainResult_AllOkResults() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateMainResult_AcceptedAndRestOkResults() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateMainResult_NotOkAndRestOkResults() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateMainResult_NotOkAndAcceptedAndRestOkResults() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateMainResult_EmptyListOfResults() {
		fail("Not yet implemented");
	}

}
