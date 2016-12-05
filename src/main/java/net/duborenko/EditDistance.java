package net.duborenko;

/**
 * @author Kiryl Dubarenka
 */
public abstract class EditDistance {

    public abstract int calculate(String word1, String word2);

    protected int min(int v1, int v2, int v3) {
        return Math.min(v1, Math.min(v2, v3));
    }

}
