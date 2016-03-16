package yemek.quartz.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Yemek;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class YemekListesiGeneratorJOB implements Job
{

	File file = new File("yemeklistesi.txt");

	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		System.out.println("YemekListesiGeneratorJOB --->>> Time is " + new Date());

		PrintWriter writer = null;
		try
		{
			writer = new PrintWriter(file, "UTF-8");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

		System.out.println(file.getAbsolutePath());

		Document document = null;
		String indirmeLinki = "";
		String yemekListesi = "";

		try
		{
			document = Jsoup.connect("http://sks.deu.edu.tr/yemek-menusu").get();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Elements elements = document.getAllElements();

		// String all = elements.html();
		String text = elements.text();

		String[] words = text.split("Haftanın Menüsü");
		String[] wordss = null;

		String newWord = words[1];
		wordss = newWord.split("Duyurular");

		// System.out.print(wordss[0] + " ");
		yemekListesi = wordss[0];

		// /////////////////////// indirme linki
		Elements links = document.select("a[href]");
		for (Element link : links)
		{
			if (link.text().contains("İndir (PDF"))
			{
				indirmeLinki = link.attr("href");
			}
		}
		// /////////////////////////

		ArrayList<String> day = new ArrayList<String>();
		String[] gunler = null;
		String gun = "";

		gunler = yemekListesi.split("Sal,");
		gun = gunler[0];
		gunler = gun.split("P.t,");
		gun = gunler[1];
		day.add("Pazartesi" + gun);

		gunler = yemekListesi.split("Ça,");
		gun = gunler[0];
		gunler = gun.split("Sal,");
		gun = gunler[1];
		day.add("Salı" + gun);

		gunler = yemekListesi.split("Per,");
		gun = gunler[0];
		gunler = gun.split("Ça,");
		gun = gunler[1];
		day.add("Çarşamba" + gun);

		gunler = yemekListesi.split("Cum,");
		gun = gunler[0];
		gunler = gun.split("Per,");
		gun = gunler[1];
		day.add("Perşembe" + gun);

		gunler = newWord.split("Duyurular");
		gun = gunler[0];
		gunler = yemekListesi.split("Cum,");
		gun = gunler[1];
		day.add("Cuma" + gun);

		// List<Yemek> list = new ArrayList<>();
		for (int i = 0; i < day.size(); i++)
		{
			// Yemek yemek = new Yemek(day.get(i), indirmeLinki);
			// list.add(yemek);
			writer.println(day.get(i));
			writer.println(indirmeLinki);
		}

		writer.close();

	}

	public List<Yemek> getAllYemek()
	{
		List<String> list2 = new ArrayList<>();

		// System.out.println(file.getAbsolutePath());
		// System.out.println(file.exists());

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("yemeklistesi.txt"), "UTF8"));)
		{
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null)
			{
				// System.out.println(sCurrentLine);
				list2.add(sCurrentLine);
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		List<Yemek> list = new ArrayList<>();
		for (int i = 0; i < list2.size(); i = i + 2)
		{
			Yemek yemek = new Yemek(list2.get(i), list2.get(i + 1));
			list.add(yemek);
		}

		return list;
	}
}