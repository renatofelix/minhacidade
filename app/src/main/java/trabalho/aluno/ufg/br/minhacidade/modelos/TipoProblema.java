package trabalho.aluno.ufg.br.minhacidade.modelos;

/**
 * Created by johnn on 19/06/2018.
 */

public enum TipoProblema {
    FALTA_SINALIZACAO ("Falta Sinalização"), ENTULHO_TERRENO_BALDIO ("Entulho no Terreno Baldio"), BURACO ("Buraco"), FALTA_ILUMINACAO ("Falta de iluminação"), NAO_RECOLHERAM_LIXO ("Não Recolheram o Lixo");

    private final String name;

    private TipoProblema(String s) {
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
