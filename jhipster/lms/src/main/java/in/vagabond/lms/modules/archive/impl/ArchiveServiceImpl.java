package in.vagabond.lms.modules.archive.impl;


import in.vagabond.lms.modules.archive.ArchiveService;
import in.vagabond.lms.service.LocalFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by asaxena on 4/10/17.
 */


@Component
@Slf4j
public class ArchiveServiceImpl implements ArchiveService{


    private Path startPath = Paths.get("/Archive/Inbox");

    private LocalFileService localFileService;

    public void ArchiveServiceImpl(LocalFileService localFileService) {


    }

    public void archive() throws IOException {
        log.info("----");
        log.info("Starting archive operation");
        Files.walkFileTree(startPath, new RulesBasedFileVisitor<Path>());

    }

}
