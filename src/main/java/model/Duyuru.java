
package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VolkanOzkan
 */
@XmlRootElement
public class Duyuru
{

	private String link;
	private String duyuruText;

	public Duyuru()
	{
	}

	public Duyuru(String link, String duyuruText)
	{
		this.link = link;
		this.duyuruText = duyuruText;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getDuyuruText()
	{
		return duyuruText;
	}

	public void setDuyuruText(String duyuruText)
	{
		this.duyuruText = duyuruText;
	}

}
