import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

@Slf4j
public class SimpleScheduler {
    // http://www.quartz-scheduler.org/
    // http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/tutorial-lesson-06.html


    @Test
    public void test() throws SchedulerException {
        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // and start it off
        scheduler.start();
    }

    @Test
    public void test2() throws SchedulerException, InterruptedException {
        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // and start it off
//        scheduler.start();

        // define the job and tie it to our MyJob class
        JobDetail job = JobBuilder.newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);
        Thread.sleep(10 * 1000);
//        Thread.sleep(10 * 1000);

    }
}

