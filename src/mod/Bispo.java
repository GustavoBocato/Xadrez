package mod;

import static java.lang.Math.abs;

public class Bispo extends Peca {

    private final int valor = 3;

    public Bispo(char cor){
        super(cor);
    }


    @Override
    public boolean consegueAlcancar(Tabuleiro tabuleiro
            , PosicaoLegal posicaoInicial, PosicaoLegal posicaoFinal) {

        // 1 o bispo pode se mover nas diagonais
        // o abs(variacaoEmLinhas) e abs(variacaoEmColunas) é igual
        // 2 a peca da posicao final se não nula deve ser de cor diferente
        // da do bispo.
        // 3 não pode haver uma peca entre a posicaoFinal e posicaoInicial

        int variacaoEmLinhas = posicaoFinal.getPosicao()[0]
                - posicaoInicial.getPosicao()[0];
        int variacaoEmColunas = posicaoFinal.getPosicao()[1]
                - posicaoInicial.getPosicao()[1];

        if(abs(variacaoEmLinhas) != abs(variacaoEmColunas)){
            return false;
        }

        Peca pecaDaPosicaoFinal = tabuleiro.getPecaDeUmaPosicao(posicaoFinal);

        if(pecaDaPosicaoFinal != null) {
            if (pecaDaPosicaoFinal.getCor() == super.getCor()) {
                return false;
            }
        }

        // Ao varrer as posicoes intermediarias deve se ter um incremento 1
        // se variacao for positiva e -1 se a variacao for negativa:
        int incrementoEmLinhas = variacaoEmLinhas/abs(variacaoEmLinhas);
        int incrementoEmColunas = variacaoEmColunas/abs(variacaoEmColunas);

        PosicaoLegal posicaoIntermediaria;
        Peca pecaIntermediaria;
        int linhaIntermediaria = posicaoInicial.getPosicao()[0];
        int colunaIntermediaria = posicaoInicial.getPosicao()[1];

        for (int i = 1; i < abs(variacaoEmLinhas) - 1; i ++) {
            linhaIntermediaria += incrementoEmLinhas;
            colunaIntermediaria += incrementoEmColunas;
            posicaoIntermediaria = new PosicaoLegal(linhaIntermediaria
                    ,colunaIntermediaria);
            pecaIntermediaria = tabuleiro.getPecaDeUmaPosicao
                    (posicaoIntermediaria);
            if (pecaIntermediaria != null){
                return false;
            }
        }

        return true;

    }


    @Override
    public String toString() {
        return " Bispo{" +
                "cor=" + super.getCor() +
                '}';
    }

}
