package in.vagabond.lms.web.rest;

import com.codahale.metrics.annotation.Timed;
import in.vagabond.lms.domain.ArchiveJob;
import in.vagabond.lms.service.ArchiveJobService;
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
 * REST controller for managing ArchiveJob.
 */
@RestController
@RequestMapping("/api")
public class ArchiveJobResource {

    private final Logger log = LoggerFactory.getLogger(ArchiveJobResource.class);

    private static final String ENTITY_NAME = "archiveJob";
        
    private final ArchiveJobService archiveJobService;

    public ArchiveJobResource(ArchiveJobService archiveJobService) {
        this.archiveJobService = archiveJobService;
    }

    /**
     * POST  /archive-jobs : Create a new archiveJob.
     *
     * @param archiveJob the archiveJob to create
     * @return the ResponseEntity with status 201 (Created) and with body the new archiveJob, or with status 400 (Bad Request) if the archiveJob has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/archive-jobs")
    @Timed
    public ResponseEntity<ArchiveJob> createArchiveJob(@RequestBody ArchiveJob archiveJob) throws URISyntaxException {
        log.debug("REST request to save ArchiveJob : {}", archiveJob);
        if (archiveJob.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new archiveJob cannot already have an ID")).body(null);
        }
        ArchiveJob result = archiveJobService.save(archiveJob);
        return ResponseEntity.created(new URI("/api/archive-jobs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /archive-jobs : Updates an existing archiveJob.
     *
     * @param archiveJob the archiveJob to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated archiveJob,
     * or with status 400 (Bad Request) if the archiveJob is not valid,
     * or with status 500 (Internal Server Error) if the archiveJob couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/archive-jobs")
    @Timed
    public ResponseEntity<ArchiveJob> updateArchiveJob(@RequestBody ArchiveJob archiveJob) throws URISyntaxException {
        log.debug("REST request to update ArchiveJob : {}", archiveJob);
        if (archiveJob.getId() == null) {
            return createArchiveJob(archiveJob);
        }
        ArchiveJob result = archiveJobService.save(archiveJob);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, archiveJob.getId().toString()))
            .body(result);
    }

    /**
     * GET  /archive-jobs : get all the archiveJobs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of archiveJobs in body
     */
    @GetMapping("/archive-jobs")
    @Timed
    public ResponseEntity<List<ArchiveJob>> getAllArchiveJobs(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of ArchiveJobs");
        Page<ArchiveJob> page = archiveJobService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/archive-jobs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /archive-jobs/:id : get the "id" archiveJob.
     *
     * @param id the id of the archiveJob to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the archiveJob, or with status 404 (Not Found)
     */
    @GetMapping("/archive-jobs/{id}")
    @Timed
    public ResponseEntity<ArchiveJob> getArchiveJob(@PathVariable Long id) {
        log.debug("REST request to get ArchiveJob : {}", id);
        ArchiveJob archiveJob = archiveJobService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(archiveJob));
    }

    /**
     * DELETE  /archive-jobs/:id : delete the "id" archiveJob.
     *
     * @param id the id of the archiveJob to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/archive-jobs/{id}")
    @Timed
    public ResponseEntity<Void> deleteArchiveJob(@PathVariable Long id) {
        log.debug("REST request to delete ArchiveJob : {}", id);
        archiveJobService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
