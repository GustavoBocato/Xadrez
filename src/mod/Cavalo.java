package mod;

import mod.Peca;

import static java.lang.Math.abs;

public class Cavalo extends Peca {

    private final int valor = 3;

    public Cavalo(char cor){
        super(cor);
    }

    @Override
    public boolean consegueAlcancar(Tabuleiro tabuleiro
            , PosicaoLegal posicaoInicial, PosicaoLegal posicaoFinal) {

        Peca pecaDaPosicaoFinal = tabuleiro.getPecaDeUmaPosicao(posicaoFinal);

        if (pecaDaPosicaoFinal != null){
            if(pecaDaPosicaoFinal.getCor() == super.getCor()){
                return false;
            }
        }

        // O cavalo dá 2 passos numa coordenada e 1 na outra.

        int numeroDePassosEmX = Math.abs(posicaoFinal.getPosicao()[0]
                - posicaoInicial.getPosicao()[0]);
        int numeroDePassosEmY = Math.abs(posicaoFinal.getPosicao()[1]
                - posicaoInicial.getPosicao()[1]);

        if(numeroDePassosEmX > 2 || numeroDePassosEmX < 1){
            return false;
        }

        if(numeroDePassosEmY > 2 || numeroDePassosEmY < 1){
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Cavalo{" +
                "cor=" + super.getCor() +
                '}';
    }

}
