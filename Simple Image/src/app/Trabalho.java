package app;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import photochopp.*;
import photochopp.Filtro;
import static photochopp.Operacoes.*;
/**
 * @author Victor Ferreira Teixeira da Silva
 */
public class Trabalho {
    public static void main(String[] args) throws IOException{
        try{
            String filtro = args[0].toLowerCase();
            
            if(!compareLength(args,3) && filtro.equals("rgb-para-cinza") || filtro.equals("filtro-gaussiano") || filtro.equals("complemento"))
                    throw new ArrayIndexOutOfBoundsException();
            
            //Shades of grey
            if(filtro.equals("rgb-para-cinza")){
                BufferedImage img = ImageIO.read(new File(args[1]));
                makeImage(shadesOfGrey(img),args[2]);
            } 
            //Complement
            else if(filtro.equals("complemento")){
                BufferedImage img = ImageIO.read(new File(args[1]));
                makeImage(complement(img),args[2]);
            }
            //Blur
            else if(filtro.equals("filtro-gaussiano")){
                BufferedImage img = ImageIO.read(new File(args[1]));
                FiltroGaussiano g = new FiltroGaussiano();
                makeImage(convolution(img,g),args[2]);
            }
            //Borders limiar
            else if(filtro.equals("bordas")){
                if(!compareLength(args,4))
                    throw new ArrayIndexOutOfBoundsException();
                BufferedImage img = ImageIO.read(new File(args[2]));
                makeImage(bordas(img,Double.parseDouble(args[1]) ),args[3]);
            }
            //Any filter
            else if(filtro.equals("filtro-linear")){
                int size = Integer.parseInt(args[1]);
                if(!compareLength(args,4+size*size))
                    throw new ArrayIndexOutOfBoundsException();
                
                BufferedImage img = ImageIO.read(new File(args[args.length-2]));
                Filtro f = new Filtro(size);
                int cursor = 2;
                
                //processing output image
                for(int x=0;x<size;x++){
                    for(int y=0;y<size;y++){
                        f.setValue(x, y, Double.parseDouble(args[cursor]));
                        cursor++;
                    }
                }
                //create the img in the folder
                makeImage(convolution(img,f),args[args.length-1]);    
            }
            else{
                throw new FiltroInvalidoException();
            }
        }
        catch(FiltroInvalidoException fi){
            System.out.println("Você inseriu um filtro invalido.");
        }
        catch(NumberFormatException n){
            System.out.println("Você inseriu um ou mais campos com valores invalidos.");
        }
        catch(DadoInvalidoException d){
            System.out.println("Você deve inserir um valor impar para o tamanho do filtro.");
        } 
        catch(IOException h){
            System.out.println("O caminho inserido para um dos arquivos é invalido.");
        }
        catch(ArrayIndexOutOfBoundsException ar){
            System.out.println("O total de parâmetros inseridos para execução do programa esta errado");
        }
    }
}






