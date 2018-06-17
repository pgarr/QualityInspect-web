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

    private static Item mockedItem1;
    private static Item mockedItem2;
    private static List<Item> mockedItems;

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
        ItemDetail mockedItemDetail1 = new ItemDetail("ACME", "Test Detail 1");
        mockedItem1 = new Item("Test item 1", mockedItemDetail1);
        mockedItem1.setId(1L);

        ItemDetail mockedItemDetail2 = new ItemDetail("ACME", "Test Detail 2");
        mockedItem2 = new Item("Test item 2", mockedItemDetail2);
        mockedItem2.setId(2L);

        mockedItems = new ArrayList<>();
        mockedItems.add(mockedItem1);
        mockedItems.add(mockedItem2);
    }

    @Test
    public void testGetItem_whenValidId1_ThenReturnItem1() {

        Mockito.when(itemDao.findById(mockedItem1.getId()))
                .thenReturn(Optional.of(mockedItem1));

        ItemDetail expectedItemDetail = new ItemDetail("ACME", "Test Detail 1");
        Item expectedItem = new Item("Test item 1", expectedItemDetail);
        expectedItem.setId(1L);

        // when
        Item foundItem = itemService.getItem(1L);

        // then
        assertThat(foundItem.getName())
                .isEqualTo(expectedItem.getName());
        assertThat(foundItem.getItemDetail().getMaker())
                .isEqualTo(expectedItem.getItemDetail().getMaker());
        assertThat(foundItem.getItemDetail().getDescription())
                .isEqualTo(expectedItem.getItemDetail().getDescription());
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
                .thenReturn(mockedItems);

        List<Item> expectedItems = new ArrayList<>();
        expectedItems.add(mockedItem1);
        expectedItems.add(mockedItem2);

        List<Item> actualItems = itemService.getItems();

        assertEquals(expectedItems.size(), actualItems.size());
        assertEquals(expectedItems.get(0).getName(), actualItems.get(0).getName());
        assertEquals(expectedItems.get(1).getItemDetail().getMaker(), actualItems.get(1).getItemDetail().getMaker());
    }

    @Test
    public void testGetItems_WhenNoItems_ThenReturnEmptyList() {

        Mockito.when(itemDao.findAll())
                .thenReturn(new ArrayList<>());

        List<Item> actualItems = itemService.getItems();

        assertEquals(0, actualItems.size());
    }

    @Test
    public void testSaveItem_ItemDaoSaveCalled() {

        itemService.saveItem(mockedItem1);

        verify(itemDao, Mockito.times(1)).save(mockedItem1);
    }
}

