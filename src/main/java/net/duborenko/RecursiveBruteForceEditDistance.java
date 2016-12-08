package net.duborenko;

/**
 * Simple and not optimized algorithms of calculating "Minimum Edit Distance".
 *
 * This algorithm is just a straight-forward implementation of the following recurrence:<p/>
 * <img src="https://goo.gl/kpy5HC"/>
 * <p>
 * Runtime complexity of this solution is {@code O(e^n)}.
 *
 * @author Kiryl Dubarenka
 */
@Order(0)
public class RecursiveBruteForceEditDistance extends EditDistance {

    public int calculate(String word1, String word2) {
        return calculate(word1, word2, word1.length(), word2.length());
    }

    private int calculate(String word1, String word2, int len1, int len2) {
        if (len1 == 0) return len2;
        if (len2 == 0) return len1;
        if (word1.charAt(len1 - 1) == word2.charAt(len2 - 1))
            return calculate(word1, word2, len1 - 1, len2 - 1);

        return 1 + min(
                calculate(word1, word2, len1 - 1, len2),
                calculate(word1, word2, len1, len2 - 1),
                calculate(word1, word2, len1 - 1, len2 - 1)
        );
    }

}
