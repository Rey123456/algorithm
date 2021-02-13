package com.algorithm.backtrack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
class pyramidTransition {

    /**
     *We are stacking blocks to form a pyramid. Each block has a color which is a one letter string, like `'Z'`.
     For every block of color `C` we place not in the bottom row, we are placing it on top of a left block of color `A` and right block of color `B`. We are allowed to place the block there only if `(A, B, C)` is an allowed triple.
     We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed. Each allowed triple is represented as a string of length 3.
     Return true if we can build the pyramid all the way to the top, otherwise false.

     Example 1:
     Input: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
     Output: true
     Explanation:
     We can stack the pyramid like this:
         A
        / \
       D   E
      / \ / \
     X   Y   Z

     This works because ('X', 'Y', 'D'), ('Y', 'Z', 'E'), and ('D', 'E', 'A') are allowed triples.
     Example 2:
     Input: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
     Output: false
     Explanation:
     We can't stack the pyramid to the top.
     Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.
     * */
    public static boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, List<String>> mapAllowed = new HashMap<>();
        for(String str : allowed){
            String key = str.substring(0,2);
            String value = str.substring(2);
            if(!mapAllowed.containsKey(key)){
                mapAllowed.put(key, new ArrayList<String>());
            }
            mapAllowed.get(key).add(value);
        }

        return helper(bottom, mapAllowed);
    }

    public static boolean helper(String bottom, Map<String, List<String>> mapAllowed){
        if(bottom.length() == 1 ){
            return true;
        }
        for(int i=0;i<bottom.length()-1;i++){
            String tmp = bottom.substring(i,i+2);
            if(!mapAllowed.containsKey(tmp)){
                return false;
            }
        }
        List<String> list = new ArrayList<>();
        getlist(bottom, 0, new StringBuilder(), list, mapAllowed);
        for(String ls : list){
            if(helper(ls, mapAllowed)) return true;
        }
        return false;
    }

    public static void getlist(String bottom, int len, StringBuilder ls, List<String> list, Map<String, List<String>> mapAllowed) {
        if(len == bottom.length()-1){
            list.add(ls.toString());
            return;
        }
        for(String s : mapAllowed.get(bottom.substring(len, len+2))){
            ls.append(s);
            getlist(bottom, len+1, ls, list, mapAllowed);
            ls.deleteCharAt(ls.length()-1);
        }
    }

    public static void main(String[] args) {
        String bottom = "XYZ";
        List<String> allowed = new ArrayList<String>(){{
            add("XYD");
            add("YZE");
            add("DEA");
            add("FFF");}};
        System.out.println(pyramidTransition(bottom,allowed));
    }
}
