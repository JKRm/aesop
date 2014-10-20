
#include "jni.h"
#include "Prompt.h"
#include <Windows.h>
#include <iostream>
#define  USE_SPEECH_DLL
#include "LaneSpeech.h"
#ifdef _DEBUG
#pragma  comment (lib,"Speech_De.lib")
#else
#pragma  comment (lib,"Speech_Re.lib")
#endif
/*
   ��char*��jstring��ת��
*/


jstring WindowsTojstring( JNIEnv *env, const char* str )
{
  jstring rtn = 0;
  int slen = strlen(str);
  unsigned short* buffer = 0;
  if( slen == 0 )
    rtn = env->NewStringUTF(str);
  else
  {
    int length = MultiByteToWideChar(CP_ACP, 0, (LPCSTR)str, slen, NULL, 0);
    buffer = (unsigned short*)malloc(length*2 + 1);
    if( MultiByteToWideChar(CP_ACP, 0, (LPCSTR)str, slen, (LPWSTR)buffer, length) >0)
      rtn = env->NewString((jchar*)buffer, length);
  }
  if(buffer)
  free(buffer);
  return rtn;
}

JNIEXPORT jstring JNICALL Java_Prompt_getLine(JNIEnv *env, jobject obj, jstring str1)
{
    char* tmpstr = "����δʶ��";// �˾��Ϊ�����ַ����ķ���
    CSR sr;
    sr.Create();
    sr.LoadCrammarFile( L"grammar.xml" );
    sr.StartReco();
    sr.ProcessReco(6000);
    if ( sr.IsRecoEnd() && sr.IsRecoSuccess() ) {
	sr.GetRecoWordA(tmpstr);
    }
    jstring rtstr = WindowsTojstring(env, tmpstr);  // ת��
	str1=rtstr;
    return rtstr;     // �����ַ���

}



 void main(){}
