import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class MyJob implements org.quartz.Job {

    public MyJob() {
        System.out.println("MyJob construct");
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOG.info("MyJob THREAD= {}", Thread.currentThread().getName());
        System.err.println("Hello World!  MyJob is executing.");
    }
}