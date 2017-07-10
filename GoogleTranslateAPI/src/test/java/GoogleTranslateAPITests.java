import com.google.api.client.googleapis.compute.ComputeCredential;
import com.google.auth.oauth2.ComputeEngineCredentials;
import com.google.auth.oauth2.GoogleCredentials;
import org.junit.Test;

import com.google.cloud.translate.Translate;
//import com.google.api.services.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.io.PrintStream;

public class GoogleTranslateAPITests {

    public static void translateText(String sourceText, PrintStream out) {
        Translate translate = createTranslateService();
        Translation translation = translate.translate(sourceText, Translate.TranslateOption.sourceLanguage("ru")/*,
                Translate.TranslateOption.targetLanguage("en")*/);

        String translatedText = translation.getTranslatedText();
        out.printf("Source Text:\n\t%s\n", sourceText);
        out.printf("Translated Text:\n\t%s\n", translation.getTranslatedText());
    }

    public static Translate createTranslateService() {
//        TranslateOptions.newBuilder().setCredentials(GoogleCredentials.getApplicationDefault());
        return TranslateOptions.newBuilder()
                .setApiKey("AIzaSyD8luNS49KK-zm_Ck34b74Zk-VChNCYfK4")
                .setTargetLanguage("en")
                .build()
                .getService();
    }

    @Test
    public void testTranslate() {
        translateText("Год", System.out);
    }

}
/*    Creating an authorized service object

        To make authenticated requests to Google Translation, you must create a service object with credentials or with an API key. The simplest way to authenticate is to use Application Default Credentials. These credentials are automatically inferred from your environment, so you only need the following code to create your service object:

        import com.google.cloud.translate.Translate;
        import com.google.cloud.translate.TranslateOptions;

        Translate translate = TranslateOptions.getDefaultInstance().getService();
        Notice that this code can be also used with an API key. By default, an API key is looked for in the GOOGLE_API_KEY environment variable. Once the API key is set, you can make API calls by invoking methods on the Translation service created via TranslateOptions.getDefaultInstance().getService().

        You can also explicitly set the API key as follows:

        Translate translate = TranslateOptions.newBuilder().setApiKey("myKey").build().getService();*/

//  https://github.com/GoogleCloudPlatform/java-docs-samples/blob/master/translate/src/main/java/com/example/cloud/translate/samples/TranslateText.java
//  https://cloud.google.com/translate/docs/reference/translate#body.QUERY_PARAMETERS