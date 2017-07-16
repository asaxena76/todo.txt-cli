package in.vagabond.robotics.lego;

import javax.persistence.Id;
import java.util.List;

/**
 * Created by asaxena on 10/14/16.
 */
public class LegoBuild {

    @Id
    private String buildId;

    private List<LegoPartLot> parts;

}
