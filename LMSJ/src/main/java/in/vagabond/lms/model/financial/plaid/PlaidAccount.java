package in.vagabond.lms.model.financial.plaid;

import com.plaid.client.response.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by asaxena on 8/2/17.
 */
@Data
@Entity
@NoArgsConstructor
public class PlaidAccount {

    public PlaidAccount(Account acc) {

        this.accountId = acc.getAccountId();
        this.name = acc.getName();
        this.mask = acc.getMask();
        this.official_name = acc.getOfficialName();
        this.type = acc.getType();
        this.subtype = acc.getSubtype();

    }
    @Id
    private String accountId;

    @ManyToOne
    private PlaidItem plaidItem;

    private String mask;

    private String name;

    private String official_name;

    private String subtype;

    private String type;

}
