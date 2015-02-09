package filters;
class VerticalSobelFilter extends Filter{
    VerticalSobelFilter(){
        super(3);
        super.vector[0][0] =-1.0;
        super.vector[0][1] =-2.0;
        super.vector[0][2] =-1.0;
        super.vector[1][0] = 0.0;
        super.vector[1][1] = 0.0;
        super.vector[1][2] = 0.0;
        super.vector[2][0] =+1.0;
        super.vector[2][1] =+2.0;
        super.vector[2][2] =+1.0;         
    }
}
