#include<cstring>
#define MAX_CHAR 256
int* setStep(char* p)
{
    int i;
    int* step=new int[MAX_CHAR];
    int plen = strlen(p);
    for(i=0;i<MAX_CHAR;i++)
        step[i]=plen+1;
    //从左向右扫描，保存每个字符所需移动步长
    for(i=0;i<plen;i++)
    {
        step[(unsigned)p[i]]=plen-i;
    }
    return step;
}

int SundayMatch(char* s,char* p)
{
    int slen = strlen(s);
    int plen = strlen(p);
    int i,j,temp;
    int* step = setStep(p);
    for(i=0;i<slen;)
    {
        temp = i;
        for(j=0;j<plen;)
        {
            if(s[i]==p[j])
            {
                i++;
                j++;
                continue;
            }
            else
            {
                if(temp+plen>slen)
                    return -1;
                char firstRightchar = s[temp+plen];
                i = temp + step[(unsigned)firstRightchar];
                break;
            }
        }
        if(j==plen)
            return i-plen;
    }
    return -1;
}
