package br.edu.ifrn.imoveis.domain

import br.edu.ifrn.imoveis.R

enum class TipoImovel(val string: Int) {
    casa(R.string.casa),
    apartamento(R.string.apartamento),
    terreno(R.string.terreno),
    favorito(R.string.favoritos)

}
