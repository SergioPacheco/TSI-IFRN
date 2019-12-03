/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifrn.view;

import ifrn.modelo.Filmes;
import ifrn.persistencia.FilmesDAO;

public class FormFilmes {

    public static final int INCLUSAO  = 1;
    public static final int ALTERACAO = 2;

    private int id;
    private String titulo;
    private String link;

    public String validaDados(int tipoDeValidacao) {

        String msgErro = "";

        if (getId() == 0) {
            msgErro += "Id deve ser informado!!!<br />";
        } else {
            Filmes filmes = FilmesDAO.getInstancia().le(getId());
            if (filmes == null) {
                if (tipoDeValidacao == ALTERACAO) {
                    msgErro += "Id do filme nao cadastrado!!!<br />";
                }
            } else {
                if (tipoDeValidacao == INCLUSAO) {
                    msgErro += "Id do filme ja cadastrado!!!<br />";
                }
            }
        }

        if (getTitulo() == null || getTitulo().equals("")) {
            msgErro += "Titulo deve ser informado!!!<br />";
        }
        return msgErro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public static int getInclusao() {
        return INCLUSAO;
    }

    public static int getAlteracao() {
        return ALTERACAO;
    }

}
