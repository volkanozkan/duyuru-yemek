package yemek.quartz.job;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class YemekListesiGunEksiltmeJOB implements Job
{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException
	{

		List<String> list2 = new ArrayList<>();
		List<String> list3 = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("yemeklistesi.txt"), "UTF8"));)
		{
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null)
			{
				list2.add(sCurrentLine);
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		list3 = list2.subList(2, list2.size());
		PrintWriter writer = null;
		try
		{
			writer = new PrintWriter("yemeklistesi.txt", "UTF-8");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

		for (int i = 0; i < list3.size(); i++)
		{
			writer.println(list3.get(i));
		}
		writer.close();
	}
}
