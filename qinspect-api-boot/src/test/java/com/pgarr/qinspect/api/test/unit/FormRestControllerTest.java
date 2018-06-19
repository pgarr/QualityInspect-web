
@RunWith(SpringRunner.class)
@WebMvcTest(FormRestController.class)
public class FormRestControllerTest(){

  @Autowired
  private MockMvc mvc;
 
  @MockBean
  private FormService formService;

  @Test
  public void test(){
    fail();
  }

}
