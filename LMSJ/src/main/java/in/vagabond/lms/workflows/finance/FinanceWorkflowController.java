package in.vagabond.lms.workflows.finance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by asaxena on 7/19/17.
 */
@RestController
@RequestMapping("finance")
public class FinanceWorkflowController {

    @Autowired
    private FinancialWorkflow workflow;

    @RequestMapping("pull")
    public void pullFromPlaid() throws IOException {
         workflow.processAllTransactions();
    }

    @RequestMapping("applyrules")
    public void applyRules() throws IOException {
        workflow.applyRules();
    }

}
