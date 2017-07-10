
import org.json.JSONException;
import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;


public class GoogleTranslateApiUtil {//by Alex

//    private static final Logger LOG = LoggerFactory.getLogger(GoogleTranslateApiUtil.class);

//    @Inject
    private GoogleApiConstants googleApiConstants;

    /*public String translate(String word) {
        String result = null;

        Map<String, String> queryParameters = new HashMap<String, String>();
        queryParameters.put("key", googleApiConstants.getKey());
        queryParameters.put("source", "en");
        queryParameters.put("target", "zh-CN");
        queryParameters.put("q", word);

        String url = "https://www.googleapis.com/language/translate/" +
                "v2?key={key}&source={source}&target={target}&q={q}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-HTTP-Method-Override", "GET");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity<String>(null, headers);

        RestTemplate restclient = new RestTemplate();
        //String response=restclient.getForObject(url,request,String.class,queryParameters);

        String response = restclient.getForObject(url, String.class, queryParameters);

        try {
            //{"data": {"translations": [{"translatedText": "Здравствуйте"}]}}
            JSONObject responseJson = new JSONObject(response);
            if (responseJson.has("error")) {
                LOG.error(responseJson.getJSONObject("error").getString("message"));
            } else {
                result = responseJson.getJSONObject("data").getJSONArray("translations").getJSONObject(0).getString("translatedText");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }*/

    //@ConfigurationProperties(prefix = "tsa.googleApi")
//@Component
    private class GoogleApiConstants {

        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

}
//in config yml file
/*googleApi:
        #Need google key and enable api option
        #https://console.cloud.google.com/apis/api/translate.googleapis.com/overview?project=velvety-argon-142915
        key: AIzaSyD8luNS49KK-zm_Ck34b74Zk-VChNCYfK4*/
