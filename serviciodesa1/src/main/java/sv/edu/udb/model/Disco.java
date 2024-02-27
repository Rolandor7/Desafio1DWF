package sv.edu.udb.model;

public class Disco {

    private int id_disco;
    private String nombre_disco;
    private int id_artista;
    private int numero_canciones;
    private double precio;
    private String nombre_Artista;

    public int getIdDisco() {
        return id_disco;
    }

    public void setIdDisco(int id_disco) {
        this.id_disco = id_disco;
    }

    public String getNombreDisco() {
        return nombre_disco;
    }

    public void setNombreDisco(String nombre_disco) {
        this.nombre_disco = nombre_disco;
    }

    public int getIdArtista() {
        return id_artista;
    }

    public void setIdArtista(int id_artista) {
        this.id_artista = id_artista;
    }

    public int getNumeroCanciones() {
        return numero_canciones;
    }

    public void setNumeroCanciones(int numero_canciones) {
        this.numero_canciones = numero_canciones;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getNombreArtista() {
        return nombre_Artista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombre_Artista = nombreArtista;
    }
}
