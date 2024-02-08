package mod;

import java.util.Objects;

import static java.lang.Math.abs;

public class Peao extends Peca{

    private final int valor = 1;
    private boolean podeSerCapituradoEmEnPassant = false;
    private final String nome = "mod.Peao";

    public Peao(char cor){
        super(cor);
    }

    @Override
    public boolean consegueAlcancar(Tabuleiro tabuleiro
            , PosicaoLegal posicaoInicial, PosicaoLegal posicaoFinal) {

        // movimento do peao:
        //
        // Se o peao já deu o primeiro passo:
        // 1 pode dar no máximo um passo em cada coordenada.
        // 2 se for branco a variacaoEmLinhas deve ser -1.
        // 3 se for preto a variacaoEmLinhas deve ser 1.
        // 4 se o abs(variacaoEmColunas) == 1 então posicaoFinal
        // tem que ser diferente de null ou deve haver um peao
        // de cor diferente cujo o valor abs da diferenca entre posicaoFinal[0]
        // e posicaoAtual do peao do oponente deve ser igual a 1, e
        // o peao do oponente.getPodeSerCapituradoEmEnPassant() == true.
        //
        // Se o peao ainda não deu o primeiro passo:
        // 1 pode dar 2 passos para frente, ou um só e também pode
        // capiturar uma peca

        int variacaoEmLinhas = posicaoFinal.getPosicao()[0]
                - posicaoInicial.getPosicao()[0];
        int variacaoEmColunas = posicaoFinal.getPosicao()[1]
                - posicaoInicial.getPosicao()[1];

        if (abs(variacaoEmColunas) > 1){
            return false;
        }

        if(abs(variacaoEmLinhas) != 1 && abs(variacaoEmLinhas) != 2){
            return false;
        }

        int auxEnPassant = 1;

        boolean jaDeuOPrimeiroPasso = true;

        if (super.getCor() == 'P'){
            auxEnPassant = -1;
            if (posicaoInicial.getPosicao()[0] == 1){
                jaDeuOPrimeiroPasso = false;
            }
        }else{
            if (posicaoInicial.getPosicao()[0] == 6){
                jaDeuOPrimeiroPasso = false;
            }
        }

        Peca pecaDaPosicaoFinal = tabuleiro.getPecaDeUmaPosicao(posicaoFinal);

        if (jaDeuOPrimeiroPasso){
            if (super.getCor() == 'B'){
                if(variacaoEmLinhas != -1){
                    return false;
                }
            }else{
                if (variacaoEmLinhas != 1) {
                    return false;
                }
            }
            if (abs(variacaoEmColunas) == 1){
                if (pecaDaPosicaoFinal == null){

                    PosicaoLegal posicaoEnPassant = new PosicaoLegal(posicaoFinal.getPosicao()[0] + auxEnPassant,
                            posicaoFinal.getPosicao()[1]);
                    Peca peaoASofrerEnPassant = tabuleiro.getPecaDeUmaPosicao(posicaoEnPassant);

                    if (peaoASofrerEnPassant == null){
                        return false;
                    }

                    return tabuleiro.getPodeSerCapituradoEmEnPassant(peaoASofrerEnPassant);
                }else{

                    if (pecaDaPosicaoFinal.getCor() == this.getCor()){
                        return false;
                    }

                }
            }
        }else{

            if(super.getCor() == 'B'){
                if(variacaoEmLinhas < -2 || variacaoEmLinhas >= 0){
                    return false;
                }
            }else{
                if(variacaoEmLinhas > 2 || variacaoEmLinhas <= 0){
                    return false;
                }
            }
            if(abs(variacaoEmColunas) == 1){
                if (pecaDaPosicaoFinal == null){
                    return false;
                }
                if (pecaDaPosicaoFinal.getCor() == super.getCor()){
                    return false;
                }
            }

        }

        return true;

    }

    @Override
    public String toString() {
        return " Peao{" +
                "cor=" + super.getCor() +
                "} ";
    }

    public void setPodeSerCapituradoEmEnPassant(boolean bool) {
        this.podeSerCapituradoEmEnPassant = bool;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peao peao = (Peao) o;
        return nome.equals(peao.nome) && super.getCor() == peao.getCor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
