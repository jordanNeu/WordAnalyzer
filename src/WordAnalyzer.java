import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.util.Map.Entry;
public class WordAnalyzer {
    // Java Program that will read in a text document and return the most commonly used words found inside
    public static void main(String[] args) throws FileNotFoundException {
        // Imports our text file to be read
        File theRaven = new File("C:\\Users\\jneum\\OneDrive\\Desktop\\raven.txt");
        // Creates a HashMap to store our word count
        HashMap<String, Integer> wordCount = new HashMap<> ();
        // Creates a Scanner to scan our imported text file
        Scanner scan = new Scanner(theRaven);
        // Creates an array container to hold words
        String[] words;

        // While loop, running until there are no more words to read
        while(scan.hasNextLine()) {
            // Reads the next line
            String line = scan.nextLine();
            // Converts current word to lowercase and removes punctuation, while separating each word from the
            // sentence with a split method
            words = line.replaceAll("\\p{Punct}", "").toLowerCase().split(" ");
            // Loops through document logging repeated words, storing each word to its own counter
            for (String word : words) {
                if(!wordCount.containsKey(word)) {
                    wordCount.put(word, 1);
                }
                else {
                    wordCount.put(word, wordCount.get(word) + 1);
                }
            }
        }
        // HashMaps are difficult to sort, so we will read in the content of the HashMap into a list that is more
        // easily sorted.
        List<Map.Entry<String, Integer>> keysAndValues = new ArrayList<Map.Entry<String, Integer>>(wordCount.entrySet());
        Collections.sort(keysAndValues, new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> key, Entry<String, Integer> value) {
                return value.getValue().compareTo(key.getValue());
            }
        });
        // New LinkedHashMap that will run in descending order listing the most used words
        Map<String, Integer> wordCountSorted = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : keysAndValues) {
            wordCountSorted.put(entry.getKey(), entry.getValue());
        }
        System.out.println(wordCountSorted);
    }
}