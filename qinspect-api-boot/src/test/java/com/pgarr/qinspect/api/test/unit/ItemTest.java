package com.pgarr.qinspect.api.test.unit;

import com.pgarr.qinspect.api.entity.Form;
import com.pgarr.qinspect.api.entity.Item;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ItemTest {

    private static Item testItem;
    private static Form testForm1;
    private static Form testForm2;
    private static List<Form> testForms;

    @BeforeClass
    public static void setUpClass() {

        testItem = new Item("Test Item", null);

        testForm1 = new Form("Test Form 1", "Test", false);
        testForm2 = new Form("Test Form 2", "Test", false);
    }

    @Before
    public void setUp() {
        testForms = new ArrayList<>();
        testItem.setForms(null);
    }

    @Test
    public void testAddStep_WhenStepsAreNull_CreateNewArrayListAndAddStep() {

        testItem.addForm(testForm1);

        assertEquals(1, testItem.getForms().size());
        assertEquals("Test Form 1", testItem.getForms().get(0).getName());
    }

    @Test
    public void testAddStep_WhenStepsAreEmpty_AddStepToList() {

        testItem.setForms(testForms);

        testItem.addForm(testForm1);

        assertEquals(1, testItem.getForms().size());
        assertEquals("Test Form 1", testItem.getForms().get(0).getName());
    }

    @Test
    public void testAddStep_WhenThereAreSteps_AddStepToList() {

        testForms.add(testForm1);
        testItem.setForms(testForms);

        testItem.addForm(testForm2);

        assertEquals(2, testItem.getForms().size());
        assertEquals("Test Form 1", testItem.getForms().get(0).getName());
        assertEquals("Test Form 2", testItem.getForms().get(1).getName());
    }

}
