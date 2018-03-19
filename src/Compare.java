import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

/**
 * Class Compare designed to
 * 1. Load Synonyms dictionary from the according file
 * 2. Compare tuples
 * @author Ze Wang
 */
public class Compare extends MATCH {
    /**
     * Using HashMap to work like a dictionary,
     * where words within the same synonyms group has the same groupID value in the HashMap
     * the word itself, a string, as the key in the HashMap
     * more like the idea of the Union Find
     *
     * Some when find keys(which is string) from the HashMap, if return the
     * same groupID, it's a plagiarism tuple.
     *
     * EX:
     syns.txt:
     run sprint jog
     Junming Mao

     MEANING: run -> 0, sprint -> 0, jog -> 0 (group 0 has three synonyms)
     Junming -> 1, Mao -> 1 (group 1 has two synonyms)

     The HashMap will store 5 records of previous examples
     */
    private HashMap<String, Integer> synonyms = new HashMap<>();
    private int groupID = 0;
    public Compare(){}

    /**ASSUMING: Different line of words in synonyms file means
     *           different group of Synonyms words
     * loading synonyms groups into HashMap from the file
     * @param fileName
     */
    public void loadsynonyms(String fileName) {
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            System.out.println("Reading Synonyms from file:" + fileName + "\n");
            String cur;
            while ((cur = br.readLine()) != null) {
                //pre-processing
                String[] words = cur.toLowerCase().split(" ");
                List<String> temp = Arrays.asList(words);
                for (String s : temp) {
                    if (synonyms.containsKey(s)) {
                        throw new RuntimeException("Duplicated input into synonyms:" + s);
                    } else {
                        synonyms.put(s, groupID);
                    }
                }
                groupID++;
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
    }

    /**
     * Class to count the number of matched tuples in file 1 according to file 2
     * @param t1 - input tuple list 1 from file 1
     * @param t2 - input tuple list 2 from file 2
     * @return
     */
    public int count(List<tuple<String>> t1, List<tuple<String>> t2) {
        int result = 0;
        if (synonyms == null) {
            throw new IllegalStateException("No synonyms found");
        }
        for (int i = 0; i < t1.size(); i++) {
            for (int j = 0; j < t2.size(); j++) {
                /**assuming one match is enough for the tuple in t1*/
                if (ismatch(t1.get(i), t2.get(j), synonyms)) {
                    result++;
                    break;
                }
            }
        }
        return result;
    }
}