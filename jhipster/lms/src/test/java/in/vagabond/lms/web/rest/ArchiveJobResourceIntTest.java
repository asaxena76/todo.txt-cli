package in.vagabond.lms.web.rest;

import in.vagabond.lms.LmsApp;

import in.vagabond.lms.domain.ArchiveJob;
import in.vagabond.lms.repository.ArchiveJobRepository;
import in.vagabond.lms.service.ArchiveJobService;
import in.vagabond.lms.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static in.vagabond.lms.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import in.vagabond.lms.domain.enumeration.ArchiveJobStatus;
/**
 * Test class for the ArchiveJobResource REST controller.
 *
 * @see ArchiveJobResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LmsApp.class)
public class ArchiveJobResourceIntTest {

    private static final ZonedDateTime DEFAULT_START_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_START_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ArchiveJobStatus DEFAULT_STATUS = ArchiveJobStatus.Configured;
    private static final ArchiveJobStatus UPDATED_STATUS = ArchiveJobStatus.Started;

    private static final ZonedDateTime DEFAULT_END_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_END_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DUMMY = "AAAAAAAAAA";
    private static final String UPDATED_DUMMY = "BBBBBBBBBB";

    @Autowired
    private ArchiveJobRepository archiveJobRepository;

    @Autowired
    private ArchiveJobService archiveJobService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restArchiveJobMockMvc;

    private ArchiveJob archiveJob;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ArchiveJobResource archiveJobResource = new ArchiveJobResource(archiveJobService);
        this.restArchiveJobMockMvc = MockMvcBuilders.standaloneSetup(archiveJobResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArchiveJob createEntity(EntityManager em) {
        ArchiveJob archiveJob = new ArchiveJob()
            .startTime(DEFAULT_START_TIME)
            .status(DEFAULT_STATUS)
            .endTime(DEFAULT_END_TIME)
            .dummy(DEFAULT_DUMMY);
        return archiveJob;
    }

    @Before
    public void initTest() {
        archiveJob = createEntity(em);
    }

    @Test
    @Transactional
    public void createArchiveJob() throws Exception {
        int databaseSizeBeforeCreate = archiveJobRepository.findAll().size();

        // Create the ArchiveJob
        restArchiveJobMockMvc.perform(post("/api/archive-jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(archiveJob)))
            .andExpect(status().isCreated());

        // Validate the ArchiveJob in the database
        List<ArchiveJob> archiveJobList = archiveJobRepository.findAll();
        assertThat(archiveJobList).hasSize(databaseSizeBeforeCreate + 1);
        ArchiveJob testArchiveJob = archiveJobList.get(archiveJobList.size() - 1);
        assertThat(testArchiveJob.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testArchiveJob.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testArchiveJob.getEndTime()).isEqualTo(DEFAULT_END_TIME);
        assertThat(testArchiveJob.getDummy()).isEqualTo(DEFAULT_DUMMY);
    }

    @Test
    @Transactional
    public void createArchiveJobWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = archiveJobRepository.findAll().size();

        // Create the ArchiveJob with an existing ID
        archiveJob.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArchiveJobMockMvc.perform(post("/api/archive-jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(archiveJob)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ArchiveJob> archiveJobList = archiveJobRepository.findAll();
        assertThat(archiveJobList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllArchiveJobs() throws Exception {
        // Initialize the database
        archiveJobRepository.saveAndFlush(archiveJob);

        // Get all the archiveJobList
        restArchiveJobMockMvc.perform(get("/api/archive-jobs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(archiveJob.getId().intValue())))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(sameInstant(DEFAULT_START_TIME))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(sameInstant(DEFAULT_END_TIME))))
            .andExpect(jsonPath("$.[*].dummy").value(hasItem(DEFAULT_DUMMY.toString())));
    }

    @Test
    @Transactional
    public void getArchiveJob() throws Exception {
        // Initialize the database
        archiveJobRepository.saveAndFlush(archiveJob);

        // Get the archiveJob
        restArchiveJobMockMvc.perform(get("/api/archive-jobs/{id}", archiveJob.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(archiveJob.getId().intValue()))
            .andExpect(jsonPath("$.startTime").value(sameInstant(DEFAULT_START_TIME)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.endTime").value(sameInstant(DEFAULT_END_TIME)))
            .andExpect(jsonPath("$.dummy").value(DEFAULT_DUMMY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingArchiveJob() throws Exception {
        // Get the archiveJob
        restArchiveJobMockMvc.perform(get("/api/archive-jobs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArchiveJob() throws Exception {
        // Initialize the database
        archiveJobService.save(archiveJob);

        int databaseSizeBeforeUpdate = archiveJobRepository.findAll().size();

        // Update the archiveJob
        ArchiveJob updatedArchiveJob = archiveJobRepository.findOne(archiveJob.getId());
        updatedArchiveJob
            .startTime(UPDATED_START_TIME)
            .status(UPDATED_STATUS)
            .endTime(UPDATED_END_TIME)
            .dummy(UPDATED_DUMMY);

        restArchiveJobMockMvc.perform(put("/api/archive-jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedArchiveJob)))
            .andExpect(status().isOk());

        // Validate the ArchiveJob in the database
        List<ArchiveJob> archiveJobList = archiveJobRepository.findAll();
        assertThat(archiveJobList).hasSize(databaseSizeBeforeUpdate);
        ArchiveJob testArchiveJob = archiveJobList.get(archiveJobList.size() - 1);
        assertThat(testArchiveJob.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testArchiveJob.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testArchiveJob.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testArchiveJob.getDummy()).isEqualTo(UPDATED_DUMMY);
    }

    @Test
    @Transactional
    public void updateNonExistingArchiveJob() throws Exception {
        int databaseSizeBeforeUpdate = archiveJobRepository.findAll().size();

        // Create the ArchiveJob

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restArchiveJobMockMvc.perform(put("/api/archive-jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(archiveJob)))
            .andExpect(status().isCreated());

        // Validate the ArchiveJob in the database
        List<ArchiveJob> archiveJobList = archiveJobRepository.findAll();
        assertThat(archiveJobList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteArchiveJob() throws Exception {
        // Initialize the database
        archiveJobService.save(archiveJob);

        int databaseSizeBeforeDelete = archiveJobRepository.findAll().size();

        // Get the archiveJob
        restArchiveJobMockMvc.perform(delete("/api/archive-jobs/{id}", archiveJob.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ArchiveJob> archiveJobList = archiveJobRepository.findAll();
        assertThat(archiveJobList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArchiveJob.class);
    }
}
