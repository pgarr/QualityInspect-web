
@RunWith(SpringRunner.class)
@WebMvcTest(ItemRestController.class)
public class ItemRestControllerTest(){

  @Autowired
  private MockMvc mvc;
 
  @MockBean
  private ItemService itemService;

  @Test
  public void test(){
    fail();
  }

}
