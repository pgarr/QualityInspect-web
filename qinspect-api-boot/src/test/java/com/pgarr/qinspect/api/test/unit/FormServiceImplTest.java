package com.pgarr.qinspect.api.test.unit;

import com.pgarr.qinspect.api.dao.FormDao;
import com.pgarr.qinspect.api.entity.Form;
import com.pgarr.qinspect.api.entity.Item;
import com.pgarr.qinspect.api.entity.Step;
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

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class FormServiceImplTest {

    private static Form form1;
    private static Form form2;
    private static Form form3;

    private static Item item1;
    private static Item item2;

    private static Step step1F1;
    private static Step step2F1;

    private static List<Form> forms;

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

        item1 = new Item("Test Item 1", null);
        item2 = new Item("Test Item 2", null);

        form1 = new Form("Test Form 1", "Test", false);
        form1.setItem(item1);
        form1.setId(1L);

        form2 = new Form("Test Form 2", "Test", true);
        form2.setItem(item1);
        form2.setId(2L);

        form3 = new Form("Test Form 3", "Test", false);
        form3.setItem(item2);
        form3.setId(3L);

        step1F1 = new Step("Test Step 1", "Test 1", 1);
        step2F1 = new Step("Test Step 2", "Test 1", 2);
        form1.addStep(step2F1);
        form1.addStep(step1F1);

        forms = new ArrayList<>();
        forms.add(form1);
        forms.add(form2);
        forms.add(form3);
    }

    @Test
    public void testGetAllForms_ReturnFullListOfForms() {

        Mockito.when(formDao.findAll())
                .thenReturn(forms);

        List<Form> expected = new ArrayList<>();
        expected.add(form1);
        expected.add(form2);
        expected.add(form3);

        List<Form> actual = formService.getAllForms();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getName(), actual.get(0).getName());
        assertEquals(expected.get(0).getSteps().get(0).getDescription(), actual.get(0).getSteps().get(0).getDescription());
    }

    @Test
    public void testGetAllForms_WhenNoForms_ThenReturnEmptyList() {

        Mockito.when(formDao.findAll())
                .thenReturn(new ArrayList<>());

        List<Form> actual = formService.getAllForms();

        assertEquals(0, actual.size());
    }

    @Test
    public void testGetAllActiveForms_ReturnActiveForms() {

        List<Form> activeForms = forms.stream()
                .filter(form -> !form.isArchived())
                .collect(toList());

        Mockito.when(formDao.findByArchivedFalse())
                .thenReturn(activeForms);

        List<Form> expected = new ArrayList<>();
        expected.add(form1);
        expected.add(form3);

        List<Form> actual = formService.getAllActiveForms();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getName(), actual.get(0).getName());
        assertEquals(expected.get(0).getSteps().get(0).getDescription(), actual.get(0).getSteps().get(0).getDescription());
    }

    @Test
    public void testGetAllActiveForms_WhenNoForms_ThenReturnEmptyList() {

        Mockito.when(formDao.findByArchivedFalse())
                .thenReturn(new ArrayList<>());

        List<Form> actual = formService.getAllActiveForms();

        assertEquals(0, actual.size());
    }

    @Test
    public void getForm() {

    }

    @Test
    public void saveForm() {

    }

    @Test
    public void archiveForm() {

    }

    @Test
    public void getActiveItemForms() {

    }

}
