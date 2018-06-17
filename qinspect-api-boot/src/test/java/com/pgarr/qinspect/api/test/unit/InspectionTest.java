package com.pgarr.qinspect.api.test.unit;

import com.pgarr.qinspect.api.entity.Inspection;
import com.pgarr.qinspect.api.entity.Result;
import com.pgarr.qinspect.api.entity.Step;
import com.pgarr.qinspect.api.entity.enums.ResultType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InspectionTest {

    private static Inspection testInspection;
    private static Result testResult1;
    private static Result testResult2;
    private static Result testResult3;
    private static List<Result> testResults;

    @BeforeClass
    public static void setUpClass() {

        testInspection = new Inspection("Test Inspection", Date.from(Instant.now()),
                Date.from(Instant.now()), "Test Inspector 1", "Warsaw", 1, ResultType.NOT_ACCEPTED,
                true);

        testResult1 = new Result(ResultType.ACCEPTED, "Test");
        testResult2 = new Result(ResultType.GOOD, "");
        testResult3 = new Result(ResultType.NOT_ACCEPTED, "Test");

        Step testStep1 = new Step("Test Step 1", "Test", 1);
        Step testStep2 = new Step("Test Step 2", "Test", 2);
        Step testStep3 = new Step("Test Step 3", "Test", 3);

        testResult1.setStep(testStep1);
        testResult2.setStep(testStep2);
        testResult3.setStep(testStep3);
    }

    @Before
    public void setUp() {
        testResults = new ArrayList<>();
        testInspection.setResults(null);
    }

    @Test
    public void TestAddResult_WhenResultsAreNull_CreateNewArrayListAndAddResult() {

        testInspection.addResult(testResult1);

        assertEquals(1, testInspection.getResults().size());
        assertEquals(1, testInspection.getResults().get(0).getStep().getNumber());
    }

    @Test
    public void TestAddResult_WhenResultsAreEmpty_AddResultToList() {

        testInspection.setResults(testResults);

        testInspection.addResult(testResult1);

        assertEquals(1, testInspection.getResults().size());
        assertEquals(1, testInspection.getResults().get(0).getStep().getNumber());

    }

    @Test
    public void TestAddResult_WhenThereAreResult_AddResultToList() {

        testResults.add(testResult1);
        testInspection.setResults(testResults);

        testInspection.addResult(testResult2);

        assertEquals(2, testInspection.getResults().size());
        assertEquals(1, testInspection
                .getResults().get(0).getStep().getNumber());
        assertEquals(2, testInspection
                .getResults().get(1).getStep().getNumber());
    }

    @Test
    public void TestSortResults_WhenResultsAreNotSorted_SortThemByFieldNumber() {

        testResults.add(testResult2);
        testResults.add(testResult3);
        testResults.add(testResult1);

        testInspection
                .setResults(testResults);

        testInspection
                .sortResults();

        assertEquals(3, testInspection
                .getResults().size());
        assertEquals(1, testInspection
                .getResults().get(0).getStep().getNumber());
        assertEquals(2, testInspection
                .getResults().get(1).getStep().getNumber());
        assertEquals(3, testInspection
                .getResults().get(2).getStep().getNumber());
    }

    @Test
    public void TestSortResults_WhenResultsAreSorted() {

        testResults.add(testResult1);
        testResults.add(testResult2);
        testResults.add(testResult3);

        testInspection
                .setResults(testResults);

        testInspection
                .sortResults();

        assertEquals(3, testInspection
                .getResults().size());
        assertEquals(1, testInspection
                .getResults().get(0).getStep().getNumber());
        assertEquals(2, testInspection
                .getResults().get(1).getStep().getNumber());
        assertEquals(3, testInspection
                .getResults().get(2).getStep().getNumber());
    }

    @Test
    public void TestSortResults_WhenThereIsOnlyOneResult() {

        testResults.add(testResult1);

        testInspection
                .setResults(testResults);

        testInspection
                .sortResults();

        assertEquals(1, testInspection
                .getResults().size());
        assertEquals(1, testInspection
                .getResults().get(0).getStep().getNumber());
    }

    @Test
    public void TestSortResults_WhenResultsAreEmpty() {

        testInspection
                .setResults(testResults);

        testInspection
                .sortResults();

        assertEquals(0, testInspection
                .getResults().size());
    }

    @Test
    public void TestSortResults_WhenResultsAreNull() {

        testInspection
                .setResults(null);

        testInspection
                .sortResults();
    }
}
