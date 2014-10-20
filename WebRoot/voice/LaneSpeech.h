
#define	USE_SPEECH_DLL	//ʹ�ö�̬���ӿ���Speech

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
	void ProcessReco( const DWORD dwMilliseconds = 5000 );		//����ʶ���¼�
	bool IsRecoSuccess() const;	//ʶ��ɹ��򷵻�true
	bool IsRecoEnd() const;		//ʶ������򷵻�true
	void GetRecoWordA ( char * pchRecoWord );
	void GetRecoWordW ( wchar_t * pwchRecoWord );
	

protected:
	void OnRecoSuccess ( ISpPhrase *ppPhrase );
	static void UpdateGrammerID() { ullGrammerID++; };	//����ullGrammerID

protected:
	CComPtr<ISpRecognizer>		m_pSREngine;	// ����ʶ������(recognition)�Ľӿڡ�
	CComPtr<ISpRecoContext>		m_pSRContext;	// ʶ������������(context)�Ľӿڡ�
	CComPtr<ISpRecoGrammar>		m_pSRGrammar;	// ʶ���ķ�(grammar)�Ľӿڡ�
	CComPtr<ISpStream>			m_pInputStream;	// ��()�Ľӿڡ�
	CComPtr<ISpObjectToken>		m_pToken;		// ����������(token)�ӿڡ�
	CComPtr<ISpAudio>			m_pAudio;		// ��Ƶ(Audio)�Ľӿڡ�(��������ԭ��Ĭ�ϵ�������)
	static ULONGLONG			ullGrammerID;	// Grammer�ı�ʶ��, 64λ�޷������� ÿ����һ��Grammar,��һ��
	bool						m_bRecoSuccess;
	bool						m_bRecoEnd;
	WCHAR						m_wchRecoWord[128]; //���ڱ���ɹ�ʶ��Ķ���
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
	CComPtr<ISpVoice>			m_pVoice;			// ���������ָ�롣
	CComPtr<ISpObjectToken>		m_pToken;			// token�����ָ�롣
	CComPtr<ISpAudio>			m_pAudio;			// ��Ƶ�����ָ�롣(��������ԭ��Ĭ�ϵ�������)
	CComPtr<ISpStream>			m_pOutputStream;	// ������ļ���������
};

#endif // LANE_SPEECH_H
