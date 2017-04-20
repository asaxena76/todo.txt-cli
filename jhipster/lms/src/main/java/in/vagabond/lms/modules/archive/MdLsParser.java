package in.vagabond.lms.modules.archive;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

/**
 * Created by asaxena on 4/12/17.
 */
public class MdLsParser {

    private static ObjectMapper mapper = new ObjectMapper();

    public static MdLsParseResult jsonFromMdls(String mdls) {
        ObjectNode root = mapper.createObjectNode();

        String[] lines = splitIntoLines(mdls);
        for ( String l : lines){
            String lw = StringUtils.strip(l);

            String[] tokens = StringUtils.split(lw , "=" );
            if ( tokens.length != 2 )
                return MdLsParseResult.error(null);
            else {
                String key = StringUtils.strip(tokens[2]);
                String val = StringUtils.strip(tokens[1]);

                if (! val.equals("(")) {
                    if ( val.contains("\"")){
                        String valp = StringUtils.remove(val,"\"");
                        root.put(key,valp);
                    }else if ( val.contains(""))
                }
            }

        }
        return null;

    }


    public static String[] splitIntoLines(String input) {

        String[] strings = StringUtils.split(input, "\n\r");
        for ( String s : strings) {
            System.out.println(s);
        }
        return strings;
    }
}
