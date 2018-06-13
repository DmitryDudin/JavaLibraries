import org.apache.commons.lang3.time.DateUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class Simple2 {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        scheduler.start();//must

        JobDetail simpleJob = JobBuilder.newJob(SimpleJob2.class)
                .withIdentity("simpleJob", "group1")
                .storeDurably()
                .usingJobData("key1", "simple Job")
                .usingJobData("key2", 111)
                .build();


        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("simpleTrigger", "group1")
                .startNow()
//                .startAt(DateUtils.addSeconds(new Date(), 5))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .withRepeatCount(2)
//                        .repeatForever()
                )
                .build();

        JobDetail cronJob = JobBuilder.newJob(SimpleJob2.class)
                .withIdentity("cronJob", "group2")
                .usingJobData("key1", "crone Job")
                .usingJobData("key2", 111)
                .build();

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("croneTrigger", "group2")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")) // trigger that fires every 2 sec
                .forJob("cronJob", "group2")
                .build();

        scheduler.scheduleJob(simpleJob, simpleTrigger);
//        scheduler.scheduleJob(cronJob, cronTrigger);

        Thread.sleep(20 * 1000);
        scheduler.shutdown();
    }

/*
    @Slf4j
    @Getter
    @Setter
    public static class MyJob2 implements Job {
        static int count= 0;
        private String key1;
        private int key2;

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
            String value1 = jobDataMap.getString("key1");
            int value2 = jobDataMap.getIntValue("key2");

            LOG.info("MyJob2 class with value1={}, value2={}", value1, value2);
            LOG.info("MyJob2 class with value1={}, value2={}", key1, key2);//if we add setter with keys names, then JobDataMap values will set automatically
            LOG.info("Count= {}", count++);
        }
    }
*/
}
