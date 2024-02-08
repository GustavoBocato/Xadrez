package mod;

public abstract class Peca {

    private final char cor;

    public Peca(char cor){
        if (cor != 'P' && cor != 'B'){
            throw new IllegalArgumentException("Cor inválida, deve se" +
                    " entrar char igual a P ou B.");
        }
        this.cor = cor;
    }

    public abstract boolean consegueAlcancar(Tabuleiro tabuleiro
            , PosicaoLegal posicaoInicial, PosicaoLegal posicaoFinal);

    public char getCor() {
        return this.cor;
    }

    public abstract String toString();

}
