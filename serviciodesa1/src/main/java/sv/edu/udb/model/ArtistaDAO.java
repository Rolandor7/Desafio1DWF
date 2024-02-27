package sv.edu.udb.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArtistaDAO extends AppConnection {

	 public void insert(Artista artista) throws SQLException {
		 
	        if (artista.getNombreArtista() == null || artista.getNombreArtista().trim().isEmpty()) {
	            throw new SQLException("404 Not Found");
	        }
        connect();
        
        pstmt = conn.prepareStatement("INSERT INTO artistas (nombre_artista, descripcion) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, artista.getNombreArtista());
        pstmt.setString(2, artista.getDescripcion());
        pstmt.executeUpdate();

        // obtener el último id generado
        ResultSet keys = pstmt.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);

        artista.setIdArtista(id);
        close();
    }
    

    public void update(Artista artista) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("UPDATE artistas SET nombre_artista = ?, descripcion = ? WHERE id_artista = ?");
        pstmt.setString(1, artista.getNombreArtista());
        pstmt.setString(2, artista.getDescripcion());
        pstmt.setInt(3, artista.getIdArtista());
        pstmt.executeUpdate();
        close();
    }

    public void delete(int id) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("DELETE FROM artistas WHERE id_artista = ?");
        pstmt.setInt(1, id);
        pstmt.execute();
        close();
    }

    public ArrayList<Artista> findAll() throws SQLException {
        connect();
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery("SELECT * FROM artistas");

        ArrayList<Artista> artistas = new ArrayList<Artista>();

        while (resultSet.next()) {
            Artista tmp = new Artista();
            tmp.setIdArtista(resultSet.getInt("id_artista"));
            tmp.setNombreArtista(resultSet.getString("nombre_artista"));
            tmp.setDescripcion(resultSet.getString("descripcion"));

            artistas.add(tmp);
        }
        close();

        return artistas;
    }

    public Artista findById(int id) throws SQLException {
        Artista artista = null;

        connect();
        pstmt = conn.prepareStatement("SELECT * FROM artistas WHERE id_artista = ?");
        pstmt.setInt(1, id);

        resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            artista = new Artista();
            artista.setIdArtista(resultSet.getInt("id_artista"));
            artista.setNombreArtista(resultSet.getString("nombre_artista"));
            artista.setDescripcion(resultSet.getString("descripcion"));
        }

        close();
        return artista;
    }
   

}
