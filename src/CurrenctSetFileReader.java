import java.io.*;

/**
 * Created by Gabriel on 19/10/2015.
 */
public class CurrenctSetFileReader {
     public CurrencySet read(String path) throws IOException {
         CurrencySet set = new CurrencySet();
         BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
         String line;
         while((line = reader.readLine()) != null){
             String[] split = line.split(";");
             set.add(new Currency(split[0], split[1], split[2]));
         }
         return set;
     }
}
