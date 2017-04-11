package in.vagabond.lms.service.impl;

import in.vagabond.lms.service.ArchiveJobService;
import in.vagabond.lms.domain.ArchiveJob;
import in.vagabond.lms.repository.ArchiveJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing ArchiveJob.
 */
@Service
@Transactional
public class ArchiveJobServiceImpl implements ArchiveJobService{

    private final Logger log = LoggerFactory.getLogger(ArchiveJobServiceImpl.class);

    private final ArchiveJobRepository archiveJobRepository;


    // Don't want this overwrittern

    public ArchiveJobServiceImpl(ArchiveJobRepository archiveJobRepository) {
        this.archiveJobRepository = archiveJobRepository;
    }

    /**
     * Save a archiveJob.
     *
     * @param archiveJob the entity to save
     * @return the persisted entity
     */
    @Override
    public ArchiveJob save(ArchiveJob archiveJob) {
        log.debug("Request to save ArchiveJob : {}", archiveJob);
        ArchiveJob result = archiveJobRepository.save(archiveJob);
        return result;
    }

    /**
     *  Get all the archiveJobs.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ArchiveJob> findAll(Pageable pageable) {
        log.debug("Request to get all ArchiveJobs");
        Page<ArchiveJob> result = archiveJobRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one archiveJob by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ArchiveJob findOne(Long id) {
        log.debug("Request to get ArchiveJob : {}", id);
        ArchiveJob archiveJob = archiveJobRepository.findOne(id);
        return archiveJob;
    }

    /**
     *  Delete the  archiveJob by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ArchiveJob : {}", id);
        archiveJobRepository.delete(id);
    }
}
