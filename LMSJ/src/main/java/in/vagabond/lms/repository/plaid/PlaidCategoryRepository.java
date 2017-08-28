package in.vagabond.lms.repository.plaid;

import in.vagabond.lms.model.financial.plaid.PlaidCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by asaxena on 8/2/17.
 */
@Repository
public interface PlaidCategoryRepository extends CrudRepository<PlaidCategory,String> {
}
