package photochopp;
/**
 * @author Victor Ferreira Teixeira da Silva
 */
public class FiltroGaussiano extends Filtro{

    public FiltroGaussiano() {
        super(5);
        super.vetor[0][0] =0.0352;
        super.vetor[0][1] =0.0387;
        super.vetor[0][2] =0.0398;
        super.vetor[0][3] =0.0387;
        super.vetor[0][4] =0.0352;
        super.vetor[1][0] =0.0387;
        super.vetor[1][1] =0.0425;
        super.vetor[1][2] =0.0438;
        super.vetor[1][3] =0.0425;
        super.vetor[1][4] =0.0387;
        super.vetor[2][0] =0.0398;
        super.vetor[2][1] =0.0438; 
        super.vetor[2][2] =0.0452; 
        super.vetor[2][3] =0.0438; 
        super.vetor[2][4] =0.0398; 
        super.vetor[3][0] =0.0387;
        super.vetor[3][1] =0.0425; 
        super.vetor[3][2] =0.0438; 
        super.vetor[3][3] =0.0425; 
        super.vetor[3][4] =0.0387; 
        super.vetor[4][0] =0.0352;
        super.vetor[4][1] =0.0387; 
        super.vetor[4][2] =0.0398; 
        super.vetor[4][3] =0.0387; 
        super.vetor[4][4] =0.0352;
    }

}
