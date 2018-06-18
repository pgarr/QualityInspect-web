package com.pgarr.qinspect.api.test.integration;

import com.pgarr.qinspect.api.dao.FormDao;
import com.pgarr.qinspect.api.entity.Form;
import com.pgarr.qinspect.api.entity.Item;
import com.pgarr.qinspect.api.entity.ItemDetail;
import com.pgarr.qinspect.api.entity.Step;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FormDaoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FormDao formDao;

    private static Form testForm1;
    private static Form testForm2;
    private static Form testForm3;

    @Before
    public void setUp() {

        testForm1 = new Form("Test Form 1", "", false);
        testForm2 = new Form("Test Form 2", "", false);
        testForm3 = new Form("Test Form 3", "", true);

        entityManager.clear();
    }

    @Test
    public void testFindByArchivedFalse() {

        entityManager.persist(testForm1);
        entityManager.persist(testForm2);
        entityManager.persist(testForm3);
        entityManager.flush();

        List<Form> forms = formDao.findByArchivedFalse();

        assertThat(forms.size()).isGreaterThan(1);

        for (Form form : forms) {
            assertThat(form.isArchived()).isFalse();
        }
    }

    @Test
    public void testFindByArchivedFalseAndItem_Id() {

        ItemDetail testItemDetail1 = new ItemDetail("Acme", "Test");
        Item testItem1 = new Item("Test Item 1", testItemDetail1);

        testForm1.setItem(testItem1);
        testForm2.setItem(testItem1);
        testForm3.setItem(testItem1);


        Form testForm4 = new Form("Test Form 4", "", false);
        Form testForm5 = new Form("Test Form 5", "", true);

        ItemDetail testItemDetail2 = new ItemDetail("Acme", "Test");
        Item testItem2 = new Item("Test Item 2", testItemDetail2);

        testForm4.setItem(testItem2);
        testForm5.setItem(testItem2);

        Long itemId1 = (long) entityManager.persistAndGetId(testItem1);
        Long itemId2 = (long) entityManager.persistAndGetId(testItem2);
        entityManager.persist(testForm1);
        entityManager.persist(testForm2);
        entityManager.persist(testForm3);
        entityManager.persist(testForm4);
        entityManager.persist(testForm5);
        entityManager.flush();

        List<Form> forms = formDao.findByArchivedFalseAndItem_Id(itemId1);

        assertThat(forms.size()).isGreaterThan(1);

        for (Form form : forms) {
            assertThat(form.isArchived()).isFalse();
            assertThat(form.getItem().getId()).isEqualTo(itemId1);
            assertThat(form.getItem().getId()).isNotEqualTo(itemId2);
        }
    }

    @Test
    public void testFindById_GetWithLazySteps() {

        Step testStep1 = new Step("Test Step 1", "Test", 1);
        Step testStep2 = new Step("Test Step 2", "Test", 2);

        testForm1.addStep(testStep1);
        testForm1.addStep(testStep2);

        Long id = (Long) entityManager.persistAndGetId(testForm1);
        entityManager.flush();

        Optional<Form> actual = formDao.findById(id);

        assertThat(actual.get().getName()).isEqualTo(testForm1.getName());
        assertThat(actual.get().getSteps().size()).isEqualTo(testForm1.getSteps().size());
        assertThat(actual.get().getSteps().get(0).getDescription())
                .isEqualTo(testForm1.getSteps().get(0).getDescription());
    }

    @Test
    public void testUpdateArchivedFor_SetToTrue() {

        Long id = (Long) entityManager.persistAndGetId(testForm1);
        entityManager.flush();

        formDao.setFixedArchivedFor(true, id);

        Form actual = entityManager.find(Form.class, id);

        assertThat(actual.isArchived()).isTrue();
    }

    @Test
    public void testUpdateArchivedFor_SetToFalse() {

        Long id = (Long) entityManager.persistAndGetId(testForm3);
        entityManager.flush();

        formDao.setFixedArchivedFor(false, id);

        Form actual = entityManager.find(Form.class, id);

        assertThat(actual.isArchived()).isFalse();
    }
}
