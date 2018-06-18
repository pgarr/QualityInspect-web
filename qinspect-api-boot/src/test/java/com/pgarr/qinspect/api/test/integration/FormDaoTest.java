package com.pgarr.qinspect.api.test.integration;

import com.pgarr.qinspect.api.dao.FormDao;
import com.pgarr.qinspect.api.entity.Form;
import com.pgarr.qinspect.api.entity.Item;
import com.pgarr.qinspect.api.entity.ItemDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FormDaoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FormDao formDao;

    @Test
    public void testFindByArchivedFalse() {

        Form testForm1 = new Form("Test Form 1", "", false);
        Form testForm2 = new Form("Test Form 2", "", false);
        Form testForm3 = new Form("Test Form 3", "", true);

        Long id1 = (Long) entityManager.persistAndGetId(testForm1);
        Long id2 = (Long) entityManager.persistAndGetId(testForm2);
        Long id3 = (Long) entityManager.persistAndGetId(testForm3);
        entityManager.flush();

        List<Form> forms = formDao.findByArchivedFalse();

        assertThat(forms.size()).isGreaterThan(1);

        for (Form form : forms) {
            assertThat(form.isArchived()).isFalse();
        }
    }

    @Test
    public void testFindByArchivedFalseAndItem_Id() {

        Form testForm1 = new Form("Test Form 1", "", false);
        Form testForm2 = new Form("Test Form 2", "", false);
        Form testForm3 = new Form("Test Form 3", "", true);

        ItemDetail testItemDetail1 = new ItemDetail("Acme", "Test");
        Item testItem1 = new Item("Test Item 1", testItemDetail1);

        testItem1.addForm(testForm1);
        testItem1.addForm(testForm2);
        testItem1.addForm(testForm3);

        Long itemId1 = (long) entityManager.persistAndGetId(testItem1);
        entityManager.flush();

        Form testForm4 = new Form("Test Form 4", "", false);
        Form testForm5 = new Form("Test Form 5", "", true);

        ItemDetail testItemDetail2 = new ItemDetail("Acme", "Test");
        Item testItem2 = new Item("Test Item 2", testItemDetail2);

        testItem2.addForm(testForm4);
        testItem2.addForm(testForm5);

        Long itemId2 = (long) entityManager.persistAndGetId(testItem2);
        entityManager.flush();

        List<Form> forms = formDao.findByArchivedFalseAndItem_Id(itemId1);

        assertThat(forms.size()).isGreaterThan(1);

        for (Form form : forms) {
            assertThat(form.isArchived()).isFalse();
            assertThat(form.getItem().getId()).isEqualTo(itemId1);
            assertThat(form.getItem().getId()).isNotEqualTo(itemId2);
        }
    }

}
