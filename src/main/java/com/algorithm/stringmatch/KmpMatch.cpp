#include <cstring>
int next[10];

/*------------------------------------------------------------------
KMP�㷨
����nextƥ����ת
-----------------------------------------------------------------------*/
void GetNext(char* p,int next[])
{
    int plen = strlen(p);
    next[0]=-1;
    int k=-1,j=0;
    while(j<plen-1)
    {
        //p[k]��ʾǰ׺��p[j]��ʾ��׺
        if(k==-1 || p[j]==p[k])
        {
            ++k;
            ++j;
            next[j]=k;
        }
        else
        {
            k=next[k];
        }
    }
}
//next�����Ż�
void GetNextval(char* p,int next[])
{
    int plen = strlen(p);
    next[0]=-1;
    int k=-1,j=0;
    while(j<plen-1)
    {
        if(k==-1 || p[j]==p[k])
        {
            ++j;
            ++k;
            if(p[j]!=p[k])
                next[j]=k;
            else//��Ϊ���ܳ���p[j] = p[ next[j ]]�����Ե�����ʱ��Ҫ�����ݹ飬k = next[k] = next[next[k]]
                next[j]=next[k];
        }
        else
            k=next[k];
    }
}
int KmpMatch(char* s,char* p)
{
    int i=0,j=0;
    int slen = strlen(s);
    int plen = strlen(p);
     //GetNextval(p,next);
    GetNext(p,next);
    while(i<slen && j<plen)
    {
        //j = -1���ߵ�ǰ�ַ�ƥ��ɹ�����S[i] == P[j]��������i++��j++
        if(j==-1 || s[i]==p[j])
        {
            i++;
            j++;
        }
        else
        {
            //���j != -1���ҵ�ǰ�ַ�ƥ��ʧ�ܣ���S[i] != P[j]�������� i ���䣬j = next[j]
            //next[j]��Ϊj����Ӧ��nextֵ
            j=next[j];
        }
    }
    if( j==plen )
        return i-j;
    else
        return -1;
}
