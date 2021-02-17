package com.algorithm.leetcode;

/**
 * @ClassName Code_523
 * @Description Reconstruct Original Digits from English 从英文中重建数字
 * @Author rey
 * @Date 2021/2/15 下午7:33
 */
public class Code_523 {
    public static void main(String[] args){
        System.out.print(new Code_523().originalDigits3("zeroonetwothreefourfivesixseveneightnine"));
    }

    public String originalDigits(String s) {
        int[] letters = new int[26];
        for(char tmp : s.toCharArray()){
            letters[tmp-'a']++;
        }
        String[] numStr = "zero,two,six,seven,eight,four,three,one,five,nine".split(",");
        int[] nums = new int[]{0,2,6,7,8,4,3,1,5,9};
        int[] resnum = new int[10];
        String res = "";
        for(int i=0;i<numStr.length;i++){
            char[] charTmp = numStr[i].toCharArray();
            int size = letters[charTmp[0]-'a'];
            for(int j=1;j<charTmp.length;j++){
                size = Math.min(size, letters[charTmp[j]-'a']);
            }
            for(char chatAt : charTmp){
                letters[chatAt-'a'] -= size;
            }
            while (size-- > 0) resnum[nums[i]]++;
        }
        for(int i=0;i<resnum.length;i++){
            while (resnum[i]-->0){
                res += i;
            }
        }

        return res;
    }

    public String originalDigits1(String s) {
        int[] count = new int[10];
        for(char tmp : s.toCharArray()){
            switch (tmp){
                case 'z'://zero
                    count[0]++;
                    break;
                case 'w'://two
                    count[2]++;
                    break;
                case 'x'://six
                    count[6]++;
                    break;
                case 's'://seven 7-6
                    count[7]++;
                    break;
                case 'g'://eight
                    count[8]++;
                    break;
                case 'u'://four
                    count[4]++;
                    break;
                case 't'://three 3-2-8
                    count[3]++;
                    break;
                case 'o'://one 1-0-2-4
                    count[1]++;
                    break;
                case 'f'://five 5-4
                    count[5]++;
                    break;
                case 'n'://nine 9-7-1
                    count[9]++;
                    break;
            }
        }
        count[7] = count[7] - count[6];
        count[3] = count[3] - count[2] - count[8];
        count[1] = count[1] - count[0] - count[2] - count[4];
        count[5] = count[5] - count[4];
        count[9] = (count[9] - count[7] - count[1])/2;

        String res = "";
        for(int i=0;i<count.length;i++){
            while (count[i]-->0){
                res += i;
            }
        }
        return res;
    }

    public String originalDigits2(String s) {
        int[] letters = new int[26];
        for(char tmp : s.toCharArray()){
            letters[tmp-'a']++;
        }
        int[] count = new int[10];
        count[0] = letters['z' - 'a'];
        count[2] = letters['w' - 'a'];
        count[6] = letters['x' - 'a'];
        count[7] = letters['s' - 'a'] - count[6];
        count[8] = letters['g' - 'a'];
        count[4] = letters['u' - 'a'];
        count[3] = letters['t' - 'a'] - count[2] - count[8];
        count[1] = letters['o' - 'a'] - count[0] - count[2] - count[4];
        count[5] = letters['f' - 'a'] - count[4];
        count[9] = (letters['n' - 'a'] - count[7] - count[1])/2;

        StringBuilder res = new StringBuilder();
        for(int i=0;i<count.length;i++){
            while (count[i]-->0){
                res.append(i);
            }
        }
        return res.toString();
    }

    public String originalDigits3(String s) {
        int[] count = new int[10];
        int len = s.length();
        s = s.replaceAll("z", "");
        count[0] = len - s.length();
        len = s.length();
        s = s.replaceAll("w", "");
        count[2] = len - s.length();
        len = s.length();
        s = s.replaceAll("x", "");
        count[6] = len - s.length();
        len = s.length();
        s = s.replaceAll("s", "");
        count[7] = len - s.length() - count[6];
        len = s.length();
        s = s.replaceAll("g", "");
        count[8] = len - s.length();
        len = s.length();
        s = s.replaceAll("u", "");
        count[4] = len - s.length();
        len = s.length();
        s = s.replaceAll("t", "");
        count[3] = len - s.length() - count[2] - count[8];
        len = s.length();
        s = s.replaceAll("o", "");
        count[1] = len - s.length() - count[0] - count[2] - count[4];
        len = s.length();
        s = s.replaceAll("f", "");
        count[5] = len - s.length() - count[4];
        len = s.length();
        s = s.replaceAll("n", "");
        count[9] = (len - s.length() - count[7] - count[1])/2;

        StringBuilder res = new StringBuilder();
        for(int i=0;i<count.length;i++){
            while (count[i]-->0){
                res.append(i);
            }
        }
        return res.toString();
    }
}