package mod;

public class MovimentoIlegal extends RuntimeException{

    public MovimentoIlegal(){
        super("O movimento a ser feito é ilegal.");
    }

}
