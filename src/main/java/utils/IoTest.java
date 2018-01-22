package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class IoTest {

    private static final Logger logger = LogManager.getLogger(IoTest.class.getName());

    /**
     * @param path
     * @throws IOException
     */
    public static void readFile(String path) throws IOException {
        int temp;
        StringBuffer sb = new StringBuffer();
        FileInputStream input = null;
        BufferedInputStream bs = null;
        try{
            input = new FileInputStream(path);
            bs = new BufferedInputStream(input);
            byte[] b = new byte[1024];
            while ((temp = bs.read(b))!= -1){
                sb.append(new String(b,0,temp));
            }
            logger.debug("success");

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            input.close();
            bs.close();
        }

    }

    /**
     *
     * @throws IOException
     */
    public static void fileMethod() throws IOException{
        File file = new File("C:\\Users\\蒲公英\\Desktop\\none.txt");


    }


    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException{
        readFile("C:\\Users\\蒲公英\\Desktop\\none.txt");

    }
}
