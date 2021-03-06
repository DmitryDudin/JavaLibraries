package ua.com.javatraining;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.com.javatraining.entity.Student;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JacksonProj {
    public String addChineseTranslation(String values) throws JSONException, IOException {
        String chineseLanguage = "zh";
        String aNull = "null";
        String englishLanguage = "en";
        ObjectMapper mapper = new ObjectMapper();

        //update chinese values
        if (isValuesContainsChineseLang(values)) {
            HashMap<String, List<String>> map = mapper.readValue(values, new TypeReference<HashMap<String, List<String>>>() {
            });
            map.put(chineseLanguage, new ArrayList<>());
            for (String enValue : map.get(englishLanguage)) {
                if (enValue == null) {
                    map.get(chineseLanguage).add(null);
                    continue;
                }
//            translate
                map.get(chineseLanguage).add("translate2");
            }
            String json = mapper.writeValueAsString(map);
            return json;
        }

        //create chinese values
        String[] asArray = mapper.readValue(values, String[].class);
        HashMap<String, List<String>> resultMap = new HashMap<>();
        resultMap.put(englishLanguage, new ArrayList<>());
        resultMap.put(chineseLanguage, new ArrayList<>());
        for (String str : asArray) {
            resultMap.get(englishLanguage).add(str);
            if (str == null) {
                resultMap.get(chineseLanguage).add(null);
                continue;
            }
//            translate
            resultMap.get(chineseLanguage).add("translate1");
        }
        String json = mapper.writeValueAsString(resultMap);
        return json;
    }

    public boolean isTemplateContainsChineseLang(String template) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(template);
        boolean res = (root.findValue("label_zh") == null) ? false : true;
        return res;
//        return template.contains("\"label_zh\"");
    }

    public boolean isValuesContainsChineseLang(String values) throws IOException {
//        JsonFactory jfactory = new JsonFactory();
//        JsonParser jParser = jfactory.createJsonParser(values);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(values);
        return root.has("zh");
//        return values.contains("\"zh\"");
    }

    //    Convert a JSON Array format to a Java List object.
    public static List<Student> jsonArrayStringToList(String jsonArrayString) throws IOException {
//      jsonArrayString = "[{\"name\":\"mkyong\"}, {\"name\":\"laplap\"}]";
        ObjectMapper mapper = new ObjectMapper();
        List<Student> list = mapper.readValue(jsonArrayString, new TypeReference<List<Student>>() {
        });
        return list;
    }


    //    Convert a JSON to a Map
    public static Map jsonMapStringToMap(String jsomString) throws IOException {
//        jsomString = "{\"name\":\"mkyong\", \"age\":33}";
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(jsomString, new TypeReference<Map<String, Object>>() {
        });
        return map;
    }
}
//  https://github.com/FasterXML/jackson-databind/
//  http://www.baeldung.com/jackson-collection-array
//  https://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/
