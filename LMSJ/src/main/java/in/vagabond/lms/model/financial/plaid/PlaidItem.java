package in.vagabond.lms.model.financial.plaid;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by asaxena on 8/2/17.
 */
@Data
@Entity
public class PlaidItem {
    @Id
    private String item_id;

    private String institution_id;

    private String access_token;

    private String friendly_name;

}
