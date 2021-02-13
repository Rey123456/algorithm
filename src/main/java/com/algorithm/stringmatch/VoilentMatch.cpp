#include<cstring>
/*-----------------------------------------------------
暴力匹配算法

假设现在文本串 S 匹配到 i 位置，模式串 P 匹配到 j 位置，则有：
1、如果当前字符匹配成功（即 S[i] == P[j]），
    则 i++，j++，继续匹配下一个字符；
2、如果失配（即 S[i]! = P[j]），令 i = i - (j - 1)，j = 0。
    相当于每次匹配失败时，i 回溯，j 被置为0。
--------------------------------------------------------*/
int VoilentMatch(char* s,char* p)
{
    int slen=strlen(s);
    int plen = strlen(p);
    int i=0,j=0;
    while(i<slen && j<plen)
    {
        if(s[i] == p[j])
        {
            i++;
            j++;
        }
        else
        {
            i=i-j+1;
            j=0;
        }
    }
    if(j==plen)
        return i-j;
    else
        return -1;
}

/*int main()
{
    char* s="bbc abcdab abcdabcdabde";
    char* p="abcdabd";
    VoilentMatch(s,p);
    return 0;
}*/
