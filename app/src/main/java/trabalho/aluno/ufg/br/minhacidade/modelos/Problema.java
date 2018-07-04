package trabalho.aluno.ufg.br.minhacidade.modelos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;


public class Problema implements Parcelable {

    TipoProblema tipoProblema;
    TipoStatus tipoStatus = TipoStatus.PENDENTE;
    String descricao;
    Date data = new Date();
    String linkImagem;
    String localizacao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFotoresponsavel() {
        return fotoresponsavel;
    }

    public void setFotoresponsavel(String fotoresponsavel) {
        this.fotoresponsavel = fotoresponsavel;
    }

    String id;
    String img;
    String responsavel;
    String comentario;
    String fotoresponsavel;



    public Problema() {
    }

    public TipoProblema getTipoProblema() {
        return tipoProblema;
    }

    public void setTipoProblema(TipoProblema tipoProblema) {
        this.tipoProblema = tipoProblema;
    }

    public TipoStatus getTipoStatus() {
        return tipoStatus;
    }

    public void setTipoStatus(TipoStatus tipoStatus) {
        this.tipoStatus = tipoStatus;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLinkImagem() {
        return linkImagem;
    }

    public void setLinkImagem(String linkImagem) {
        this.linkImagem = linkImagem;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.tipoProblema == null ? -1 : this.tipoProblema.ordinal());
        dest.writeInt(this.tipoStatus == null ? -1 : this.tipoStatus.ordinal());
        dest.writeString(this.descricao);
        dest.writeLong(this.data != null ? this.data.getTime() : -1);
        dest.writeString(this.linkImagem);
        dest.writeString(this.localizacao);
        dest.writeString(this.id);
        dest.writeString(this.img);
        dest.writeString(this.responsavel);
        dest.writeString(this.comentario);
        dest.writeString(this.fotoresponsavel);
    }

    protected Problema(Parcel in) {
        int tmpTipoProblema = in.readInt();
        this.tipoProblema = tmpTipoProblema == -1 ? null : TipoProblema.values()[tmpTipoProblema];
        int tmpTipoStatus = in.readInt();
        this.tipoStatus = tmpTipoStatus == -1 ? null : TipoStatus.values()[tmpTipoStatus];
        this.descricao = in.readString();
        long tmpData = in.readLong();
        this.data = tmpData == -1 ? null : new Date(tmpData);
        this.linkImagem = in.readString();
        this.localizacao = in.readString();
        this.id = in.readString();
        this.img = in.readString();
        this.responsavel = in.readString();
        this.comentario = in.readString();
        this.fotoresponsavel = in.readString();
    }

    public static final Creator<Problema> CREATOR = new Creator<Problema>() {
        @Override
        public Problema createFromParcel(Parcel source) {
            return new Problema(source);
        }

        @Override
        public Problema[] newArray(int size) {
            return new Problema[size];
        }
    };
}
