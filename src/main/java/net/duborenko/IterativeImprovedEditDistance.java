package net.duborenko;

/**
 * @author Kiryl Dubarenka
 */
@Order(3)
public class IterativeImprovedEditDistance extends EditDistance {

    public int calculate(String word1, String word2) {
        int[] memo = new int[word2.length()+1];
        for (int i = 0; i < memo.length; i++) memo[i] = i;

        for (int len1 = 1; len1 <= word1.length(); len1++) {
            int d = memo[0];
            memo[0] = len1;
            for (int len2 = 1; len2 <= word2.length(); len2++) {
                int res = word1.charAt(len1 - 1) == word2.charAt(len2 - 1)
                        ? d
                        : 1 + min(d, memo[len2 - 1], memo[len2]);
                d = memo[len2];
                memo[len2] = res;
            }
        }

        return memo[memo.length - 1];
    }

}
