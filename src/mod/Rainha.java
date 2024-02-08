package mod;

public class Rainha extends Peca {

    private final int valor = 9;

    public Rainha(char cor){
        super(cor);
    }

    @Override
    public boolean consegueAlcancar(Tabuleiro tabuleiro
            , PosicaoLegal posicaoInicial, PosicaoLegal posicaoFinal) {

        Bispo bispo = new Bispo(super.getCor());
        Torre torre = new Torre(super.getCor());

        boolean bool = bispo.consegueAlcancar(tabuleiro,posicaoInicial,
                posicaoFinal) || torre.consegueAlcancar(tabuleiro,posicaoInicial,
                posicaoFinal);

        return bool;
    }

    @Override
    public String toString() {
        return "Rainha{" +
                "cor=" + super.getCor() +
                '}';

    }

}
