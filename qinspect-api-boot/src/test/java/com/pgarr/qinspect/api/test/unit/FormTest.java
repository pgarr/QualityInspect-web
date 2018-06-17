package com.pgarr.qinspect.api.test.unit;

import com.pgarr.qinspect.api.entity.Form;
import com.pgarr.qinspect.api.entity.Step;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FormTest {

    private static Form testForm;
    private static Step testStep1;
    private static Step testStep2;
    private static Step testStep3;
    private static List<Step> testSteps;

    @BeforeClass
    public static void setUpClass() {
        testForm = new Form("Test Form", "Test", false);

        testStep1 = new Step("Test Step 1", "Test", 1);
        testStep2 = new Step("Test Step 2", "Test", 2);
        testStep3 = new Step("Test Step 2", "Test", 3);
    }

    @Before
    public void setUp() {
        testSteps = new ArrayList<>();
        testForm.setSteps(null);
    }

    @Test
    public void TestAddStep_WhenStepsAreNull_CreateNewArrayListAndAddStep() {

        testForm.addStep(testStep1);

        assertEquals(1, testForm.getSteps().size());
        assertEquals(1, testForm.getSteps().get(0).getNumber());
    }

    @Test
    public void TestAddStep_WhenStepsAreEmpty_AddStepToList() {

        testForm.setSteps(testSteps);

        testForm.addStep(testStep1);

        assertEquals(1, testForm.getSteps().size());
        assertEquals(1, testForm.getSteps().get(0).getNumber());

    }

    @Test
    public void TestAddStep_WhenThereAreSteps_AddStepToList() {

        testSteps.add(testStep1);
        testForm.setSteps(testSteps);

        testForm.addStep(testStep2);

        assertEquals(2, testForm.getSteps().size());
        assertEquals(1, testForm.getSteps().get(0).getNumber());
        assertEquals(2, testForm.getSteps().get(1).getNumber());
    }

    @Test
    public void TestSortSteps_WhenStepsAreNotSorted_SortThemByFieldNumber() {

        testSteps.add(testStep2);
        testSteps.add(testStep3);
        testSteps.add(testStep1);

        testForm.setSteps(testSteps);

        testForm.sortSteps();

        assertEquals(3, testForm.getSteps().size());
        assertEquals(1, testForm.getSteps().get(0).getNumber());
        assertEquals(2, testForm.getSteps().get(1).getNumber());
        assertEquals(3, testForm.getSteps().get(2).getNumber());
    }

    @Test
    public void TestSortSteps_WhenStepsAreSorted() {

        testSteps.add(testStep1);
        testSteps.add(testStep2);
        testSteps.add(testStep3);

        testForm.setSteps(testSteps);

        testForm.sortSteps();

        assertEquals(3, testForm.getSteps().size());
        assertEquals(1, testForm.getSteps().get(0).getNumber());
        assertEquals(2, testForm.getSteps().get(1).getNumber());
        assertEquals(3, testForm.getSteps().get(2).getNumber());
    }

    @Test
    public void TestSortSteps_WhenThereIsOnlyOneStep() {

        testSteps.add(testStep1);

        testForm.setSteps(testSteps);

        testForm.sortSteps();

        assertEquals(1, testForm.getSteps().size());
        assertEquals(1, testForm.getSteps().get(0).getNumber());
    }

    @Test
    public void TestSortSteps_WhenStepsAreEmpty() {

        testForm.setSteps(testSteps);

        testForm.sortSteps();

        assertEquals(0, testForm.getSteps().size());
    }

    @Test
    public void TestSortSteps_WhenStepsAreNull () {

        testForm.sortSteps();
    }
}
