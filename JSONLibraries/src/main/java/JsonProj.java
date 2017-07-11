import org.json.JSONArray;
import org.json.JSONException;

public class JsonProj {
    public String[] convertToStringArray(String jsonValues) throws JSONException {
        String[] result = new String[]{};
        String[] result1 = new String[0];
        JSONArray jsonArray = new JSONArray(jsonValues);
        String s = jsonArray.optString(0);
        String s1 = jsonArray.optString(1);
        String s2 = jsonArray.optString(2);
        String s3 = jsonArray.optString(3);
        String arrayStr = jsonArray.toString();
        result = jsonArray.toList().toArray(new String[]{});
        result1 = jsonArray.toList().toArray(new String[]{});
//        JSONObject jsonObject = new JSONObject(values);
        return result;
    }

    public boolean isTemplateContainsChineseLang(String template) {
        return template.contains("label_zh");
    }
}
