package br.edu.ifrn.imoveis.domain

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.icu.math.BigDecimal
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Entity(tableName = "imovel")
class Imovel : Parcelable {

    @PrimaryKey
    var id: Long = 0
    var tipo = ""
    var descricao = ""
    var preco: Double = 0.0
    var foto = ""
    var link = ""
    var modalidadeVenda = ""
    var valorAvaliacao: Double = 0.0
    var desconto: Double = 0.0
    var cidade = ""
    var endereco = ""
    var bairro = ""
    var estado = ""

    override fun toString(): String {
        return "Imovel{descricao='$descricao'}"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        // Escreve os dados para serem serializados
        dest.writeLong(id)
        dest.writeString(this.tipo)
        dest.writeString(this.descricao)
        dest.writeDouble(this.preco)
        dest.writeString(this.foto)
        dest.writeString(this.link)
        dest.writeString(this.modalidadeVenda)
        dest.writeDouble(this.valorAvaliacao)
        dest.writeDouble(this.desconto)
        dest.writeString(this.cidade)
        dest.writeString(this.endereco)
        dest.writeString(this.bairro)
        dest.writeString(this.estado)
    }

    fun readFromParcel(parcel: Parcel) {
        // Lê os dados na mesma ordem em que foram escritos
        this.id = parcel.readLong()
        this.tipo = parcel.readString()
        this.descricao = parcel.readString()
        this.preco = parcel.readDouble()
        this.foto = parcel.readString()
        this.link = parcel.readString()
        this.modalidadeVenda = parcel.readString()
        this.valorAvaliacao = parcel.readDouble()
        this.desconto = parcel.readDouble()
        this.cidade = parcel.readString()
        this.endereco = parcel.readString()
        this.bairro = parcel.readString()
        this.estado = parcel.readString()
    }

    companion object {
        private val serialVersionUID = 6601006766832473959L
        @JvmField val CREATOR: Parcelable.Creator<Imovel> = object : Parcelable.Creator<Imovel> {
            override fun createFromParcel(p: Parcel): Imovel {
                // Cria o objeto imovel com um Parcel
                val c = Imovel()
                c.readFromParcel(p)
                return c
            }

            override fun newArray(size: Int): Array<Imovel?> {
                // Retorna um array vazio
                return arrayOfNulls(size)
            }
        }
    }
}
