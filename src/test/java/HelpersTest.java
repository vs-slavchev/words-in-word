import org.example.Main;
import org.junit.Test;

public class HelpersTest {

    @Test
    public void whenRemovingMiddleLetter_returnOtherTwo() {
        String actual = Main.getWordsAfterRemovingOneLetter("abc", 1);

        assert (actual.equals("ac"));
    }

    @Test
    public void whenRemovingFirstLetter_returnTail() {
        String actual = Main.getWordsAfterRemovingOneLetter("abc", 0);

        assert (actual.equals("bc"));
    }

    @Test
    public void whenRemovingLastLetter_returnHead() {
        String actual = Main.getWordsAfterRemovingOneLetter("abc", 2);

        assert (actual.equals("ab"));
    }


}
