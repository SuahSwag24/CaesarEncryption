import java.util.Random;

public class CaesarClass 
{
    private String messageString;
    Random keyGen = new Random();
    static final int MAX = 90 , MIN = 65;

    static final int KEYSIZE = 10;

    private int[] key = new int[26];
    private String encryptedMessage;
    
    public CaesarClass(String messageInput)
    {
        messageString = messageInput;
    }

    public String GenerateKey()
    {
        //Alphabet A - Z , ASCII 65 - 90
        char[] keyString = new char[26];

        // String messageString; ------------------------- FOR FUTURE USE
        

        //Generates random letters based on KEYSIZE
        for(int keySize = 0 ; keySize < KEYSIZE ; keySize++)
        {
            int a;
            boolean redundancyCheck;

            a = (keyGen.nextInt(MAX - MIN + 1) + MIN);

            do //Redundancy check ----------------- PASSED
            {
                redundancyCheck = true;

                for(int i = 0 ; i < key.length ; i++) //Cycles through all elements
                {
                    if(key[i] == a)
                    {
                        //If there is a match, remake a new "a"

                        redundancyCheck = false;
                        a = (keyGen.nextInt(MAX - MIN + 1) + MIN);
                        i--;
                    }
                }
            }
            while(redundancyCheck == false);

            //Set value if there is no match, proceeds to next array position
            key[keySize] = a;
        }

        //Program a filler to fill the rest of the alphabets (26)
        boolean skip = false;
        int filler , minFiller = MIN;

        for(int j = KEYSIZE ; j < key.length ; j++) //Cycles through the area without the key
        {
            skip = false;

            for(filler = minFiller ;  filler <= MAX ; filler++)
            {
                /*
                 * minFiller restricts filled letters to be reused again
                 * Cycles through all alphabets
                 */

                for(int k = 0 ; k < KEYSIZE ; k++) //Cycles through all the letters in the key
                {
                    if(key[k] == filler) //Checks for redundancy, if yes, skip to next letter
                    {
                        skip = true;
                    }
                }

                if(skip != true) //Set value if its a new letter
                {
                    key[j] = filler;
                    break;
                }
            }

            if(skip == true) //Goes back to the skipped array position to proceed to next letter
            {
                j--;
            }

            //Increment to go to next letter
            minFiller += 1;
        }

        for(int i = 0 ; i < 26 ; i++)
        {
            keyString[i] = (char) key[i];
        }

        return String.valueOf(keyString);
    }

    public String Encrypt()
    {
        int[] c = new int[50];
        char[] d = new char[50];

        //0 - 26 is alphabetic

        for(int i = 0 ; i < messageString.length() ; i++)
        {
            c[i] = (int) messageString.charAt(i);
        }

        //x - 65 + 1 = Location of each ASCII code
        for(int i = 0 ; i < messageString.length() ; i++) //Cycles all letters in the string
        {
            int j = c[i] - 65;
            d[i] = (char) key[j];
        }

        encryptedMessage = String.valueOf(d);

        return encryptedMessage;
    }

    public String Decrypt()
    {
        String output;

        int[] e = new int[50];
        char[] f = new char[50];

        for(int i = 0 ; i < messageString.length() ; i++)
        {
            e[i] = (int) encryptedMessage.charAt(i);
        }

        for(int i = 0 ; i < messageString.length() ; i++)
        {
            for(int j = 0 ; j <= 25 ; j++)
            {
                if(e[i] == (key[j]))
                {
                    f[i] = (char) (j + 65);
                }
            }
        }

        output = String.valueOf(f);
        return output;
    }
}
