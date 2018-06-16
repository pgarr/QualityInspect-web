package com.pgarr.qinspect.api.test.unit;

import com.pgarr.qinspect.api.dao.ItemDao;
import com.pgarr.qinspect.api.entity.Item;
import com.pgarr.qinspect.api.entity.ItemDetail;
import com.pgarr.qinspect.api.exception.ResourceNotFoundException;
import com.pgarr.qinspect.api.service.ItemService;
import com.pgarr.qinspect.api.service.ItemServiceImpl;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class ItemServiceImplTest {

    private static Item testItem1;
    private static Item testItem2;
    private static List<Item> items;

    @TestConfiguration
    static class ItemServiceImplTestContextConfiguration {

        @Bean
        public ItemService itemService() {
            return new ItemServiceImpl();
        }
    }


    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemDao itemDao;

    @BeforeClass
    public static void setUp() {
        ItemDetail testItemDetail1 = new ItemDetail("ACME", "Test Detail 1");
        testItem1 = new Item("Test item 1", testItemDetail1);
        testItem1.setId(1L);

        ItemDetail testItemDetail2 = new ItemDetail("ACME", "Test Detail 2");
        testItem2 = new Item("Test item 2", testItemDetail2);
        testItem2.setId(2L);

        items = new ArrayList<>();
        items.add(testItem1);
        items.add(testItem2);
    }

    @Test
    public void testGetItem_whenValidId1_ThenReturnItem1() {

        Mockito.when(itemDao.findById(testItem1.getId()))
                .thenReturn(Optional.of(testItem1));

        ItemDetail testItemDetail1 = new ItemDetail("ACME", "Test Detail 1");
        Item testItem1 = new Item("Test item 1", testItemDetail1);
        testItem1.setId(1L);

        // when
        Item foundItem = itemService.getItem(1L);

        // then
        assertThat(foundItem.getName())
                .isEqualTo(testItem1.getName());
        assertThat(foundItem.getItemDetail().getMaker())
                .isEqualTo(testItem1.getItemDetail().getMaker());
        assertThat(foundItem.getItemDetail().getDescription())
                .isEqualTo(testItem1.getItemDetail().getDescription());

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetItem_WhenIdOutOfBound_ThenThrowResourceNotFoundException() {

        Mockito.when(itemDao.findById(3L))
                .thenReturn(Optional.empty());

        itemService.getItem(3L);
    }

    @Test
    public void testGetItems_ReturnListOfItems() {

        Mockito.when(itemDao.findAll())
                .thenReturn(items);

        List<Item> expected = new ArrayList<>();
        expected.add(testItem1);
        expected.add(testItem2);

        List<Item> actual = itemService.getItems();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getName(), actual.get(0).getName());
        assertEquals(expected.get(1).getItemDetail().getMaker(), actual.get(1).getItemDetail().getMaker());
    }

    @Test
    public void testGetItems_WhenNoItems_ThenReturnEmptyList() {

        Mockito.when(itemDao.findAll())
                .thenReturn(new ArrayList<>());

        List<Item> actual = itemService.getItems();

        assertEquals(0, actual.size());

    }

    @Test
    public void testSaveItem_ItemDAOSaveCalled() {

        itemService.saveItem(testItem1);

        verify(itemDao, Mockito.times(1)).save(testItem1);
    }


}
