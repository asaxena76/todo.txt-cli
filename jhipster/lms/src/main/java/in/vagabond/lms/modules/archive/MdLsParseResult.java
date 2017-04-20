package in.vagabond.lms.modules.archive;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by asaxena on 4/12/17.
 */
public class MdLsParseResult {

    public static MdLsParseResult success(JsonNode node) {
        MdLsParseResult res = new MdLsParseResult();
        res.result = Result.SUCCESS;
        res.node = node;
        return res;
    }

    public static MdLsParseResult error(JsonNode node) {
        MdLsParseResult res = new MdLsParseResult();
        res.result = Result.ERROR;
        res.node = node;
        return res;
    }

    public enum Result {
        SUCCESS,
        ERROR
    }
    private Result result;

    private JsonNode node;
}
