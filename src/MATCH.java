import java.util.HashMap;

/**
 * A abstract class and the default ismatch method is based on the requirement of
 * this task, but you can override / modify this ismatch method for further use
 *
 * This can also be an interface. Here I used an abstract class.
 */
public abstract class MATCH {
    /**
     * Class to compare two single tuples according to synonyms
     * @param t1 - input tuple 1
     * @param t2 - input tuple 2
     * @param synonyms - HashMap storing the synonyms information
     * @return
     */
    public boolean ismatch(tuple<String> t1, tuple<String> t2, HashMap<String, Integer> synonyms) {
        if ((t1 == null && t2 != null) || (t1 != null && t2 == null)) {
            return false;
        }
        if (t1.getsize() != t2.getsize()) {
            return false;
        }
        for (int i = 0; i < t1.getsize(); i++) {
            String temp1 = t1.getWord(i);
            String temp2 = t2.getWord(i);
            if (temp1.equals(temp2)) {
                continue;
            } else if (synonyms.containsKey(temp1) && synonyms.containsKey(temp2)) {
                //when not equal,check synonyms groupID
                int index1 = synonyms.get(temp1);
                int index2 = synonyms.get(temp2);
                if (index1 != index2) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}