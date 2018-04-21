package brayan0428.notasapp.com.notasapp.Clases;

import java.util.Date;

public class Notas {
    private int Id;
    private String Titulo;
    private String Descripcion;
    private String Fecha;

    public Notas(int Id,String Titulo,String Descripcion,String Fecha){
        this.Id = Id;
        this.Titulo = Titulo;
        this.Descripcion = Descripcion;
        this.Fecha = Fecha;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
