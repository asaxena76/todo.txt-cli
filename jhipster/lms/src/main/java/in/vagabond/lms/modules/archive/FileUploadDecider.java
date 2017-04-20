package in.vagabond.lms.modules.archive;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

/**
 * Created by asaxena on 4/11/17.
 */
public class FileUploadDecider {

    private Path path;
    private BasicFileAttributes fileAttributes;
    private boolean isDirectory;
    FileUploadStatus fileUploadStatus;


    public FileUploadDecider(Path path , BasicFileAttributes attributes , boolean isDirectory) {
        this.path = path;
        this.fileAttributes = attributes;
        this.isDirectory = isDirectory;
    }

    public  FileUploadStatus decide() {


        return FileUploadStatus.Ignore;
    }


    public void checkFileType() {

    }

    public HashMap<String,String> getAttributes() {

        return null;
    }


}
