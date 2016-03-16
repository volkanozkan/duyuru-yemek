package yemek.quartz;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import yemek.quartz.job.YemekListesiGeneratorJOB;
import yemek.quartz.job.YemekListesiGunEksiltmeJOB;

@WebListener
public class MyApp implements javax.servlet.ServletContextListener
{

	public void run()
	{
		try
		{

			/// ************** haftanın 1 günü yenile listeyi
			JobDetail job = JobBuilder.newJob(YemekListesiGeneratorJOB.class).withIdentity("job", "group4").build();

			Trigger trigger4 = TriggerBuilder.newTrigger().withIdentity("cronTrigger4", "group4")
					.withSchedule(CronScheduleBuilder.weeklyOnDayAndHourAndMinute(7, 9, 45)).build();

			Scheduler scheduler4 = new StdSchedulerFactory().getScheduler();
			scheduler4.start();
			scheduler4.scheduleJob(job, trigger4);
			///// ********** haftanın 1 günü yenile listeyi

			///// *********** gun eksilt
			JobDetail job2 = JobBuilder.newJob(YemekListesiGunEksiltmeJOB.class).withIdentity("job2", "group5").build();

			Trigger trigger5 = TriggerBuilder.newTrigger().withIdentity("cronTrigger5", "group5")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0 15 ? * MON,TUE,WED,THU *")).build();

			Scheduler scheduler5 = new StdSchedulerFactory().getScheduler();
			scheduler5.start();
			scheduler5.scheduleJob(job2, trigger5);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
		System.out.println("ServletContextListener destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		System.out.println("ServletContextListener started");
		run();
	}

}