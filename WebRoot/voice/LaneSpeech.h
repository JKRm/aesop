
#define	USE_SPEECH_DLL	//使用动态连接库版的Speech

#include "LaneSpeechDefine.h"


#ifndef	LANE_SPEECH_H
#define LANE_SPEECH_H

class LANE_SPEECH_DLL CSR  
{
public:
	CSR();
	virtual ~CSR();
	bool Create( const DWORD dwLanguage = SP_CHINESE,
				 const bool bInproc = true );
	bool LoadCrammarFile ( const WCHAR * pwchFilename );
	bool SetInputFromWav ( const WCHAR * pwchFileName, const SPSTREAMFORMAT FileFormat = SPSF_8kHz8BitMono );
	bool StartReco();
	void ProcessReco( const DWORD dwMilliseconds = 5000 );		//处理识别事件
	bool IsRecoSuccess() const;	//识别成功则返回true
	bool IsRecoEnd() const;		//识别结束则返回true
	void GetRecoWordA ( char * pchRecoWord );
	void GetRecoWordW ( wchar_t * pwchRecoWord );
	

protected:
	void OnRecoSuccess ( ISpPhrase *ppPhrase );
	static void UpdateGrammerID() { ullGrammerID++; };	//更新ullGrammerID

protected:
	CComPtr<ISpRecognizer>		m_pSREngine;	// 语音识别引擎(recognition)的接口。
	CComPtr<ISpRecoContext>		m_pSRContext;	// 识别引擎上下文(context)的接口。
	CComPtr<ISpRecoGrammar>		m_pSRGrammar;	// 识别文法(grammar)的接口。
	CComPtr<ISpStream>			m_pInputStream;	// 流()的接口。
	CComPtr<ISpObjectToken>		m_pToken;		// 语音特征的(token)接口。
	CComPtr<ISpAudio>			m_pAudio;		// 音频(Audio)的接口。(用来保存原来默认的输入流)
	static ULONGLONG			ullGrammerID;	// Grammer的标识符, 64位无符号整型 每建立一个Grammar,加一。
	bool						m_bRecoSuccess;
	bool						m_bRecoEnd;
	WCHAR						m_wchRecoWord[128]; //用于保存成功识别的短语
};

class LANE_SPEECH_DLL CTTS  
{
public:
	CTTS();
	virtual ~CTTS();
	bool Create( const DWORD dwLanguage = SP_CHINESE );
	bool SetOutputToWav ( const WCHAR *pwchFileName, const SPSTREAMFORMAT FileFormat  = SPSF_8kHz8BitMono  );
	bool UnSetOutputWithWav ();

	bool SetLanguage ( const DWORD dwLanguage );
	void SetVolume ( const USHORT usVolume ) const;
	USHORT GetVolume ();
	void SetRate ( const LONG  RateAdjust )const;
	LONG GetRate ();

	void Speak ( const WCHAR *pwcs, const DWORD dwFlags = SPF_ASYNC ) const;
	void Stop () const;
	void Pause () const;
	void Resume () const;

	
protected:
	CComPtr<ISpVoice>			m_pVoice;			// 声音对象的指针。
	CComPtr<ISpObjectToken>		m_pToken;			// token对象的指针。
	CComPtr<ISpAudio>			m_pAudio;			// 音频对象的指针。(用来保存原来默认的输入流)
	CComPtr<ISpStream>			m_pOutputStream;	// 输出到文件的流对象。
};

#endif // LANE_SPEECH_H
