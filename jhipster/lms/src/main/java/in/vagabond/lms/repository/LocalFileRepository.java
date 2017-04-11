package in.vagabond.lms.repository;

import in.vagabond.lms.domain.LocalFile;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the LocalFile entity.
 */
@SuppressWarnings("unused")
public interface LocalFileRepository extends JpaRepository<LocalFile,Long> {

}
