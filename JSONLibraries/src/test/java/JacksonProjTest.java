import org.junit.Assert;
import org.junit.Test;


public class JacksonProjTest {
    private String jsonChineseTemplateString = "[{\"type\":\"comment-box\",\"label_zh\":\"sdsdsd\",\"label\":\"sdsd\"},{\"type\":\"text-field\",\"label_zh\":\"ddfdf\",\"label\":\"sdsdsd\"}]";
    private String jsonEnTemplateString = "[{\"type\":\"text-field\",\"label\":\"Student Name\"},{\"type\":\"checkbox\",\"label\":\"Attended All Classes\"},{\"type\":\"checkbox\",\"label\":\"Completed All Assessments\"},{\"type\":\"comment-box\",\"label\":\"Principal Comments...\"}]";
    private String jsonEnValuesString = "[\"New Year\",null,null,\"the sun\"]";
    @Test
    public void translateOnChinese() throws Exception {
        JacksonProj jacksonProj= new JacksonProj();
        Assert.assertTrue(jacksonProj.isTemplateContainsChineseLang(jsonChineseTemplateString));
        Assert.assertFalse(jacksonProj.isTemplateContainsChineseLang(jsonEnTemplateString));

        String onChinese = jacksonProj.addChineseTranslation(jsonEnValuesString);
        System.out.println(onChinese);
    }

}