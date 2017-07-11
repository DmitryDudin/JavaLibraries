import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JsonProjTests {
    private String jsonChineseTemplateString = "[{\"type\":\"comment-box\",\"label_zh\":\"sdsdsd\",\"label\":\"sdsd\"},{\"type\":\"text-field\",\"label_zh\":\"ddfdf\",\"label\":\"sdsdsd\"}]";
    private String jsonEnTemplateString = "[{\"type\":\"text-field\",\"label\":\"Student Name\"},{\"type\":\"checkbox\",\"label\":\"Attended All Classes\"},{\"type\":\"checkbox\",\"label\":\"Completed All Assessments\"},{\"type\":\"comment-box\",\"label\":\"Principal Comments...\"}]";
    private String jsonEnValuesString = "[\"New Year\",null,null,\"the sun\"]";

    @Test
    public void shouldParseJsonStr() {
        JsonProj jsonUtil = new JsonProj();
        assertTrue(jsonUtil.isTemplateContainsChineseLang(jsonChineseTemplateString));
        assertFalse(jsonUtil.isTemplateContainsChineseLang(jsonEnTemplateString));

        String[] strArray = jsonUtil.convertToStringArray(jsonEnValuesString);
    }
}
