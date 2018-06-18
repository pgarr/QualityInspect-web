package com.pgarr.qinspect.api.test.integration;

import com.pgarr.qinspect.api.dao.ItemDao;
import com.pgarr.qinspect.api.entity.Item;
import com.pgarr.qinspect.api.entity.ItemDetail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemDaoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ItemDao itemDao;

    private static long id;

    @Before
    public void setUp() {

        ItemDetail testItemDetail = new ItemDetail("Acme", "Test");
        Item testItem = new Item("Test Item", testItemDetail);

        id = (long) entityManager.persistAndGetId(testItem);
        entityManager.flush();
    }


    //    This is just to check, how JPA handle eager fetching. Don't really have to test default methods from framework
    @Test
    public void testFindById_GetItemWithItemDetail() {

        Optional<Item> found = itemDao.findById(id);

        if (found.equals(Optional.empty()))
            fail();
        else {
            assertThat(found.get().getName()).isEqualTo("Test Item");
            assertThat(found.get().getItemDetail().getMaker()).isEqualTo("Acme");
        }
    }
}
