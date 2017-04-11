package in.vagabond.lms.service;

import in.vagabond.lms.domain.LocalFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing LocalFile.
 */
public interface LocalFileService {

    /**
     * Save a localFile.
     *
     * @param localFile the entity to save
     * @return the persisted entity
     */
    LocalFile save(LocalFile localFile);

    /**
     *  Get all the localFiles.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<LocalFile> findAll(Pageable pageable);

    /**
     *  Get the "id" localFile.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    LocalFile findOne(Long id);

    /**
     *  Delete the "id" localFile.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
