package net.duborenko;

/**
 * @author Kiryl Dubarenka
 */
@Order(2)
public class IterativeEditDistance extends EditDistance {

    public int calculate(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 1; i < memo.length; i++) memo[i][0] = i;
        for (int i = 1; i < memo[0].length; i++) memo[0][i] = i;

        for (int len1 = 1; len1 <= word1.length(); len1++) {
            for (int len2 = 1; len2 <= word2.length(); len2++) {
                memo[len1][len2] = word1.charAt(len1 - 1) == word2.charAt(len2 - 1)
                        ? memo[len1 - 1][len2 - 1]
                        : 1 + min(memo[len1 - 1][len2],
                                memo[len1][len2 - 1],
                                memo[len1 - 1][len2 - 1]);
            }
        }

        return memo[word1.length()][word2.length()];
    }

}
