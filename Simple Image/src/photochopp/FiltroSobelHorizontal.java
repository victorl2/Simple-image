package photochopp;
/**
 * @author Victor Ferreira Teixeira da Silva
 */
class FiltroSobelHorizontal extends Filtro {
    FiltroSobelHorizontal(){
        super(3);
        super.vetor[0][0] =-1.0;
        super.vetor[0][1] = 0.0;
        super.vetor[0][2] =+1.0;
        super.vetor[1][0] =-2.0;
        super.vetor[1][1] = 0.0;
        super.vetor[1][2] =+2.0;
        super.vetor[2][0] =-1.0;
        super.vetor[2][1] = 0.0;
        super.vetor[2][2] =+1.0;               
    }
}
