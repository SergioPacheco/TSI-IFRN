package com.algaworks.brewer.service.event.cidade;

import com.algaworks.brewer.model.Cidade;

public class CidadeSalvarEvent {

	private Cidade cidade;
	
	public CidadeSalvarEvent(Cidade cidade){
		this.cidade = cidade;
	}

	public Cidade getCidade() {
		return cidade;
	}
	
	
}
