import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/**Class loading tuple information from the file
 * Assuming in the input file:
 * 1. words in the input file is space divided only!
 *    Otherwise you have to using other Regex to split lines
 * 2. A new line in the file means a new start point to count
 *    and record tuples. Words in the previous line will not be
 *    taken into account
 * 3. Assuming lowercase and uppercase the same. Therefore,
 *    load all into lowercase
 *************************************************
 *    Ex: File 2 :
 *    go for a jog
 *    work to go for
 *
 *    Loading tuples of size 3
 *    is
 *    "go for a", "for a jog", "work to go", "to go for"
 **************************************************/
public class tuple_load {
    public tuple_load(){}

    /**
     * Almost the same way as reading synonyms from file
     * @param fileName
     * @param TSize
     * @return
     */
    public List<tuple<String>> loadfromfile(String fileName, int TSize) {
        BufferedReader br = null;
        FileReader fr = null;
        List<tuple<String>> result = new ArrayList<>();
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            System.out.println("Reading from file:" + fileName + "\n");
            String cur;
            while ((cur = br.readLine()) != null) {
                result.addAll(readline(cur, TSize));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException x) {
                x.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Convert from line to list of tuples, assumptions 1&2&3 applied
     * @param line
     * @param Tsize
     * @return
     */
    private List<tuple<String>> readline(String line, int Tsize) {
        List<tuple<String>> result = new ArrayList<>();
        String[] words = line.toLowerCase().split(" ");
        if (words.length < Tsize) {
            return result;
        }

        for (int i = 0; i < words.length - Tsize + 1; i++) {
            tuple<String> temp = new tuple<>();
            for (int j = i; j < i + Tsize; j++) {
                temp.addword(words[j]);
            }
            result.add(temp);
        }
        return result;
    }
}