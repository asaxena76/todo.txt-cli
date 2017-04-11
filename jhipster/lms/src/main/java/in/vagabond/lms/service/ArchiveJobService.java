package in.vagabond.lms.service;

import in.vagabond.lms.domain.ArchiveJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing ArchiveJob.
 */
public interface ArchiveJobService {

    /**
     * Save a archiveJob.
     *
     * @param archiveJob the entity to save
     * @return the persisted entity
     */
    ArchiveJob save(ArchiveJob archiveJob);

    /**
     *  Get all the archiveJobs.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ArchiveJob> findAll(Pageable pageable);

    /**
     *  Get the "id" archiveJob.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ArchiveJob findOne(Long id);

    /**
     *  Delete the "id" archiveJob.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
