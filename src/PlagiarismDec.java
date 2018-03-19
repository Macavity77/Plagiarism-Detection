import java.util.List;

/**Plagiarism Detector
 * @author Ze Wang
 */
public class PlagiarismDec {

    public PlagiarismDec(){}

    /**
     * Getting Duplication Rate
     * @param filesynonyms
     * @param fileName1
     * @param fileName2
     * @param tSize
     * @return
     */
    public void getScore(String filesynonyms, String fileName1, String fileName2, int tSize) {
        Compare cm = new Compare();
        tuple_load ld = new tuple_load();
        cm.loadsynonyms(filesynonyms);
        System.out.println("Base File Reading:");
        List<tuple<String>> tuples1 = ld.loadfromfile(fileName1, tSize);
        System.out.println("Reference File Reading:");
        List<tuple<String>> tuples2 = ld.loadfromfile(fileName2, tSize);

        int count = cm.count(tuples1, tuples2);
        double score = ((double)count / (double)tuples1.size()) * 100;
        System.out.println("Rating Score is:");
        System.out.println(String.format("%.0f", score) + "%");
    }

    /**
     * Main function
     * @param args
     */
    public static void main(String[] args) {
        PlagiarismDec pg = new PlagiarismDec();
        String synonysmFile;
        String file1;
        String file2;
        int size = 3;//default value

        if (args.length < 3 || args.length > 4) {
            System.err.println("Wrong format of input");
            System.exit(-1);
        }
        synonysmFile = args[0];
        file1 = args[1];
        file2 = args[2];
        if (args.length == 4) {
            size = Integer.valueOf(args[3]);
        }
        pg.getScore(synonysmFile, file1, file2, size);
    }
}
