package in.vagabond.robotics.lego;

import javax.persistence.Id;

/**
 * Created by asaxena on 10/14/16.
 */
public class LegoDesign {
    @Id
    private String designId;

    private String category;
    private String description;

}
