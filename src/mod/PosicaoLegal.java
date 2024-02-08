package mod;

import java.util.Arrays;

public class PosicaoLegal {

    private int[] posicao = new int[2];

    public PosicaoLegal(int linha, int coluna){

        if(linha > 7 || linha < 0){
            throw new PosicaoIlegal();
        }
        if(coluna > 7 || coluna < 0){
            throw new PosicaoIlegal();
        }

        posicao[0] = linha;
        posicao[1] = coluna;

    }

    public int[] getPosicao() {
        return posicao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PosicaoLegal that = (PosicaoLegal) o;
        return Arrays.equals(posicao, that.posicao);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(posicao);
    }

    @Override
    public String toString() {
        return "mod.PosicaoLegal{" +
                "posicao=" + Arrays.toString(posicao) +
                '}';
    }
}
