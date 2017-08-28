package in.vagabond.lms.model.financial;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by asaxena on 8/27/17.
 */
@Data
@Entity
@NoArgsConstructor
public class Account {

    @TableGenerator(name = "gen_account_id", table = "sequence_generator", pkColumnName = "sequence_name",
            valueColumnName = "sequence_value", pkColumnValue = "account_id", allocationSize = 1)
    @Id

    @GeneratedValue(
            strategy = GenerationType.TABLE, generator = "gen_account_id")

    private Long id;

    private String plaid_account_id;

    private String friendly_name;


}
