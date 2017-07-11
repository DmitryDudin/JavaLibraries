import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JacksonProj {
    public String addChineseTranslation(String values) throws JSONException, IOException {
        String chineseLanguage = "zh";
        String aNull = "null";
        String englishLanguage = "en";
        ObjectMapper mapper = new ObjectMapper();

        String[] asArray = mapper.readValue(values, String[].class);
        HashMap<String, List<String>> resultMap = new HashMap<>();
        resultMap.put(englishLanguage, new ArrayList<>());
        resultMap.put(chineseLanguage, new ArrayList<>());
        for (String str : asArray) {
            resultMap.get(englishLanguage).add(str);
            if (str == null) {
                resultMap.get(chineseLanguage).add(aNull);
                continue;
            }
//            translate
            resultMap.get(chineseLanguage).add("translate");
        }
        String json = mapper.writeValueAsString(resultMap);
        return json;
    }

    public boolean isTemplateContainsChineseLang(String template) {
        return template.contains("label_zh");
    }
}
