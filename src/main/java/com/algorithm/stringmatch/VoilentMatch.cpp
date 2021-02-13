#include<cstring>
/*-----------------------------------------------------
����ƥ���㷨

���������ı��� S ƥ�䵽 i λ�ã�ģʽ�� P ƥ�䵽 j λ�ã����У�
1�������ǰ�ַ�ƥ��ɹ����� S[i] == P[j]����
    �� i++��j++������ƥ����һ���ַ���
2�����ʧ�䣨�� S[i]! = P[j]������ i = i - (j - 1)��j = 0��
    �൱��ÿ��ƥ��ʧ��ʱ��i ���ݣ�j ����Ϊ0��
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
