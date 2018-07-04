package trabalho.aluno.ufg.br.minhacidade.modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {

        private String login;

        private String usertype;


        private String password;

        private String id;

        private String nome;

        private String photo;

        private String email;

        private String cpf;

        private String enviados;

        private String resolvidos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEnviados() {
        return enviados;
    }

    public void setEnviados(String enviados) {
        this.enviados = enviados;
    }

    public String getResolvidos() {
        return resolvidos;
    }

    public void setResolvidos(String resolvidos) {
        this.resolvidos = resolvidos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.login);
        dest.writeString(this.usertype);
        dest.writeString(this.password);
        dest.writeString(this.id);
        dest.writeString(this.nome);
        dest.writeString(this.photo);
        dest.writeString(this.email);
        dest.writeString(this.cpf);
        dest.writeString(this.enviados);
        dest.writeString(this.resolvidos);
    }

    protected Usuario(Parcel in) {
        this.login = in.readString();
        this.usertype = in.readString();
        this.password = in.readString();
        this.id = in.readString();
        this.nome = in.readString();
        this.photo = in.readString();
        this.email = in.readString();
        this.cpf = in.readString();
        this.enviados = in.readString();
        this.resolvidos = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel source) {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
