package trabalho.aluno.ufg.br.minhacidade.modelos;

/**
 * Created by johnn on 19/06/2018.
 */

public enum TipoStatus {
    PENDENTE ("Pendente"), REPASSADO ("Repassado"), RESOLVIDO ("Resolvido");

    private final String name;

    private TipoStatus(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
