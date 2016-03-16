package resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.Duyuru;
import service.DuyuruService;

@Path("duyuru")
public class DuyuruResource
{
	DuyuruService duyuruService = new DuyuruService();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Duyuru> getMessages()
	{
		return duyuruService.getAllDuyurular();
	}
}
