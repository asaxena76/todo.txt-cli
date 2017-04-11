package in.vagabond.lms.web.rest;

import in.vagabond.lms.LmsApp;

import in.vagabond.lms.domain.LocalFile;
import in.vagabond.lms.repository.LocalFileRepository;
import in.vagabond.lms.service.LocalFileService;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import in.vagabond.lms.domain.enumeration.FileType;
/**
 * Test class for the LocalFileResource REST controller.
 *
 * @see LocalFileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LmsApp.class)
public class LocalFileResourceIntTest {

    private static final String DEFAULT_LOCAL_PATH = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_PATH = "BBBBBBBBBB";

    private static final FileType DEFAULT_TYPE = FileType.jpg;
    private static final FileType UPDATED_TYPE = FileType.java;

    private static final Integer DEFAULT_SIZE = 1;
    private static final Integer UPDATED_SIZE = 2;

    private static final LocalDate DEFAULT_DATE_ADDED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ADDED = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_REJECTED = false;
    private static final Boolean UPDATED_REJECTED = true;

    private static final Boolean DEFAULT_UPLOADED = false;
    private static final Boolean UPDATED_UPLOADED = true;

    private static final Boolean DEFAULT_DUPLICATE = false;
    private static final Boolean UPDATED_DUPLICATE = true;

    private static final String DEFAULT_HASH = "AAAAAAAAAA";
    private static final String UPDATED_HASH = "BBBBBBBBBB";

    private static final String DEFAULT_RESOURCE_URL = "AAAAAAAAAA";
    private static final String UPDATED_RESOURCE_URL = "BBBBBBBBBB";

    @Autowired
    private LocalFileRepository localFileRepository;

    @Autowired
    private LocalFileService localFileService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLocalFileMockMvc;

    private LocalFile localFile;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LocalFileResource localFileResource = new LocalFileResource(localFileService);
        this.restLocalFileMockMvc = MockMvcBuilders.standaloneSetup(localFileResource)
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
    public static LocalFile createEntity(EntityManager em) {
        LocalFile localFile = new LocalFile()
            .localPath(DEFAULT_LOCAL_PATH)
            .type(DEFAULT_TYPE)
            .size(DEFAULT_SIZE)
            .dateAdded(DEFAULT_DATE_ADDED)
            .rejected(DEFAULT_REJECTED)
            .uploaded(DEFAULT_UPLOADED)
            .duplicate(DEFAULT_DUPLICATE)
            .hash(DEFAULT_HASH)
            .resourceUrl(DEFAULT_RESOURCE_URL);
        return localFile;
    }

    @Before
    public void initTest() {
        localFile = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocalFile() throws Exception {
        int databaseSizeBeforeCreate = localFileRepository.findAll().size();

        // Create the LocalFile
        restLocalFileMockMvc.perform(post("/api/local-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(localFile)))
            .andExpect(status().isCreated());

        // Validate the LocalFile in the database
        List<LocalFile> localFileList = localFileRepository.findAll();
        assertThat(localFileList).hasSize(databaseSizeBeforeCreate + 1);
        LocalFile testLocalFile = localFileList.get(localFileList.size() - 1);
        assertThat(testLocalFile.getLocalPath()).isEqualTo(DEFAULT_LOCAL_PATH);
        assertThat(testLocalFile.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testLocalFile.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testLocalFile.getDateAdded()).isEqualTo(DEFAULT_DATE_ADDED);
        assertThat(testLocalFile.isRejected()).isEqualTo(DEFAULT_REJECTED);
        assertThat(testLocalFile.isUploaded()).isEqualTo(DEFAULT_UPLOADED);
        assertThat(testLocalFile.isDuplicate()).isEqualTo(DEFAULT_DUPLICATE);
        assertThat(testLocalFile.getHash()).isEqualTo(DEFAULT_HASH);
        assertThat(testLocalFile.getResourceUrl()).isEqualTo(DEFAULT_RESOURCE_URL);
    }

    @Test
    @Transactional
    public void createLocalFileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = localFileRepository.findAll().size();

        // Create the LocalFile with an existing ID
        localFile.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocalFileMockMvc.perform(post("/api/local-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(localFile)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<LocalFile> localFileList = localFileRepository.findAll();
        assertThat(localFileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLocalFiles() throws Exception {
        // Initialize the database
        localFileRepository.saveAndFlush(localFile);

        // Get all the localFileList
        restLocalFileMockMvc.perform(get("/api/local-files?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(localFile.getId().intValue())))
            .andExpect(jsonPath("$.[*].localPath").value(hasItem(DEFAULT_LOCAL_PATH.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE)))
            .andExpect(jsonPath("$.[*].dateAdded").value(hasItem(DEFAULT_DATE_ADDED.toString())))
            .andExpect(jsonPath("$.[*].rejected").value(hasItem(DEFAULT_REJECTED.booleanValue())))
            .andExpect(jsonPath("$.[*].uploaded").value(hasItem(DEFAULT_UPLOADED.booleanValue())))
            .andExpect(jsonPath("$.[*].duplicate").value(hasItem(DEFAULT_DUPLICATE.booleanValue())))
            .andExpect(jsonPath("$.[*].hash").value(hasItem(DEFAULT_HASH.toString())))
            .andExpect(jsonPath("$.[*].resourceUrl").value(hasItem(DEFAULT_RESOURCE_URL.toString())));
    }

    @Test
    @Transactional
    public void getLocalFile() throws Exception {
        // Initialize the database
        localFileRepository.saveAndFlush(localFile);

        // Get the localFile
        restLocalFileMockMvc.perform(get("/api/local-files/{id}", localFile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(localFile.getId().intValue()))
            .andExpect(jsonPath("$.localPath").value(DEFAULT_LOCAL_PATH.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE))
            .andExpect(jsonPath("$.dateAdded").value(DEFAULT_DATE_ADDED.toString()))
            .andExpect(jsonPath("$.rejected").value(DEFAULT_REJECTED.booleanValue()))
            .andExpect(jsonPath("$.uploaded").value(DEFAULT_UPLOADED.booleanValue()))
            .andExpect(jsonPath("$.duplicate").value(DEFAULT_DUPLICATE.booleanValue()))
            .andExpect(jsonPath("$.hash").value(DEFAULT_HASH.toString()))
            .andExpect(jsonPath("$.resourceUrl").value(DEFAULT_RESOURCE_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLocalFile() throws Exception {
        // Get the localFile
        restLocalFileMockMvc.perform(get("/api/local-files/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocalFile() throws Exception {
        // Initialize the database
        localFileService.save(localFile);

        int databaseSizeBeforeUpdate = localFileRepository.findAll().size();

        // Update the localFile
        LocalFile updatedLocalFile = localFileRepository.findOne(localFile.getId());
        updatedLocalFile
            .localPath(UPDATED_LOCAL_PATH)
            .type(UPDATED_TYPE)
            .size(UPDATED_SIZE)
            .dateAdded(UPDATED_DATE_ADDED)
            .rejected(UPDATED_REJECTED)
            .uploaded(UPDATED_UPLOADED)
            .duplicate(UPDATED_DUPLICATE)
            .hash(UPDATED_HASH)
            .resourceUrl(UPDATED_RESOURCE_URL);

        restLocalFileMockMvc.perform(put("/api/local-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLocalFile)))
            .andExpect(status().isOk());

        // Validate the LocalFile in the database
        List<LocalFile> localFileList = localFileRepository.findAll();
        assertThat(localFileList).hasSize(databaseSizeBeforeUpdate);
        LocalFile testLocalFile = localFileList.get(localFileList.size() - 1);
        assertThat(testLocalFile.getLocalPath()).isEqualTo(UPDATED_LOCAL_PATH);
        assertThat(testLocalFile.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testLocalFile.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testLocalFile.getDateAdded()).isEqualTo(UPDATED_DATE_ADDED);
        assertThat(testLocalFile.isRejected()).isEqualTo(UPDATED_REJECTED);
        assertThat(testLocalFile.isUploaded()).isEqualTo(UPDATED_UPLOADED);
        assertThat(testLocalFile.isDuplicate()).isEqualTo(UPDATED_DUPLICATE);
        assertThat(testLocalFile.getHash()).isEqualTo(UPDATED_HASH);
        assertThat(testLocalFile.getResourceUrl()).isEqualTo(UPDATED_RESOURCE_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingLocalFile() throws Exception {
        int databaseSizeBeforeUpdate = localFileRepository.findAll().size();

        // Create the LocalFile

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLocalFileMockMvc.perform(put("/api/local-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(localFile)))
            .andExpect(status().isCreated());

        // Validate the LocalFile in the database
        List<LocalFile> localFileList = localFileRepository.findAll();
        assertThat(localFileList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLocalFile() throws Exception {
        // Initialize the database
        localFileService.save(localFile);

        int databaseSizeBeforeDelete = localFileRepository.findAll().size();

        // Get the localFile
        restLocalFileMockMvc.perform(delete("/api/local-files/{id}", localFile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LocalFile> localFileList = localFileRepository.findAll();
        assertThat(localFileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocalFile.class);
    }
}
