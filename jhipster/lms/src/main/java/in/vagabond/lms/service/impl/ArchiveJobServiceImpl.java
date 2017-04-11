package in.vagabond.lms.service.impl;

import in.vagabond.lms.domain.enumeration.ArchiveJobStatus;
import in.vagabond.lms.modules.archive.ArchiveService;
import in.vagabond.lms.service.ArchiveJobService;
import in.vagabond.lms.domain.ArchiveJob;
import in.vagabond.lms.repository.ArchiveJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import sun.plugin.dom.exception.InvalidStateException;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Service Implementation for managing ArchiveJob.
 */
@Service
@Transactional
public class ArchiveJobServiceImpl implements ArchiveJobService{

    private final Logger log = LoggerFactory.getLogger(ArchiveJobServiceImpl.class);

    private final ArchiveJobRepository archiveJobRepository;


    private final ArchiveService archiveService;

    private  ArchiveJob lastJob;

    public ArchiveJobServiceImpl(ArchiveJobRepository archiveJobRepository, ArchiveService archiveService) {
        this.archiveJobRepository = archiveJobRepository;
        this.archiveService = archiveService;
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


        if ( archiveService.isRunning() ) {
            throw new InvalidStateException("Job is already running");
        } else {
            archiveJob.setStatus(ArchiveJobStatus.Started);
            archiveJob.setStartTime(ZonedDateTime.now());
            lastJob = archiveJob;
            try {

                archiveService.archive();

            } catch (IOException e) {
                e.printStackTrace();
                archiveJob.setEndTime(ZonedDateTime.now());
                archiveJob.setStatus(ArchiveJobStatus.CompletedError);
            }
        }

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
    @Transactional()
    public Page<ArchiveJob> findAll(Pageable pageable) {
        log.debug("Request to get all ArchiveJobs");

        updateStatus();

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
    @Transactional()
    public ArchiveJob findOne(Long id) {
        updateStatus();
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
        if ( lastJob.getId().equals(id)) {
            if ( lastJob.getStatus().equals(ArchiveJobStatus.CompletedError)
                || lastJob.getStatus().equals(ArchiveJobStatus.CompletedSuccess)) {
                lastJob = null;
            } else {
                throw new IllegalStateException("Can't delete last running job");
            }

        }

        log.debug("Request to delete ArchiveJob : {}", id);
        archiveJobRepository.delete(id);
    }

    private void updateStatus() {
        if ( lastJob != null) {
            if ( lastJob.getStatus().equals(ArchiveJobStatus.Started)) {
                if ( ! archiveService.isRunning()) {
                    if ( archiveService.isSuccess()) {
                        lastJob.setStatus(ArchiveJobStatus.CompletedSuccess);
                    } else {
                        lastJob.setStatus(ArchiveJobStatus.CompletedError);
                    }
                }

                archiveJobRepository.save(lastJob);
            }

        }
    }
}
