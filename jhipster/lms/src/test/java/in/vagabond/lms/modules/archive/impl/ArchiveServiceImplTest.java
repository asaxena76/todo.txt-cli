package in.vagabond.lms.modules.archive.impl;

import in.vagabond.lms.LmsApp;
import in.vagabond.lms.modules.archive.ArchiveService;
import javafx.scene.shape.Arc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by asaxena on 4/10/17.
 */

public class ArchiveServiceImplTest {



    @Test
    public void testArchive() throws Exception {

        ArchiveService archiveService = new ArchiveServiceImpl();

        archiveService.archive();

    }
}
