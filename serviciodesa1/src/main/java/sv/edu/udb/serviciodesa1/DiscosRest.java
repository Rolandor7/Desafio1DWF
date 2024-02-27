package sv.edu.udb.serviciodesa1;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sv.edu.udb.model.Disco;
import sv.edu.udb.model.DiscoDAO;

@Path("discos")
public class DiscosRest {

    DiscoDAO discosDAO = new DiscoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscos() throws SQLException {
        List<Disco> discos = discosDAO.findAll();

        return Response.status(200).entity(discos).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getDiscoById(@PathParam("id") int id) throws SQLException {
        Disco disco = discosDAO.findById(id);
        if (disco == null) {
            return Response.status(404).header("Access-Control-Allow-Origin", "*").entity("404 Not Found").build();//msj de error
        }

        return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(disco).build();//msj de confirmacion
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertDisco(@FormParam("nombre_disco") String nombre_disco, @FormParam("id_artista") int id_artista,
            @FormParam("numero_canciones") int numero_canciones, @FormParam("precio") double precio) throws SQLException {
        if (nombre_disco == null || nombre_disco.trim().isEmpty()) {
            return Response.status(400).header("Access-Control-Allow-Origin", "*").entity("400 Bad Request").build();//if para validad si el campo esta vacion de error
        }

        Disco disco = new Disco();
        disco.setNombreDisco(nombre_disco);
        disco.setIdArtista(id_artista);
        disco.setNumeroCanciones(numero_canciones);
        disco.setPrecio(precio);

        discosDAO.insert(disco);

        return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(disco).build();//msj de confirmacion
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response eliminarDisco(@PathParam("id") int id) throws SQLException {
        Disco disco = discosDAO.findById(id);
        if (disco == null) {
            return Response.status(400).entity("404 Not Found").header("Access-Control-Allow-Origin", "*").build();//msj de error
        }

        discosDAO.delete(id);
        return Response.status(204).header("Access-Control-Allow-Origin", "*").entity(disco).build();//msj de confirmacion
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateDisco(@PathParam("id") int id, @FormParam("nombre_disco") String nombre_disco,
            @FormParam("id_artista") int id_artista, @FormParam("numero_canciones") int numero_canciones,
            @FormParam("precio") double precio) throws SQLException {
        Disco disco = discosDAO.findById(id);

        if (disco == null) {
            return Response.status(404).header("Access-Control-Allow-Origin", "*").entity("404 Not Found").build();//msj de error
        }

        disco.setNombreDisco(nombre_disco);
        disco.setIdArtista(id_artista);
        disco.setNumeroCanciones(numero_canciones);
        disco.setPrecio(precio);

        discosDAO.update(disco);

        return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(disco).build();//msj confirmacion
    }
}
