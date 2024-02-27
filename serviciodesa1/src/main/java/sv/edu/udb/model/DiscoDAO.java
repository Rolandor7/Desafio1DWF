package sv.edu.udb.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DiscoDAO extends AppConnection {

	public void insert(Disco disco) throws SQLException {
        if (disco.getNombreDisco() == null || disco.getNombreDisco().trim().isEmpty()) {
            throw new SQLException("404 Not Found");
        }

        connect();
        pstmt = conn.prepareStatement("INSERT INTO discos (nombre_disco, id_artista, numero_canciones, precio) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, disco.getNombreDisco());
        pstmt.setInt(2, disco.getIdArtista());
        pstmt.setInt(3, disco.getNumeroCanciones());
        pstmt.setDouble(4, disco.getPrecio());
        pstmt.executeUpdate();

        // obtener el último id generado
        ResultSet keys = pstmt.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);

        disco.setIdDisco(id);
        close();
    }

    public void update(Disco disco) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("UPDATE discos SET nombre_disco = ?, id_artista = ?, numero_canciones = ?, precio = ? WHERE id_disco = ?");
        pstmt.setString(1, disco.getNombreDisco());
        pstmt.setInt(2, disco.getIdArtista());
        pstmt.setInt(3, disco.getNumeroCanciones());
        pstmt.setDouble(4, disco.getPrecio());
        pstmt.setInt(5, disco.getIdDisco());
        pstmt.executeUpdate();
        close();
    }

    public void delete(int id) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("DELETE FROM discos WHERE id_disco = ?");
        pstmt.setInt(1, id);
        pstmt.execute();
        close();
    }

    public ArrayList<Disco> findAll() throws SQLException {
        connect();
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery("SELECT * FROM discos");

        ArrayList<Disco> discos = new ArrayList<Disco>();

        while (resultSet.next()) {
            Disco tmp = new Disco();
            tmp.setIdDisco(resultSet.getInt("id_disco"));
            tmp.setNombreDisco(resultSet.getString("nombre_disco"));
            tmp.setIdArtista(resultSet.getInt("id_artista"));
            tmp.setNumeroCanciones(resultSet.getInt("numero_canciones"));
            tmp.setPrecio(resultSet.getDouble("precio"));

            discos.add(tmp);
        }
        close();

        return discos;
    }

    public Disco findById(int id) throws SQLException {
        Disco disco = null;

        connect();
        pstmt = conn.prepareStatement("SELECT * FROM discos WHERE id_disco = ?");
        pstmt.setInt(1, id);

        resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            disco = new Disco();
            disco.setIdDisco(resultSet.getInt("id_disco"));
            disco.setNombreDisco(resultSet.getString("nombre_disco"));
            disco.setIdArtista(resultSet.getInt("id_artista"));
            disco.setNumeroCanciones(resultSet.getInt("numero_canciones"));
            disco.setPrecio(resultSet.getDouble("precio"));
            
        }

        close();
        return disco;
    }
   

}
