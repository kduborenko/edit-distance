package net.duborenko;

import java.util.Arrays;

/**
 * @author Kiryl Dubarenka
 */
@Order(1)
public class RecursiveMemoizationEditDistance extends EditDistance {

    public int calculate(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        for (int[] row : memo) Arrays.fill(row, -1);

        return calculate(word1, word2, word1.length(), word2.length(), memo);
    }

    private int calculate(String word1, String word2, int len1, int len2, int[][] memo) {
        if (memo[len1][len2] != -1) return memo[len1][len2];
        if (len1 == 0) return memo[len1][len2] = len2;
        if (len2 == 0) return memo[len1][len2] = len1;

        if (word1.charAt(len1 - 1) == word2.charAt(len2 - 1))
            return memo[len1][len2] = calculate(word1, word2, len1 - 1, len2 - 1, memo);

        return memo[len1][len2] = 1 + min(
                calculate(word1, word2, len1 - 1, len2, memo),
                calculate(word1, word2, len1, len2 - 1, memo),
                calculate(word1, word2, len1 - 1, len2 - 1, memo)
        );
    }

}
