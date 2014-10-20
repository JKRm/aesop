
#ifndef	SAPIDEFINE_H
#define SAPIDEFINE_H

#ifdef USE_SPEECH_DLL	//������USE_SPEECH_DLL���Ͱ�����DLL����������������

	#ifdef LANE_SPEECH_EXPORTS
		#define LANE_SPEECH_DLL __declspec(dllexport)
	#else
		#define LANE_SPEECH_DLL __declspec(dllimport)
	#endif

	//������������ڻ�û���������ô�����ˣ�������DLL��com��atl�й�,��ʱֻ�����ε������ھ�̬����Ͳ������������档
	#pragma warning( disable : 4251 )

#else				//û����USE_SPEECH_DLL�����������������ࣨLANE_SPEECH_DLL��Ϊ�գ�
	#define LANE_SPEECH_DLL

#endif	//USE_SPEECH_DLL

#include <windows.h>
#define _ATL_APARTMENT_THREADED
#include <atlbase.h>
extern CComModule _Module;	//You may derive a class from CComModule and use it. if you want to override something,but do not change the name of _Module
#include <atlcom.h>
#include "sphelper.h"		//sapi��Ҫ��ͷ�ļ�

const DWORD		SP_CHINESE = 0x0000;	//��������.
const DWORD		SP_ENGLISH = 0x0001;	//Ӣ��.

inline void ShowMsg ( const LPCTSTR lpText = "ERROR", 
					   const LPCTSTR lpCaption = "ERROR" )
{ 
	::MessageBox( NULL, lpText, lpCaption, MB_OK ); 
}

inline BOOL CheckHr ( const HRESULT &hr, 
					 const LPCTSTR lpText = "ERROR", 
					 const LPCTSTR   lpCaption = "ERROR" )
{
	if ( FAILED(hr) ) {
		ShowMsg ( lpText, lpCaption ); 
		return false; 
	}
	
	return true;
}

#endif	//SAPIDEFINE_H