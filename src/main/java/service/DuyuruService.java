/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Duyuru;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author VolkanOzkan
 */
public class DuyuruService
{
	public List<Duyuru> getAllDuyurular()
	{

		String url = "http://www.deu.edu.tr/DEUWeb/Guncel/v2_index_cron.html";
		Document doc = null;
		try
		{
			doc = Jsoup.connect(url).get();
		}
		catch (IOException ex)
		{
			Logger.getLogger(DuyuruService.class.getName()).log(Level.SEVERE, null, ex);
		}
		Elements links = doc.select("a[href]");

		ArrayList<String> linkler = new ArrayList<>();
		ArrayList<String> duyurular = new ArrayList<>();

		for (Element link : links)
		{
			String text = "";
			linkler.add(link.attr("abs:href"));
			if (link.text().length() > 75)
			{
				text = link.text().substring(0, 75) + "...";
			}
			else
			{
				text = link.text();
			}

			duyurular.add(text);
		}

		List<Duyuru> list = new ArrayList<>();
		for (int i = 0; i < duyurular.size(); i++)
		{
			Duyuru duyuru = new Duyuru(linkler.get(i), duyurular.get(i));
			list.add(duyuru);
		}
		return list;
	}
}
