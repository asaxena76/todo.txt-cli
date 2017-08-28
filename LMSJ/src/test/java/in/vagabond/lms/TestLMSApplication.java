package in.vagabond.lms;

import in.vagabond.lms.repository.plaid.PlaidAccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by asaxena on 8/2/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestLMSApplication {

    @Autowired
    private PlaidAccountRepository plaidAccountRepository;
    @Test
    public void testLoad(){

        plaidAccountRepository.count();
    }
}
