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

import sv.edu.udb.model.Artista;
import sv.edu.udb.model.ArtistaDAO;

@Path("artistas")
public class ArtistasRest {

    ArtistaDAO artistasDAO = new ArtistaDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArtistas() throws SQLException {
        List<Artista> artistas = artistasDAO.findAll();

        return Response.status(200).entity(artistas).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getArtistaById(@PathParam("id") int id) throws SQLException {
        Artista artista = artistasDAO.findById(id);
        if (artista == null) {
            return Response.status(404).header("Access-Control-Allow-Origin", "*").entity("404 Not Found").build();//mensaje de error al no encontrar el artista
        }

        return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(artista).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertArtista(@FormParam("nombre_artista") String nombre_artista, @FormParam("descripcion") String descripcion) throws SQLException {
        if (nombre_artista == null || nombre_artista.trim().isEmpty()) {
            return Response.status(400).header("Access-Control-Allow-Origin", "*").entity("400 Not Found").build();//un if para validar si el campo nombre esta vacio nos de el error
        }

        Artista artista = new Artista();
        artista.setNombreArtista(nombre_artista);
        artista.setDescripcion(descripcion);

        artistasDAO.insert(artista);

        return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(artista).build();//se modifica el response status 201
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response eliminarArtista(@PathParam("id") int id) throws SQLException {
        Artista artista = artistasDAO.findById(id);
        if (artista == null) {
            return Response.status(400).entity("404 Not Found").header("Access-Control-Allow-Origin", "*").build();//msj de error
        }

        artistasDAO.delete(id);
        return Response.status(204).header("Access-Control-Allow-Origin", "*").entity(artista).build();//msj de confirmacion
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateArtista(@PathParam("id") int id, @FormParam("nombre_artista") String nombre_artista, @FormParam("descripcion") String descripcion) throws SQLException {
        Artista artista = artistasDAO.findById(id);

        if (artista == null) {
            return Response.status(404).header("Access-Control-Allow-Origin", "*").entity("404 Not Found").build();//msj de error
        }

        artista.setNombreArtista(nombre_artista);
        artista.setDescripcion(descripcion);

        artistasDAO.update(artista);

        return Response.status(204).header("Access-Control-Allow-Origin", "*").entity(artista).build();//msj de confirmacion
    }
}
