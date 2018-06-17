package com.pgarr.qinspect.api.test.unit;

import com.pgarr.qinspect.api.dao.InspectionDao;
import com.pgarr.qinspect.api.entity.Form;
import com.pgarr.qinspect.api.entity.Inspection;
import com.pgarr.qinspect.api.entity.Result;
import com.pgarr.qinspect.api.entity.Step;
import com.pgarr.qinspect.api.entity.enums.ResultType;
import com.pgarr.qinspect.api.exception.ResourceNotFoundException;
import com.pgarr.qinspect.api.service.InspectionService;
import com.pgarr.qinspect.api.service.InspectionServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class InspectionServiceImplTest {

    @TestConfiguration
    static class InspectionServiceImplTestContextConfiguration {

        @Bean
        public InspectionService inspectionService() {
            return new InspectionServiceImpl();
        }
    }

    @Autowired
    private InspectionService inspectionService;

    @MockBean
    private InspectionDao inspectionDao;

    private static Inspection mockedInspection1;
    private static Inspection mockedInspection2;

    private static List<Inspection> mockedInspections;

    @BeforeClass
    public static void setUp() {

        Form mockedForm1 = new Form("Test Form 1", "Test", false);

        mockedInspection1 = new Inspection("Test Inspection 1", Date.from(Instant.now()),
                Date.from(Instant.now()), "Test Inspector 1", "Warsaw", 2, ResultType.ACCEPTED,
                true);
        mockedInspection1.setForm(mockedForm1);
        mockedInspection1.setId(1L);

        mockedInspection2 = new Inspection("Test Inspection 2", Date.from(Instant.now()),
                Date.from(Instant.now()), "Test Inspector 2", "Warsaw", 2, ResultType.GOOD,
                false);
        mockedInspection2.setForm(mockedForm1);
        mockedInspection2.setId(2L);

        Step mockedStep1 = new Step("Test Step 1", "Test 1", 1);
        Step mockedStep2 = new Step("Test Step 2", "Test 2", 2);

        Result mockedResult1I1 = new Result(ResultType.ACCEPTED, "Test");
        mockedResult1I1.setStep(mockedStep1);
        Result mockedResult2I1 = new Result(ResultType.GOOD, "");
        mockedResult2I1.setStep(mockedStep2);
        mockedInspection1.addResult(mockedResult2I1);
        mockedInspection1.addResult(mockedResult1I1);

        Result mockedResult1I2 = new Result(ResultType.GOOD, "");
        mockedResult1I2.setStep(mockedStep1);
        Result mockedResult2I2 = new Result(ResultType.GOOD, "");
        mockedResult2I2.setStep(mockedStep2);
        mockedInspection2.addResult(mockedResult1I2);
        mockedInspection2.addResult(mockedResult2I2);

        mockedInspections = new ArrayList<>();
        mockedInspections.add(mockedInspection1);
        mockedInspections.add(mockedInspection2);
    }

    @Test
    public void testGetAlInspections_ReturnFullListOfInspections() {

        Mockito.when(inspectionDao.findAll())
                .thenReturn(mockedInspections);

        List<Inspection> expectedInspections = new ArrayList<>();
        expectedInspections.add(mockedInspection1);
        expectedInspections.add(mockedInspection2);

        List<Inspection> actualInspections = inspectionService.getAllInspections();

        assertEquals(expectedInspections.size(), actualInspections.size());
        assertEquals(expectedInspections.get(0).getSerialNumber(), actualInspections.get(0).getSerialNumber());
        assertEquals(expectedInspections.get(1).getSerialNumber(), actualInspections.get(1).getSerialNumber());
        assertEquals(expectedInspections.get(0).getResults().get(0).getResult(),
                actualInspections.get(0).getResults().get(0).getResult());
    }

    @Test
    public void testGetAlInspections_WhenNoInspections_ThenReturnEmptyList() {

        Mockito.when(inspectionDao.findAll())
                .thenReturn(new ArrayList<>());

        List<Inspection> actualInspections = inspectionService.getAllInspections();

        assertEquals(0, actualInspections.size());
    }

    @Test
    public void testGetInspection_WhenValidId1andNeedToSortResults_ThenReturnInspection1WithSortedResults() {

        Mockito.when(inspectionDao.findById(mockedInspection1.getId()))
                .thenReturn(Optional.of(mockedInspection1));

        Form expectedForm = new Form("Test Form 1", "Test", false);

        Inspection expectedInspection = new Inspection("Test Inspection 1", Date.from(Instant.now()),
                Date.from(Instant.now()), "Test Inspector 1", "Warsaw", 2, ResultType.ACCEPTED,
                true);
        expectedInspection.setForm(expectedForm);
        expectedInspection.setId(1L);

        Result expectedResult1 = new Result(ResultType.ACCEPTED, "Test");
        Step expectedStep1 = new Step("Test Step 1", "Test 1", 1);
        expectedResult1.setStep(expectedStep1);
        Result expectedResult2 = new Result(ResultType.GOOD, "");
        Step expectedStep2 = new Step("Test Step 2", "Test 2", 2);
        expectedResult2.setStep(expectedStep2);
        expectedInspection.addResult(expectedResult1);
        expectedInspection.addResult(expectedResult2);

        // when
        Inspection foundInspection = inspectionService.getInspection(1L);

        // then
        assertThat(foundInspection.getSerialNumber())
                .isEqualTo(expectedInspection.getSerialNumber());
        assertThat(foundInspection.getId())
                .isEqualTo(expectedInspection.getId());
        assertThat(foundInspection.getResults().get(0).getStep().getNumber())
                .isEqualTo(expectedInspection.getResults().get(0).getStep().getNumber());
    }

    @Test
    public void testGetInspection_WhenValidId2andResultsAreSorted_ThenReturnInspection2WithSortedResults() {

        Mockito.when(inspectionDao.findById(mockedInspection2.getId()))
                .thenReturn(Optional.of(mockedInspection2));

        Form expectedForm = new Form("Test Form 1", "Test", false);

        Inspection expectedInspection = new Inspection("Test Inspection 2", Date.from(Instant.now()),
                Date.from(Instant.now()), "Test Inspector 2", "Warsaw", 2, ResultType.GOOD,
                false);
        expectedInspection.setForm(expectedForm);
        expectedInspection.setId(2L);

        Result expectedResult1 = new Result(ResultType.GOOD, "");
        Step expectedStep1 = new Step("Test Step 1", "Test 1", 1);
        expectedResult1.setStep(expectedStep1);
        Result expectedResult2 = new Result(ResultType.GOOD, "");
        Step expectedStep2 = new Step("Test Step 2", "Test 2", 2);
        expectedResult2.setStep(expectedStep2);
        expectedInspection.addResult(expectedResult1);
        expectedInspection.addResult(expectedResult2);

        // when
        Inspection foundInspection = inspectionService.getInspection(2L);

        // then
        assertThat(foundInspection.getSerialNumber())
                .isEqualTo(expectedInspection.getSerialNumber());
        assertThat(foundInspection.getId())
                .isEqualTo(expectedInspection.getId());
        assertThat(foundInspection.getResults().get(0).getStep().getNumber())
                .isEqualTo(expectedInspection.getResults().get(0).getStep().getNumber());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetInspection_WhenIdOutOfBound_ThenThrowResourceNotFoundException() {

        Mockito.when(inspectionDao.findById(4L))
                .thenReturn(Optional.empty());

        inspectionService.getInspection(4L);
    }

    @Test
    public void testSaveInspection_InspectionDaoSaveCalled() {

        inspectionService.saveInspection(mockedInspection1);

        verify(inspectionDao, Mockito.times(1)).save(mockedInspection1);
    }

    @Test(expected = NullPointerException.class)
    public void testSaveInspection_WhenNullArgument_ThenThrowNullArgumentPassedException() {

        inspectionService.saveInspection(null);

        verify(inspectionDao, Mockito.times(0)).save(any(Inspection.class));
    }

    @Test
    public void testDeleteInspection_InspectionDaoDeleteCalled() {

        inspectionService.deleteInspection(1L);

        verify(inspectionDao, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testSaveInspection_WhenInspectionLacksRequiredFields() {

        // expected some exception
        // dont call inspectionDao.save
        fail();
    }
}
