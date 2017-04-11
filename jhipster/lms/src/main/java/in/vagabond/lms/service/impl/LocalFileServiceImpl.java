package in.vagabond.lms.service.impl;

import in.vagabond.lms.service.LocalFileService;
import in.vagabond.lms.domain.LocalFile;
import in.vagabond.lms.repository.LocalFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing LocalFile.
 */
@Service
@Transactional
public class LocalFileServiceImpl implements LocalFileService{

    private final Logger log = LoggerFactory.getLogger(LocalFileServiceImpl.class);
    
    private final LocalFileRepository localFileRepository;

    public LocalFileServiceImpl(LocalFileRepository localFileRepository) {
        this.localFileRepository = localFileRepository;
    }

    /**
     * Save a localFile.
     *
     * @param localFile the entity to save
     * @return the persisted entity
     */
    @Override
    public LocalFile save(LocalFile localFile) {
        log.debug("Request to save LocalFile : {}", localFile);
        LocalFile result = localFileRepository.save(localFile);
        return result;
    }

    /**
     *  Get all the localFiles.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LocalFile> findAll(Pageable pageable) {
        log.debug("Request to get all LocalFiles");
        Page<LocalFile> result = localFileRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one localFile by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public LocalFile findOne(Long id) {
        log.debug("Request to get LocalFile : {}", id);
        LocalFile localFile = localFileRepository.findOne(id);
        return localFile;
    }

    /**
     *  Delete the  localFile by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LocalFile : {}", id);
        localFileRepository.delete(id);
    }
}
