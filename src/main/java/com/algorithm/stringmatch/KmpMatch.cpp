#include <cstring>
int next[10];

/*------------------------------------------------------------------
KMP算法
根据next匹配跳转
-----------------------------------------------------------------------*/
void GetNext(char* p,int next[])
{
    int plen = strlen(p);
    next[0]=-1;
    int k=-1,j=0;
    while(j<plen-1)
    {
        //p[k]表示前缀，p[j]表示后缀
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
//next数组优化
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
            else//因为不能出现p[j] = p[ next[j ]]，所以当出现时需要继续递归，k = next[k] = next[next[k]]
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
        //j = -1或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++
        if(j==-1 || s[i]==p[j])
        {
            i++;
            j++;
        }
        else
        {
            //如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]
            //next[j]即为j所对应的next值
            j=next[j];
        }
    }
    if( j==plen )
        return i-j;
    else
        return -1;
}
