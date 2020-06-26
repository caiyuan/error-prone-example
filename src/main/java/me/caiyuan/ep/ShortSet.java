package me.caiyuan.ep;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ryan
 */
public class ShortSet {

    public static void main(String[] args) {
        Set<Short> s = new HashSet<>();
        int max = 100;
        for (short i = 0; i < max; i++) {
            s.add(i);
            s.remove(i - 1);
        }
        System.out.println(s.size());
    }

}
