package in.vagabond.lms.model.financial.plaid;

import com.plaid.client.response.TransactionsGetResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by asaxena on 8/2/17.
 */

@Entity
@Data
@NoArgsConstructor
public class PlaidLocation {

    @TableGenerator(name = "gen_plaid_location_id", table = "sequence_generator", pkColumnName = "sequence_name",
            valueColumnName = "sequence_value", pkColumnValue = "plaid_location_id", allocationSize = 1)
    @Id

    @GeneratedValue(
            strategy = GenerationType.TABLE, generator = "gen_plaid_location_id")

    private Long id;

    private String address;

    private String city;

    private String lat;

    private String lon;

    private String state;

    private String store_number;

    private String zip;


    public PlaidLocation(TransactionsGetResponse.Transaction.Location location) {
        this.address = location.getAddress();
        this.city = location.getCity();
        this.lat= (location.getLat() == null) ? null : location.getLat().toString();
        this.lon = (location.getLon() == null) ? null : location.getLon().toString();
        this.state = location.getState();
        this.store_number=location.getStoreNumber();
        this.zip= location.getZip();

    }

}
