package in.vagabond.robotics.lego;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by asaxena on 10/14/16.
 */

@RestController("inventory")
public class InventoryController {

    public List<LegoPartLot> myinventory() {
        return null;
    }

    public List<LegoPartLot> mywantedlist() {
        return null;
    }

    public void uploadbuildlists() {

    }

}
