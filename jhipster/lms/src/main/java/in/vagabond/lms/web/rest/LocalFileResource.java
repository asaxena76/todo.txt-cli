package in.vagabond.lms.web.rest;

import com.codahale.metrics.annotation.Timed;
import in.vagabond.lms.domain.LocalFile;
import in.vagabond.lms.service.LocalFileService;
import in.vagabond.lms.web.rest.util.HeaderUtil;
import in.vagabond.lms.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing LocalFile.
 */
@RestController
@RequestMapping("/api")
public class LocalFileResource {

    private final Logger log = LoggerFactory.getLogger(LocalFileResource.class);

    private static final String ENTITY_NAME = "localFile";
        
    private final LocalFileService localFileService;

    public LocalFileResource(LocalFileService localFileService) {
        this.localFileService = localFileService;
    }

    /**
     * POST  /local-files : Create a new localFile.
     *
     * @param localFile the localFile to create
     * @return the ResponseEntity with status 201 (Created) and with body the new localFile, or with status 400 (Bad Request) if the localFile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/local-files")
    @Timed
    public ResponseEntity<LocalFile> createLocalFile(@RequestBody LocalFile localFile) throws URISyntaxException {
        log.debug("REST request to save LocalFile : {}", localFile);
        if (localFile.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new localFile cannot already have an ID")).body(null);
        }
        LocalFile result = localFileService.save(localFile);
        return ResponseEntity.created(new URI("/api/local-files/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /local-files : Updates an existing localFile.
     *
     * @param localFile the localFile to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated localFile,
     * or with status 400 (Bad Request) if the localFile is not valid,
     * or with status 500 (Internal Server Error) if the localFile couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/local-files")
    @Timed
    public ResponseEntity<LocalFile> updateLocalFile(@RequestBody LocalFile localFile) throws URISyntaxException {
        log.debug("REST request to update LocalFile : {}", localFile);
        if (localFile.getId() == null) {
            return createLocalFile(localFile);
        }
        LocalFile result = localFileService.save(localFile);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, localFile.getId().toString()))
            .body(result);
    }

    /**
     * GET  /local-files : get all the localFiles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of localFiles in body
     */
    @GetMapping("/local-files")
    @Timed
    public ResponseEntity<List<LocalFile>> getAllLocalFiles(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of LocalFiles");
        Page<LocalFile> page = localFileService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/local-files");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /local-files/:id : get the "id" localFile.
     *
     * @param id the id of the localFile to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the localFile, or with status 404 (Not Found)
     */
    @GetMapping("/local-files/{id}")
    @Timed
    public ResponseEntity<LocalFile> getLocalFile(@PathVariable Long id) {
        log.debug("REST request to get LocalFile : {}", id);
        LocalFile localFile = localFileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(localFile));
    }

    /**
     * DELETE  /local-files/:id : delete the "id" localFile.
     *
     * @param id the id of the localFile to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/local-files/{id}")
    @Timed
    public ResponseEntity<Void> deleteLocalFile(@PathVariable Long id) {
        log.debug("REST request to delete LocalFile : {}", id);
        localFileService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
