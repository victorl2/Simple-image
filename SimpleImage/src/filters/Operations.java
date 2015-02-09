package filters;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Operations {
    /**Get the alpha value of a RGB pixel*/
    private static int getAlpha(int pixel){
        return(pixel>>24) & 0xFF;
    }
    /**Get the red value of a RGB pixel*/
    private static int getRed(int pixel){
        return (pixel>>16) & 0xFF;
    }
    /**Get the green value of a RGB pixel*/
    private static int getGreen(int pixel){
        return (pixel>>8) & 0xFF;
    }
    /**Get the blue value of a RGB pixel*/
    private static int getBlue (int pixel){
        return (pixel>>0) & 0xFF;
    }
    
    /**Creating the RGB value receiving the red , green and blue parameters:
    *The RGB integer is a combination of 4 components of 8 bits each.
    * Alpha , Red , Green and Blue .combined they make a new integer with
    * the value in the correct position, this will make the final RGB pixel.
     */
    private static int toRGB(int red , int green,int blue){
        return red<<16 | green <<8| blue;
    }
    
     /**Complement operation - the parameter is a image - return a image with the effect applied*/
    public static BufferedImage complement(BufferedImage image){
        
        int width = image.getWidth();
        int height = image.getHeight();
       
        BufferedImage newImage = new BufferedImage(width,height,image.getType());
        
        /*Reading the rgb and breaking it down to red , green and blue*/
        for(int w =0;w<width;w++){
            for(int h=0;h<height;h++){
                int pixel = image.getRGB(w, h);
                int redC = 255 - getRed(pixel);
                int greenC = 255 - getGreen(pixel);
                int blueC = 255 - getBlue(pixel);
                
                /*Making the color of the pixel by combining the bit of each position
                  00000000 00000000 00000000 11111111
                  ^ Alpha  ^Red     ^Green   ^Blue
                 */
                
                int newPixel = toRGB(redC,greenC,blueC);
                newImage.setRGB(w, h, newPixel);
            }
        }
        return newImage;
    }
    
    /**Shades of grey effect - the value is a image - return a image with the effect applied*/
    public static BufferedImage shadesOfGrey (BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
       
        BufferedImage newImage = new BufferedImage(width,height,image.getType());
        
        /*Reading the rgb and breaking it down to red , green and blue*/
        for(int w =0;w<width;w++){
            for(int h=0;h<height;h++){
                int pixel = image.getRGB(w, h);
                int intensity = (int)(getRed(pixel)*0.3 + getGreen(pixel) *0.59 + getBlue(pixel) * 0.11);
                int redC = (int) (0.3* getRed(pixel));
                int greenC =(int) (0.59* getGreen(pixel));
                int blueC =(int) (0.11* getBlue(pixel));
                
                int newPixel = toRGB(intensity,intensity,intensity);
                newImage.setRGB(w, h, newPixel);
            }
        }    
        return newImage;
    }
    
    /**Convolution Operation - values are a image and a filter - return a image with the effect applied*/
    public static BufferedImage convolution(BufferedImage image,Filter  filter){
        int width = image.getWidth();
        int height = image.getHeight();
        int u = filter.getSize() / 2;
        int v = u;
        
        BufferedImage newImage = new BufferedImage(width,height,image.getType());
        //on the image
        int newPixel = 0;
        int resultantR = 0;
        int resultantG = 0;
        int resultantB = 0;
        int k = filter.getSize() / 2;
        
        if(filter.getSize()%2 ==0){
            throw new InvalidDataException();
        }
        
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                //on the filter
                resultantR = 0;
                resultantG = 0;
                resultantB = 0;
                for(int i=-u;i<=u;i++){
                   for(int j=-v;j<=v;j++){
                       
                       if(filter.validPixel(x-i, y-j, image)){
                            int pixel = image.getRGB(x-i, y-j);
                            resultantR+= (getRed(pixel))*filter.vector[i+k][j+k];
                            resultantG+= (getGreen(pixel))*filter.vector[i+k][j+k];
                            resultantB+= (getBlue(pixel))*filter.vector[i+k][j+k];  
                           
                       }
                       
                    } 
                }
                //Check if the value is on 
                if(resultantR<0){
                    resultantR = 0;
                }
                else if(resultantR>255){
                    resultantR = 255;
                }
                if(resultantB<0){
                    resultantB = 0;
                }
                else if(resultantB>255){
                    resultantB = 255;
                }
                if(resultantG<0){
                    resultantG = 0;
                }
                else if(resultantG>255){
                    resultantG = 255;
                }
                newPixel =toRGB(resultantR,resultantG,resultantB);
                newImage.setRGB(x, y, newPixel); 
            }
            
        }
        return newImage;
    } 
    
    /**Create a image on the destiny folder with a fixed name - the parameter is a image*/
    public static void makeImage(BufferedImage image) throws IOException{
        ImageIO.write(image, "png", new File("imagem_modificada.png"));
    }
    /**Create a image on the destiny folder - values are a image and named of the file*/
    public static void makeImage(BufferedImage image,String path) throws IOException{
        ImageIO.write(image, "png", new File(path));
    }
   
    /**limiar borders - receive a image and a double value for the effect - return a image with the effect applied*/ 
    public static BufferedImage bordas(BufferedImage image,double limiar){
        HorizontalSobelFilter horizontal = new HorizontalSobelFilter();
        VerticalSobelFilter vertical = new VerticalSobelFilter();
        
        BufferedImage newImage = new BufferedImage(image.getWidth(),image.getHeight(),image.getType());
        BufferedImage h  = convolution(shadesOfGrey(image),horizontal);
        BufferedImage v  = convolution(shadesOfGrey(image),vertical);
        
        for(int x=0;x<image.getWidth();x++){
            for(int y=0;y<image.getHeight();y++){
               int red = (int) Math.sqrt( Math.pow(getRed(h.getRGB(x, y)),2 ) + Math.pow(getRed(v.getRGB(x, y)),2) );
               
               if(red>=limiar){
                   newImage.setRGB(x, y, toRGB(255,255,255));
               }
               else{
                   newImage.setRGB(x, y, toRGB(0,0,0));
               }
                
            }
        }
        return newImage;
    }
    
    /**Check the length of a array is equal to a integer value  - return true if both are equal */
    public static boolean compareLength(String[] val,int size){
        if(val.length == size)
            return true;
        return false;
    }
}
