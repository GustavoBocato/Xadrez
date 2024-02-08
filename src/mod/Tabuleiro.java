package mod;

import java.util.*;

import static java.lang.Math.abs;

public class Tabuleiro {

    private Peca[][] posicaoAtual = new Peca[8][8];
    protected List<Movimento> listaDosMovimentos = new ArrayList<>();

    public Tabuleiro(){

        posicaoAtual[0][0] = new Torre('P');
        posicaoAtual[0][1] = new Cavalo('P');
        posicaoAtual[0][2] = new Bispo('P');
        posicaoAtual[0][3] = new Rainha('P');
        posicaoAtual[0][4] = new Rei('P');
        posicaoAtual[0][5] = new Bispo('P');
        posicaoAtual[0][6] = new Cavalo('P');
        posicaoAtual[0][7] = new Torre('P');

        for (int i = 0; i < 8; i++) {

            posicaoAtual[1][i] = new Peao('P');
            posicaoAtual[6][i] = new Peao('B');

        }

        posicaoAtual[7][0] = new Torre('B');
        posicaoAtual[7][1] = new Cavalo('B');
        posicaoAtual[7][2] = new Bispo('B');
        posicaoAtual[7][3] = new Rainha('B');
        posicaoAtual[7][4] = new Rei('B');
        posicaoAtual[7][5] = new Bispo('B');
        posicaoAtual[7][6] = new Cavalo('B');
        posicaoAtual[7][7] = new Torre('B');

    }

    public void verPosicaoAtual(){

        for (int i = 0; i < 8; i++) {

            Object[] linha = posicaoAtual[i];

            System.out.print(i + " ");

            for (int j = 0; j < 8; j++) {

                if(linha[j] == null){
                    System.out.print("     " + linha[j] + "     ");
                }else{
                    System.out.print(linha[j] + " ");
                }

            }

            System.out.println();

        }

        System.out.print("    ");

        for(int i = 0; i < 8; i++){

            System.out.print("      "+ i + "       ");

        }

        System.out.println();

    }

    public Peca getPecaDeUmaPosicao(PosicaoLegal posicao){
        return this.posicaoAtual[posicao.getPosicao()[0]][posicao.getPosicao()[1]];
    }

    public void moverPeca(PosicaoLegal posicaoInicial, PosicaoLegal posicaoFinal){

        // 1 ver se a posicao final é diferente da inicial
        // 2 ver se a peça pode alacançar a posicao final da inicial
        // 3 fazer o movimento
        // 4 ver se o rei de mesma cor da peca movida está em check ao
        // final do movimento, se sim desfazer o movimento.
        // 5 ver se peca a ser movida é um peao a ser promovido e chamar a
        // função de promoção de peão.
        // 6 se a peca a ser movida é um peao que capiturou em en passant
        // remover o peao capiturda em en passant.

        // 1
        if (posicaoInicial.equals(posicaoFinal)){
            throw new IllegalArgumentException("A posiçao final" +
                    " é igual a inicial.");
        }

        Peca pecaASerMovida = getPecaDeUmaPosicao(posicaoInicial);

        // 2
        if (!pecaASerMovida.consegueAlcancar(this,
                posicaoInicial, posicaoFinal)){
            throw new MovimentoIlegal();
        }

        Peca pecaOriginal = getPecaDeUmaPosicao(posicaoFinal);

        // 3
        this.posicaoAtual[posicaoFinal.getPosicao()[0]]
                [posicaoFinal.getPosicao()[1]] = pecaASerMovida;

        this.posicaoAtual[posicaoInicial.getPosicao()[0]]
                [posicaoInicial.getPosicao()[1]] = null;

        // 4
        if(estaEmCheck(pecaASerMovida.getCor())){

            this.posicaoAtual[posicaoFinal.getPosicao()[0]]
                    [posicaoFinal.getPosicao()[1]] = pecaOriginal;

            this.posicaoAtual[posicaoInicial.getPosicao()[0]]
                    [posicaoInicial.getPosicao()[1]] =
                    pecaASerMovida;

            throw new MovimentoIlegal();

        }

        Peao peao = new Peao(pecaASerMovida.getCor());

        if(peao.equals(pecaASerMovida) && listaDosMovimentos.size() > 2){

            if((peao.getCor() == 'B' && posicaoFinal.getPosicao()[0] ==
            0) || (peao.getCor() == 'P' && posicaoFinal.getPosicao()[0] ==
                    7)) {

                promoverPeao(posicaoInicial, posicaoFinal, peao);

            }

            int incrementoEnPassant = 1;
            if(pecaASerMovida.getCor() == 'P'){
                incrementoEnPassant = -1;
            }

            PosicaoLegal posicaoDoPeaoCapituradoEnPassant =
                    new PosicaoLegal(posicaoFinal.getPosicao()[0]
                            + incrementoEnPassant,posicaoFinal
                            .getPosicao()[1]);

            Peca peaoCapituradoEnPassant = getPecaDeUmaPosicao
                    (posicaoDoPeaoCapituradoEnPassant);

            if(this.getPodeSerCapituradoEmEnPassant(peaoCapituradoEnPassant)){

                this.posicaoAtual[posicaoDoPeaoCapituradoEnPassant
                        .getPosicao()[0]][posicaoDoPeaoCapituradoEnPassant
                        .getPosicao()[1]] = null;

            }

        }

    }

    public boolean getPodeSerCapituradoEmEnPassant(Peca peao) {

        int indiceDoUltimoMovimento = listaDosMovimentos.size() - 1;

        Peca ultimaPecaASerMovida = listaDosMovimentos.
                get(indiceDoUltimoMovimento).getPeca();

        PosicaoLegal posicaoInicialDaUltimaPecaMovida =
                listaDosMovimentos.get(indiceDoUltimoMovimento)
                        .getPosicaoInicial();
        PosicaoLegal posicaoFinalDaUltimaPecaMovida = listaDosMovimentos.get(indiceDoUltimoMovimento)
                .getPosicaoFinal();

        int varEmLinhas = posicaoFinalDaUltimaPecaMovida.getPosicao()[0] - posicaoInicialDaUltimaPecaMovida
                .getPosicao()[0];
        int absVarEmLinhas = abs(varEmLinhas);


        if(ultimaPecaASerMovida.equals(peao) && absVarEmLinhas == 2){

            return true;

        }

        return false;

    }

    public PosicaoLegal posicaoDoReideCor(char cor){

        Rei reiProcurado = new Rei(cor);

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                if(reiProcurado.equals(posicaoAtual[i][j])){
                    return new PosicaoLegal(i,j);
                }

            }

        }

        throw new IllegalArgumentException("mod.Tabuleiro não tem rei de cor " +
                cor + ".");

    }

    public void promoverPeao(PosicaoLegal posicaoInicial
            ,PosicaoLegal posicaoFinal, Peao peao){

        int linhaInicial = posicaoInicial.getPosicao()[0];
        int colunaInicial = posicaoInicial.getPosicao()[1];

        this.posicaoAtual[linhaInicial][colunaInicial] = null;

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um inteiro para escolher" +
                " uma peça: (1) mod.Rainha, (2) mod.Torre, (3) mod.Cavalo," +
                " (4) mod.Bispo.");

        int numeroDaPecaDaPromocao = sc.nextInt();

        int linhaFinal = posicaoFinal.getPosicao()[0];
        int colunaFinal = posicaoFinal.getPosicao()[1];

        if (numeroDaPecaDaPromocao == 2){
            this.posicaoAtual[linhaFinal][colunaFinal] =
                    new Torre(peao.getCor());
        }

        if (numeroDaPecaDaPromocao == 3){
            this.posicaoAtual[linhaFinal][colunaFinal] =
                    new Cavalo(peao.getCor());
        }

        if (numeroDaPecaDaPromocao == 4){
            this.posicaoAtual[linhaFinal][colunaFinal] =
                    new Bispo(peao.getCor());
        }

        else{
            this.posicaoAtual[linhaFinal][colunaFinal] =
                        new Rainha(peao.getCor());
        }

    }

    public boolean estaEmCheck(char corDoRei){

        PosicaoLegal posicaoDoRei = posicaoDoReideCor(corDoRei);

        int linhaDoRei = posicaoDoRei.getPosicao()[0];
        int colunaDoRei = posicaoDoRei.getPosicao()[1];

        char corOpostaDoRei = 'B';
        if(corOpostaDoRei == corDoRei){
            corOpostaDoRei = 'P';
        }

        int numeroDePecasDaCorOpostaEncontradas = 0;

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                if(numeroDePecasDaCorOpostaEncontradas <= 16) {

                    int absVarLinhas = abs(linhaDoRei - i);
                    int absVarColunas = abs(colunaDoRei - j);

                    boolean ehPosicaoDeInteresse = (absVarColunas
                            == absVarLinhas) || (absVarLinhas == 0 &&
                            absVarColunas != 0) || (absVarLinhas != 0 &&
                            absVarColunas == 0) || (absVarLinhas +
                            absVarColunas == 3);

                    if(ehPosicaoDeInteresse) {

                        Peca peca = this.posicaoAtual[i][j];
                        PosicaoLegal posicaoDaPeca = new PosicaoLegal(i, j);

                        if (peca != null && peca.consegueAlcancar(this,
                                posicaoDaPeca, posicaoDoRei)) {

                            return true;

                        }

                        if (peca != null && peca.getCor() == corOpostaDoRei) {

                            numeroDePecasDaCorOpostaEncontradas++;

                        }
                    }

                }else{

                    return false;

                }
            }

        }

        return false;

    }

    public void jogar(){

        Scanner sc = new Scanner(System.in);
        PosicaoLegal posicaoInicial;
        PosicaoLegal posicaoFinal;
        Peca pecaASerMovida;
        Movimento movimento;
        char corDaPecaASerMovida = 'B';
        boolean continuarJogando = true;

        while (continuarJogando){

            verPosicaoAtual();

            int linhaInicial = sc.nextInt();
            int colunaInicial = sc.nextInt();

            posicaoInicial = new PosicaoLegal(linhaInicial,
                    colunaInicial);

            int linhaFinal = sc.nextInt();
            int colunaFinal = sc.nextInt();

            posicaoFinal = new PosicaoLegal(linhaFinal,
                    colunaFinal);

            pecaASerMovida = getPecaDeUmaPosicao(posicaoInicial);

            if(pecaASerMovida.getCor() != corDaPecaASerMovida){
                System.out.println("Deve se mover uma peca da cor "
                +corDaPecaASerMovida+ ".");
                continue;
            }

            try {

                moverPeca(posicaoInicial, posicaoFinal);
                movimento = new Movimento(pecaASerMovida,posicaoInicial,
                        posicaoFinal);
                listaDosMovimentos.add(movimento);

            }catch (MovimentoIlegal | IllegalArgumentException ex){
                System.out.println("O movimento a ser feito é ilegal.");
                continue;
            }

            if(corDaPecaASerMovida == 'B'){
                corDaPecaASerMovida = 'P';
            }else{
                corDaPecaASerMovida = 'B';
            }

            if(estaEmCheck(corDaPecaASerMovida)){
                // verificar se é check mate.
            }

        }

    }


}
