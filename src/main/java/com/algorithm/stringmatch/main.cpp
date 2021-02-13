#include <iostream>
#include <cstring>
using namespace std;


int VoilentMatch(char* s,char* p);

int KmpMatch(char* s,char* p);

void BoyerMoore(char *pattern, int m, char *text, int n);

int SundayMatch(char* s,char* p);



int main()
{
    char* s="this example is an example";
    char* p = "example";
    cout<<VoilentMatch(s,p)<<endl;
    cout<<KmpMatch(s,p)<<endl;
    cout<<SundayMatch(s,p);
    BoyerMoore(p, strlen(p), s, strlen(s));
    return 0;
}
