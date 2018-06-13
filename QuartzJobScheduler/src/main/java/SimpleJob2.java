import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
public class SimpleJob2 implements Job {
    static int count = 0;
    String key1;
    int key2;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
//        String value1 = jobDataMap.getString("key1");
//        int value2 = jobDataMap.getIntValue("key2");
//        LOG.info("MyJob2 class with value1={}, value2={}", value1, value2);

        LOG.info("MyJob2 class with value1={}, value2={}", key1, key2);//if we add setter with keys names, then JobDataMap values will set automatically
        LOG.info("Count= {}", count++);
    }

//    public SimpleJob2() {
//    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public void setKey2(int key2) {
        this.key2 = key2;
    }

}
