package in.vagabond.lms.repository;

import in.vagabond.lms.domain.ArchiveJob;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ArchiveJob entity.
 */
@SuppressWarnings("unused")
public interface ArchiveJobRepository extends JpaRepository<ArchiveJob,Long> {

}
