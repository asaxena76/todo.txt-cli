package in.vagabond.lms.model.financial.plaid;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by asaxena on 8/2/17.
 */
@Entity
@Data
public class PlaidCategory {

    public static PlaidCategory NONE ;
    static {
        NONE = new PlaidCategory();
        NONE.setCategoryId("NONEID");
        NONE.setHierarchy("NONE");
        NONE.setPgroup("NONE");
    }


    @Id
    private String categoryId;

    private String pgroup;

    private String hierarchy;

    public String getHierarchy() {
        String sanitized = StringUtils.remove(hierarchy, "\"");

        return sanitized;
    }
}
