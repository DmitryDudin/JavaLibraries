import entity.Student;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;


public class JacksonProjTest {
    private String jsonChineseTemplateString = "[{\"type\":\"comment-box\",\"label_zh\":\"sdsdsd\",\"label\":\"sdsd\"},{\"type\":\"text-field\",\"label_zh\":\"ddfdf\",\"label\":\"sdsdsd\"}]";

    private String jsonEnTemplateString = "[{\"type\":\"text-field\",\"label\":\"Student Name\"},{\"type\":\"checkbox\",\"label\":\"Attended All Classes\"},{\"type\":\"checkbox\",\"label\":\"Completed All Assessments\"},{\"type\":\"comment-box\",\"label\":\"Principal Comments...\"}]";
    private String jsonEnValuesString = "[\"New Year\",null,null,\"the sun\"]";
    private String jsonValuesWithChineseTranslations = "{\"en\":[\"New Year\",null,null,\"the sun\"],\"zh\":[\"translate\",null,null,\"translate\"]}";

    @Test
    public void translateOnChinese() throws Exception {
        JacksonProj jacksonProj = new JacksonProj();
        Assert.assertTrue(jacksonProj.isTemplateContainsChineseLang(jsonChineseTemplateString));
        Assert.assertFalse(jacksonProj.isTemplateContainsChineseLang(jsonEnTemplateString));

        String onChinese = jacksonProj.addChineseTranslation(jsonEnValuesString);
        System.out.println(onChinese);
        String onChinese2 = jacksonProj.addChineseTranslation(jsonValuesWithChineseTranslations);
        System.out.println(onChinese2);
    }

    @Test
    public void jsonArrayToList() throws Exception {
        String jsonArrayString = "[{\"name\":\"Name1\"}, {\"name\":\"Name2\"}]";
        List<Student> students = JacksonProj.jsonArrayStringToList(jsonArrayString);
        System.out.println(students);
        Assert.assertThat(students.size(), is(2));
    }

    @Test
    public void jsonToMap() throws Exception {
        String jsonMapString = "{\"name\":\"mkyong\", \"age\":33}";
        Map map = JacksonProj.jsonMapStringToMap(jsonMapString);
        System.out.println(map);
        Assert.assertThat(map.keySet().size(), is(2));

    }

}