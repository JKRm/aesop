
#ifndef	SAPIDEFINE_H
#define SAPIDEFINE_H

#ifdef USE_SPEECH_DLL	//定义了USE_SPEECH_DLL，就按生成DLL，声明导出导入类

	#ifdef LANE_SPEECH_EXPORTS
		#define LANE_SPEECH_DLL __declspec(dllexport)
	#else
		#define LANE_SPEECH_DLL __declspec(dllimport)
	#endif

	//这个警告我现在还没闹清楚是怎么回事了，估计是DLL和com或atl有关,暂时只能屏蔽掉它，在静态库里就不会出现这个警告。
	#pragma warning( disable : 4251 )

#else				//没定义USE_SPEECH_DLL，则不声明导出或导入类（LANE_SPEECH_DLL就为空）
	#define LANE_SPEECH_DLL

#endif	//USE_SPEECH_DLL

#include <windows.h>
#define _ATL_APARTMENT_THREADED
#include <atlbase.h>
extern CComModule _Module;	//You may derive a class from CComModule and use it. if you want to override something,but do not change the name of _Module
#include <atlcom.h>
#include "sphelper.h"		//sapi需要的头文件

const DWORD		SP_CHINESE = 0x0000;	//简体中文.
const DWORD		SP_ENGLISH = 0x0001;	//英语.

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