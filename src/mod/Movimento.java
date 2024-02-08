package mod;

import mod.Peca;

public class Movimento {

    private Peca peca;
    private PosicaoLegal posicaoInicial;
    private PosicaoLegal posicaoFinal;

    public Movimento(Peca peca, PosicaoLegal posicaoInicial,
                     PosicaoLegal posicaoFinal){

        this.peca = peca;
        this.posicaoInicial = posicaoInicial;
        this.posicaoFinal = posicaoFinal;

    }

    public Peca getPeca() {
        return peca;
    }

    public PosicaoLegal getPosicaoFinal() {
        return posicaoFinal;
    }

    public PosicaoLegal getPosicaoInicial() {
        return posicaoInicial;
    }

}
