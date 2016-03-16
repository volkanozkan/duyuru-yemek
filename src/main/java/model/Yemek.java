package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VolkanOzkan
 */
@XmlRootElement
public class Yemek
{
	private String text;
	private String link;

	public Yemek()
	{
		super();
	}

	public Yemek(String text, String link)
	{
		super();
		this.text = text;
		this.link = link;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}
}
