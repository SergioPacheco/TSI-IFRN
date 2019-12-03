package ifrn.modelo;

public class HorariosFilmes {

    private int id_horarios_FK;
    private int id_filmes_FK;

    public HorariosFilmes() {

    }

    public int getId_horarios_FK() {
        return id_horarios_FK;
    }

    public void setId_horarios_FK(int id_horarios_FK) {
        this.id_horarios_FK = id_horarios_FK;
    }

    public int getId_filmes_FK() {
        return id_filmes_FK;
    }

    public void setId_filmes_FK(int id_filmes_FK) {
        this.id_filmes_FK = id_filmes_FK;
    }

}
