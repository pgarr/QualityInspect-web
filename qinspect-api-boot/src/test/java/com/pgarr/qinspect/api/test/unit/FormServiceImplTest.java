package com.pgarr.qinspect.api.test.unit;

import com.pgarr.qinspect.api.dao.FormDao;
import com.pgarr.qinspect.api.entity.Form;
import com.pgarr.qinspect.api.entity.Item;
import com.pgarr.qinspect.api.entity.Step;
import com.pgarr.qinspect.api.exception.ResourceNotFoundException;
import com.pgarr.qinspect.api.service.FormService;
import com.pgarr.qinspect.api.service.FormServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class FormServiceImplTest {

    private static Form mockedForm1;
    private static Form mockedForm2;
    private static Form mockedForm3;

    private static List<Form> mockedForms;

    @TestConfiguration
    static class FormServiceImplTestContextConfiguration {

        @Bean
        public FormService formService() {
            return new FormServiceImpl();
        }
    }

    @Autowired
    private FormService formService;

    @MockBean
    private FormDao formDao;

    @BeforeClass
    public static void setUp() {

        Item mockedItem1 = new Item("Test Item 1", null);
        mockedItem1.setId(1L);
        Item mockedItem2 = new Item("Test Item 2", null);
        mockedItem2.setId(2L);

        mockedForm1 = new Form("Test Form 1", "Test", false);
        mockedForm1.setItem(mockedItem1);
        mockedForm1.setId(1L);

        mockedForm2 = new Form("Test Form 2", "Test", true);
        mockedForm2.setItem(mockedItem1);
        mockedForm2.setId(2L);

        mockedForm3 = new Form("Test Form 3", "Test", false);
        mockedForm3.setItem(mockedItem2);
        mockedForm3.setId(3L);

        Step mockedStep2F1 = new Step("Test Step 2", "Test 1", 2);
        Step mockedStep1F1 = new Step("Test Step 1", "Test 1", 1);
        mockedForm1.addStep(mockedStep2F1);
        mockedForm1.addStep(mockedStep1F1);

        Step mockedStep1F3 = new Step("Test Step 1", "Test 3", 1);
        Step mockedStep2F3 = new Step("Test Step 2", "Test 3", 2);
        mockedForm3.addStep(mockedStep1F3);
        mockedForm3.addStep(mockedStep2F3);

        mockedForms = new ArrayList<>();
        mockedForms.add(mockedForm1);
        mockedForms.add(mockedForm2);
        mockedForms.add(mockedForm3);
    }

    @Test
    public void testGetAllForms_ReturnFullListOfForms() {

        Mockito.when(formDao.findAll())
                .thenReturn(mockedForms);

        List<Form> expectedForms = new ArrayList<>();
        expectedForms.add(mockedForm1);
        expectedForms.add(mockedForm2);
        expectedForms.add(mockedForm3);

        List<Form> actualForms = formService.getAllForms();

        assertEquals(expectedForms.size(), actualForms.size());
        assertEquals(expectedForms.get(0).getName(), actualForms.get(0).getName());
        assertEquals(expectedForms.get(0).getSteps().get(0).getDescription(), actualForms.get(0).getSteps().get(0).getDescription());
    }

    @Test
    public void testGetAllForms_WhenNoForms_ThenReturnEmptyList() {

        Mockito.when(formDao.findAll())
                .thenReturn(new ArrayList<>());

        List<Form> actualForms = formService.getAllForms();

        assertEquals(0, actualForms.size());
    }

    @Test
    public void testGetAllActiveForms_ReturnActiveForms() {

        List<Form> mockedActiveForms = mockedForms.stream()
                .filter(form -> !form.isArchived())
                .collect(toList());

        Mockito.when(formDao.findByArchivedFalse())
                .thenReturn(mockedActiveForms);

        List<Form> expectedActiveForms = new ArrayList<>();
        expectedActiveForms.add(mockedForm1);
        expectedActiveForms.add(mockedForm3);

        List<Form> actualActiveForms = formService.getAllActiveForms();

        assertEquals(expectedActiveForms.size(), actualActiveForms.size());
        assertEquals(expectedActiveForms.get(0).getName(), actualActiveForms.get(0).getName());
        assertEquals(expectedActiveForms.get(0).getSteps().get(0).getDescription(), actualActiveForms.get(0).getSteps().get(0).getDescription());
    }

    @Test
    public void testGetAllActiveForms_WhenNoForms_ThenReturnEmptyList() {

        Mockito.when(formDao.findByArchivedFalse())
                .thenReturn(new ArrayList<>());

        List<Form> actualActiveForms = formService.getAllActiveForms();

        assertEquals(0, actualActiveForms.size());
    }

    @Test
    public void testGetForm_WhenValidId1andNeedToSortSteps_ThenReturnForm1WithSortedSteps() {

        Mockito.when(formDao.findById(mockedForm1.getId()))
                .thenReturn(Optional.of(mockedForm1));

        Item expectedItem = new Item("Test Item 1", null);
        Form expectedForm = new Form("Test Form 1", "Test", false);
        expectedForm.setItem(expectedItem);
        expectedForm.setId(1L);

        Step expectedStep1 = new Step("Test Step 1", "Test 1", 1);
        Step expectedStep2 = new Step("Test Step 2", "Test 1", 2);
        expectedForm.addStep(expectedStep1);
        expectedForm.addStep(expectedStep2);

        // when
        Form foundForm = formService.getForm(1L);

        // then
        assertThat(foundForm.getName())
                .isEqualTo(expectedForm.getName());
        assertThat(foundForm.getId())
                .isEqualTo(expectedForm.getId());
        assertThat(foundForm.getSteps().get(0).getNumber())
                .isEqualTo(expectedForm.getSteps().get(0).getNumber());
        assertThat(foundForm.getSteps().get(1).getNumber())
                .isEqualTo(expectedForm.getSteps().get(1).getNumber());
    }

    @Test
    public void testGetForm_WhenValidId2andStepsAreSorted_ThenReturnForm2WithSortedSteps() {

        Mockito.when(formDao.findById(mockedForm3.getId()))
                .thenReturn(Optional.of(mockedForm3));

        Item expectedItem = new Item("Test Item 1", null);
        Form expectedForm = new Form("Test Form 3", "Test", false);
        expectedForm.setItem(expectedItem);
        expectedForm.setId(3L);

        Step expectedStep1 = new Step("Test Step 1", "Test 3", 1);
        Step expectedStep2 = new Step("Test Step 2", "Test 3", 2);
        expectedForm.addStep(expectedStep1);
        expectedForm.addStep(expectedStep2);

        // when
        Form foundForm = formService.getForm(3L);

        // then
        assertThat(foundForm.getName())
                .isEqualTo(expectedForm.getName());
        assertThat(foundForm.getId())
                .isEqualTo(expectedForm.getId());
        assertThat(foundForm.getSteps().get(0).getNumber())
                .isEqualTo(expectedForm.getSteps().get(0).getNumber());
        assertThat(foundForm.getSteps().get(1).getNumber())
                .isEqualTo(expectedForm.getSteps().get(1).getNumber());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetForm_WhenIdOutOfBound_ThenThrowResourceNotFoundException() {

        Mockito.when(formDao.findById(4L))
                .thenReturn(Optional.empty());

        formService.getForm(4L);
    }

    @Test
    public void testSaveForm_FormDaoSaveCalled() {
        formService.saveForm(mockedForm1);

        verify(formDao, Mockito.times(1)).save(mockedForm1);
    }

    @Test(expected = NullPointerException.class)
    public void testSaveForm_WhenNullArgument_ThenThrowNullArgumentPassedException() {

        formService.saveForm(null);

        verify(formDao, Mockito.times(0)).save(any(Form.class));
    }

    @Test
    public void testArchiveForm_FormDaoArchiveCalled() {
        formService.archiveForm(1L);

        verify(formDao, Mockito.times(1)).archiveThis(1L);
    }

    @Test
    public void testGetActiveItemForms_ReturnActiveItemForms() {

        List<Form> mockedActiveItemForms = mockedForms.stream()
                .filter(form -> !form.isArchived() & form.getItem().getId() == 1L)
                .collect(toList());

        Mockito.when(formDao.findByArchivedFalseAndItem_Id(1L))
                .thenReturn(mockedActiveItemForms);

        List<Form> expectedActiveForms = new ArrayList<>();
        expectedActiveForms.add(mockedForm1);

        List<Form> actualActiveItemForms = formService.getActiveItemForms(1L);

        assertEquals(expectedActiveForms.size(), actualActiveItemForms.size());
        assertEquals(expectedActiveForms.get(0).getName(), actualActiveItemForms.get(0).getName());
        assertEquals(expectedActiveForms.get(0).getSteps().get(0).getDescription(), actualActiveItemForms.get(0).getSteps().get(0).getDescription());
    }

    @Test
    public void testGetActiveItemForms_WhenNoForms_ThenReturnEmptyList() {
        Mockito.when(formDao.findByArchivedFalse())
                .thenReturn(new ArrayList<>());

        List<Form> actualActiveForms = formService.getActiveItemForms(3L);

        assertEquals(0, actualActiveForms.size());
    }

    @Test
    public void testSaveForm_WhenFormLacksRequiredFields() {
        // expected some exception
        // dont call formDao.save
        fail();
    }

}
