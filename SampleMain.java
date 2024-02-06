public class SampleMain 
{
    public static void main(String[] args)
    {
        CaesarClass message1 = new CaesarClass("SUAHSWAG");
        
        System.out.println(message1.GenerateKey());
        System.out.println(message1.Encrypt());
    }    
}
