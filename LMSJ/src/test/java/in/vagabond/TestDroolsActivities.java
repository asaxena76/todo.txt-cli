package in.vagabond;

import in.vagabond.lms.activities.DroolsActivities;
import in.vagabond.lms.activities.drools.DroolsActivitiesImpl;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asaxena on 7/20/17.
 */
public class TestDroolsActivities {

    private DroolsActivities da;
    @Before
    public void init() {
       da = new DroolsActivitiesImpl();
    }

    @Test
    public void testAc() {

//        Transaction tx1 = new Transaction();
//        tx1.setName("ABC");
//        Transaction tx2 = new Transaction();
//        tx2.setName("DEF");
//
//        //PlaidTransaction txs = new ArrayList<>();
//
//        da.applyTransactionRules(tx1,"CategorizerKS");
    }


}
