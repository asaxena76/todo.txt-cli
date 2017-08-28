package in.vagabond.lms.model.financial.plaid;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by asaxena on 8/2/17.
 */
@Data
@Entity
public class PlaidBalance {

    @TableGenerator(name = "gen_plaid_balance_id", table = "sequence_generator", pkColumnName = "sequence_name",
            valueColumnName = "sequence_value", pkColumnValue = "plaid_balance_id", allocationSize = 1)
    @Id

    @GeneratedValue(
            strategy = GenerationType.TABLE, generator = "gen_plaid_balance_id")

    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @ManyToOne
    private PlaidAccount plaidAccount;


    private Long available;

    private Long current;

    private Long plimit;


}
