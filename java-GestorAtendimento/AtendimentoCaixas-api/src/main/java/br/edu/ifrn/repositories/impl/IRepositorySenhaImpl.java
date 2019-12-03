package br.edu.ifrn.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.repositories.IRepositorySenha;
import br.edu.ifrn.repositories.custom.IRepositorySenhaCustom;

public class IRepositorySenhaImpl implements IRepositorySenhaCustom {

    @Autowired
    IRepositorySenha repository;


}
