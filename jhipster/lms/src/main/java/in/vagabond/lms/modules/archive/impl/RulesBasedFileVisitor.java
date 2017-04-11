package in.vagabond.lms.modules.archive.impl;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by asaxena on 4/10/17.
 */
@Slf4j
public class RulesBasedFileVisitor<T> implements FileVisitor<T> {
    @Override
    public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
        log.info("PreVisiting directory {} " , dir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
        log.info("Visiting file {} " , file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
        log.info("visit file failed");
        throw exc;
    }

    @Override
    public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
        log.info("PostVisiting directory {} " , dir);
        if (exc != null)
            throw exc;
        return FileVisitResult.CONTINUE;
    }
}
