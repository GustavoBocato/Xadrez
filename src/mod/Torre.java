package mod;

import mod.Peca;
import mod.PosicaoLegal;
import mod.Tabuleiro;

import static java.lang.Math.abs;

public class Torre extends Peca{

    private final int valor = 5;

    public Torre(char cor){
        super(cor);
    }

    @Override
    public boolean consegueAlcancar(Tabuleiro tabuleiro
            , PosicaoLegal posicaoInicial, PosicaoLegal posicaoFinal) {

        //1 Ou a variacaoEmLinhas == 0 ou variacaoEmColunas == 0
        //2 A torre nao pode atravessar obstaculos, ou seja deve se
        // verificar que nao existe nenhuma peca entre posicao final e
        // inicial.

        int variacaoEmLinhas = posicaoFinal.getPosicao()[0]
                - posicaoInicial.getPosicao()[0];
        int variacaoEmColunas = posicaoFinal.getPosicao()[1]
                - posicaoInicial.getPosicao()[1];

        if(variacaoEmLinhas != 0 && variacaoEmColunas != 0){
            return false;
        }

        Peca pecaDaPosicaoFinal = tabuleiro.getPecaDeUmaPosicao(posicaoFinal);

        if (pecaDaPosicaoFinal != null) {
            if (pecaDaPosicaoFinal.getCor() == super.getCor()) {
                return false;
            }
        }

        PosicaoLegal posicaoNoMeioDoCaminho;
        Peca pecaNoMeioDoCaminho;
        int colunaIntermediaria = posicaoInicial.getPosicao()[1];
        int linhaIntermediaria = posicaoInicial.getPosicao()[0];
        int incrementoEmLinhas = 0;
        int incrementoEmColunas = 0;
        int somaDosAbsolutosDasVariacoes = abs(variacaoEmLinhas) +
                abs(variacaoEmColunas);

        if(variacaoEmLinhas != 0){
            incrementoEmLinhas = variacaoEmLinhas/abs(variacaoEmLinhas);
        }else{
            incrementoEmColunas = variacaoEmColunas/abs(variacaoEmColunas);
        }


        for (int i = 1; i < somaDosAbsolutosDasVariacoes - 1; i++) {

            linhaIntermediaria += incrementoEmLinhas;
            colunaIntermediaria += incrementoEmColunas;

            posicaoNoMeioDoCaminho = new PosicaoLegal(linhaIntermediaria,
                    colunaIntermediaria);
            pecaNoMeioDoCaminho = tabuleiro.getPecaDeUmaPosicao
                    (posicaoNoMeioDoCaminho);

            if (pecaNoMeioDoCaminho != null){
                return false;
            }
        }

        return true;

    }

    public String toString() {
        return " Torre{" +
                "cor=" + super.getCor() +
                "} ";
    }

}
