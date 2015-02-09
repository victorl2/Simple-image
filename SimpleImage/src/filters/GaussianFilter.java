package filters;
public class GaussianFilter extends Filter{
    public GaussianFilter() {
        super(5);
        super.vector[0][0] =0.0352;
        super.vector[0][1] =0.0387;
        super.vector[0][2] =0.0398;
        super.vector[0][3] =0.0387;
        super.vector[0][4] =0.0352;
        super.vector[1][0] =0.0387;
        super.vector[1][1] =0.0425;
        super.vector[1][2] =0.0438;
        super.vector[1][3] =0.0425;
        super.vector[1][4] =0.0387;
        super.vector[2][0] =0.0398;
        super.vector[2][1] =0.0438; 
        super.vector[2][2] =0.0452; 
        super.vector[2][3] =0.0438; 
        super.vector[2][4] =0.0398; 
        super.vector[3][0] =0.0387;
        super.vector[3][1] =0.0425; 
        super.vector[3][2] =0.0438; 
        super.vector[3][3] =0.0425; 
        super.vector[3][4] =0.0387; 
        super.vector[4][0] =0.0352;
        super.vector[4][1] =0.0387; 
        super.vector[4][2] =0.0398; 
        super.vector[4][3] =0.0387; 
        super.vector[4][4] =0.0352;
    }

}
