package resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Yemek;
import yemek.quartz.job.YemekListesiGeneratorJOB;

@Path("yemek")
public class YemekResource
{

	YemekListesiGeneratorJOB y = new YemekListesiGeneratorJOB();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Yemek> getMessages()
	{
		return y.getAllYemek();
	}
}
