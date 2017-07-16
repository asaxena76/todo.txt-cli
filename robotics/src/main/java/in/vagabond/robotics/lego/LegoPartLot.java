package in.vagabond.robotics.lego;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.persistence.Id;

/**
 * Created by asaxena on 10/14/16.
 */
@Data
public class LegoPartLot {

    @Id
    String lotid;

    LegoDesign design;

    Integer quantity;

    public LegoPartLot(String line) {
        String[] split = StringUtils.split(line, ',');
        if (split.length <2)
            throw new RuntimeException("Incorrect length");
        else {
            String partnum = split[0];

            quantity = Integer.parseInt(split[1]);
        }

    }


}
