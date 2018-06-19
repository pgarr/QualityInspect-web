package com.pgarr.qinspect.api.test.integration;

import com.pgarr.qinspect.api.dao.InspectionDao;
import com.pgarr.qinspect.api.entity.*;
import com.pgarr.qinspect.api.entity.enums.ResultType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InspectionDaoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InspectionDao inspectionDao;

    private static Inspection testInspection;

    private static List<Result> testResults;

    @BeforeClass
    public static void setUpClass() {

        ItemDetail testItemDetail = new ItemDetail("ACME", "Test Detail 1");
        Item testItem = new Item("Test Item", null);
        testItem.setItemDetail(testItemDetail);

        Form testForm = new Form("Test Form", "Test", false);
        testForm.setItem(testItem);

        testInspection = new Inspection("Test Inspection 1", Date.from(Instant.now()),
                Date.from(Instant.now()), "Test Inspector 1", "Warsaw", 1, ResultType.ACCEPTED,
                true);
        testInspection.setForm(testForm);

        Result testResult1 = new Result(ResultType.GOOD, "");
        Step testStep1 = new Step("Test Step 1", "Test", 1);
        testResult1.setStep(testStep1);

        Result testResult2 = new Result(ResultType.ACCEPTED, "Test");
        Step testStep2 = new Step("Test Step 2", "Test", 2);
        testResult2.setStep(testStep2);

        testResults = new ArrayList<>();

        testResults.add(testResult1);
        testResults.add(testResult2);
        testInspection.setResults(testResults);
    }

    // This should find full Inspection with: Form with Item with ItemDetail and List of Results with Step and List of FaultPicture
    @Test
    public void testFindById() {

        long id = (long) entityManager.persistAndGetId(testInspection);

        Optional<Inspection> found = inspectionDao.findById(id);

        if (found.equals(Optional.empty()))
            fail();
        else {
            assertThat(found.get().getSerialNumber()).isEqualTo("Test Inspection 1");
            assertThat(found.get().getForm().getName()).isEqualTo("Test Form");
            assertThat(found.get().getForm().getItem().getName()).isEqualTo("Test Item");
            assertThat(found.get().getForm().getItem().getItemDetail().getMaker()).isEqualTo("ACME");
            assertThat(found.get().getResults().size()).isEqualTo(2);
            assertThat(found.get().getResults().get(1).getNote()).isEqualTo("Test");
            assertThat(found.get().getResults().get(0).getStep().getDescription()).isEqualTo("Test Step 1");
        }

    }
}
