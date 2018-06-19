package com.pgarr.qinspect.api.test.unit;

import com.pgarr.qinspect.api.restcontroller.ItemRestController;
import com.pgarr.qinspect.api.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemRestController.class)
public class ItemRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemService itemService;

    @Test
    public void test() {
        fail();
    }

}
