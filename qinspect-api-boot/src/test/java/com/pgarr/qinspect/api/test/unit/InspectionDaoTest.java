
import com.pgarr.qinspect.api.entity.FaultPicture;
import com.pgarr.qinspect.api.entity.Form;
import com.pgarr.qinspect.api.entity.Inspection;
import com.pgarr.qinspect.api.entity.Item;
import com.pgarr.qinspect.api.entity.ItemDetail;
import com.pgarr.qinspect.api.entity.Result;
import com.pgarr.qinspect.api.entity.Step;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FormTest {

    private static Inspection testInspection;
    private static Form testForm;
    private static Item testItem;
    private static ItemDetail testItemDetail;
    private static Result testResult1;
    private static Result testResult2;
    private static Step testStep1;
    private static Step testStep2;
    
    private static List<Result> testResults;

    @BeforeClass
    public static void setUpClass() {
    
        testItemDetail = new ItemDetail("ACME", "Test Detail 1");
        testItem = new Item("Test Item", null);
        testItem.setItemDetail(testItemDetail);
        
        testForm = new Form("Test Form", "Test", false);
        testForm.setItem(testItem);
        
        testInspection = new Inspection("Test Inspection 1", Date.from(Instant.now()),
                Date.from(Instant.now()), "Test Inspector 1", "Warsaw", 1, ResultType.ACCEPTED,
                true);
        testInspection.setForm(testForm);

        testResult1 = new Result(ResultType.GOOD, "");
        testStep1 = new Step("Test Step 1", "Test", 1);
        testResult1.setStep(testStep1);
        
        testResult2 = new Result(ResultType.ACCEPTED, "Test");
        testStep2 = new Step("Test Step 2", "Test", 2);
        testResult2.setStep(testStep2);
        
        testResults.add(testResult1);
        testResults.add(testResult2);
        testInspection.setResults(testResults);
       
    }
    
    // This should find full Inspection with: Form with Item with ItemDetail and List of Results with Step and List of FaultPicture
    @Test
    public void testFindById(){
    
    }
}
