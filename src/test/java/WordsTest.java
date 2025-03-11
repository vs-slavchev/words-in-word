import org.example.Loader;
import org.example.Main;
import org.example.Trie;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class WordsTest {

    private final Trie trie = new Trie();
    List<String> allWords = Loader.loadAllWords();

    @Before
    public void setUp() {
        allWords.add("A");
        allWords.add("I");
        allWords.forEach(trie::insert);
    }

    @Test
    public void whenGettingContainedWordsOfI_returnI() {
        List<String> actual = Main.getContainedWords("I", trie);

        assert (actual.size() == 1);
        assert (actual.contains("I"));
    }

    @Test
    public void whenGettingContainedWordsOfIN_returnIandIN() {
        List<String> actual = Main.getContainedWords("IN", trie);

        assert (actual.size() == 2);
        assert (actual.contains("I"));
        assert (actual.contains("IN"));
    }

    @Test
    public void whenGettingContainedWordsOfING_returnIandINandING() {
        List<String> actual = Main.getContainedWords("ING", trie);

        assert (actual.size() == 3);
        assert (actual.contains("I"));
        assert (actual.contains("IN"));
        assert (actual.contains("ING"));
    }
}
