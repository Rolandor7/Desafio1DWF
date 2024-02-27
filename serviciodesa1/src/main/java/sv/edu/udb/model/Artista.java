package sv.edu.udb.model;

public class Artista {

    private int id_artista;
    private String nombre_artista;
    private String descripcion;
    private String discos;

    public int getIdArtista() {
        return id_artista;
    }

    public void setIdArtista(int id_artista) {
        this.id_artista = id_artista;
    }

    public String getNombreArtista() {
        return nombre_artista;
    }

    public void setNombreArtista(String nombre_artista) {
        this.nombre_artista = nombre_artista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
  
}
