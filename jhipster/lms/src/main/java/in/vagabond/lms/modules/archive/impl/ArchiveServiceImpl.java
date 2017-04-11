package in.vagabond.lms.modules.archive.impl;


import in.vagabond.lms.modules.archive.ArchiveService;
import in.vagabond.lms.service.LocalFileService;

import org.springframework.scheduling.annotation.Async;
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


public class ArchiveServiceImpl implements ArchiveService{


    private boolean running = false;


    private Path startPath = Paths.get("/Archive/Inbox");

    private LocalFileService localFileService;

    public void ArchiveServiceImpl(LocalFileService localFileService) {


    }

    @Async
    public void archive() throws IOException {
        //log.info("----");
        //log.info("Starting archive operation");
        running = true;
        try {
            Files.walkFileTree(startPath, new RulesBasedFileVisitor<Path>());
        }catch (IOException ioe) {
            throw ioe;
        } finally {
            running = false;
        }
    }

    public boolean isRunning() {

        return running;

    }

}
