
@RunWith(SpringRunner.class)
@WebMvcTest(InspectionRestController.class)
public class InspectionRestControllerTest(){

  @Autowired
  private MockMvc mvc;
 
  @MockBean
  private InspectionService inspectionService;

  @Test
  public void test(){
    fail();
  }

}
