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
    public FileVisitResult preVisitDirectory(T dir, BasicFileAttributes attrs) throws IOException {
        log.debug("PreVisiting directory {} " , dir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(T file, BasicFileAttributes attrs) throws IOException {
        log.debug("Visiting file {} " , file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(T file, IOException exc) throws IOException {
        log.debug("visit file failed");
        throw exc;
    }

    @Override
    public FileVisitResult postVisitDirectory(T dir, IOException exc) throws IOException {
        log.debug("PostVisiting directory {} " , dir);

        if (exc != null)
            throw exc;
        return FileVisitResult.CONTINUE;
    }
}
