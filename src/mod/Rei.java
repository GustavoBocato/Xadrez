package mod;

import static java.lang.Math.abs;

public class Rei extends Peca {

    public Rei(char cor){
        super(cor);
    }

    @Override
    public boolean consegueAlcancar(Tabuleiro tabuleiro
            , PosicaoLegal posicaoInicial, PosicaoLegal posicaoFinal) {

        // o rei em seu movimento pode andar até um passo em cada direção (horizontal ou vertical)

        int variacaoEmLinhas = posicaoFinal.getPosicao()[0]
                - posicaoInicial.getPosicao()[0];
        int variacaoEmColunas = posicaoFinal.getPosicao()[1]
                - posicaoInicial.getPosicao()[1];

        if (abs(variacaoEmColunas) > 1 || abs(variacaoEmLinhas) > 1){
            return false;
        }

        Rainha rainha = new Rainha(super.getCor());

        return rainha.consegueAlcancar(tabuleiro,posicaoInicial,
                posicaoFinal);

    }

    @Override
    public String toString() {
        return "  Rei{" +
                "cor=" + super.getCor() +
                "}  ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rei rei = (Rei) o;
        return super.getCor() == rei.getCor();
    }

}
