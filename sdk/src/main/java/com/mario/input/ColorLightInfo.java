package com.mario.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.jna.Structure;
import lombok.Data;

/**
 * @author zxz
 * @date 2023年03月06日 17:31
 */
@Data
@Structure.FieldOrder({"m_index","m_flags","m_cardType","m_bHseries","majorVersion","minorVersion"})
public class ColorLightInfo extends Structure {


    @JsonIgnoreProperties(value = {"pointer"}) // TODO 注释2
    public static class ByReference extends ColorLightInfo implements Structure.ByReference {
    }

    @JsonIgnoreProperties(value = {"pointer"})
    public static class ByValue extends ColorLightInfo implements Structure.ByValue {
    }


    int    m_index;
    int  m_flags;                //标志位
    int    m_cardType;             //发送卡类型
    byte   m_bHseries;             //是否是H 系列发送卡
    int    majorVersion;           //fpga主版本号
    int    minorVersion;           //fpga副版本号
//    int    armMajorVersion;        //arm主版本号
//    int    armMinorVersion;        //arm次版本号
//    int    m_portCount;            //网口数
//    //SPortArea  m_portArea[6];      //网口1-6
//    byte   bHDCP;                  //是否启用hdcp 服务 1启用 其他不启用
//    int    m_syncStyle;            //同步方式，参见SYNC_STYLE
//    int    m_realTimeBright;       //实时亮度
//    int    m_savedBright;          //保存的亮度
//    byte   m_brightColorType;      //亮度色温调节执行设置（由发送卡还是接收卡调节）40（低亮高灰）
//    int    m_split;                //包间隙
//    int    m_bigPack;              //是否是大包
//   // SUColorTempInfo clrTempInfo;   //色温信息
//    int    m_framRate;             //帧率
//    int  m_vsync_num;
//    int  m_hsync_num;
//    int  m_de_num;
//    short   m_res_width;            //发送卡输入信号分辨率宽度
//    short   m_res_height;           //发送卡输入信号分辨率高度
//    byte[]   m_uid=new byte[12];              //发送卡ID
//    short   m_activeWidth;          //有效宽度
//    short   m_activeHeight;         //有效高度
//    byte   m_syncMethod;           //接收卡同步方式  Bit0:0不同步 1同步  Bit1:0软同步 1硬同步     byte: 0无  1软同步 1硬同步
//    //SDateTime  m_curTime;
//    //SDateTime  m_endTime;
//    byte   m_realParamFlags;       //发送卡实时参数标志
//    int    m_testingMode;          //测试模式
//    int    m_curVInput;            //当前的视频输入,参见VIDEO_INPUT_TYPE
//    int    m_initInputType;        //参见VIDEO_INPUT_TYPE
//    boolean   m_bNoVInputNoOut;       //没有视频输入时，不输出视频音频
//    boolean   m_bAutoSwitch;          //是否自动切换DVI/HDMI
//    int    m_videoBits;            //8, 10 ,12
//    byte   m_signalSource;         //信号源 HDMI: 00000110 DVI:  00000101
//    int  m_startAddres;          //升级的起始地址
//    int  m_flashLen;             //升级时的flash地址长度
//    short   m_fielLen;              //升级时文件长度
//    boolean   m_crcFlags;             //升级时的CRC标志位
//    byte   m_brightFromFunCard;    //多功能卡返回的亮度
//    byte   m_portHasRcvCard;       //检测当前发送卡各网口是否有连接接收卡 bit0:  1 网口A有连接收卡  0 网口A未连接收卡  bit1:...
//    byte   m_bSuppoort12M;         //是否支持12M波特率
//    byte   m_currectRate;          //目前连接的串口波特率bit[1:0] 0:115200 1:12M
//    byte   m_audioFlag;            //音频设置标志 0， 表示启用音频设置；1，表示不启用音频设置
//    byte   m_audioInfo;            //Bit0 表示网口0，bitn表示网口n ‘0’表示 开启音频；‘1’表示关闭音频
//    byte[]   m_specialfunList= new byte[8];    //发送卡特殊功能列表
//    byte   m_opticFibereFlags;     //是否是光纤 Bit0： 1：开启网络 0：关闭网络 Bit1：1：开启光纤 0：关闭光纤
//    byte   m_workModeFlags;        //发送卡实时参数标志
//    byte   m_armCRCFlags;          //升级arm时的CRC标志位（Bit0: 1：发送卡crc校验通过 0：未通过  Bit1：1：前面板crc校验通过 0：未通过）
//    byte   m_timingBright;         //定时亮度是否开启
//    byte   m_sendRcvLayOut;        //发送卡发送接收卡连接关系使能
//    byte   m_upgradePackVersionInteger; //升级包版本号整数部分
//    byte   m_upgradePackVersionDecimal; //升级包版本号小数部分
//    byte[]   m_armSVNVersion= new byte[2];     //发送卡arm的源代码svn版本号（高位在前，低位在后）
//    byte   m_hardwareSetNumber;    //发送卡主板硬件集编号（兼容的pcb为同一个集）
//    byte   m_lcdVersionInteger;    //发送卡前面版版本号整数部分
//    byte   m_lcdVersionDecimal;    //发送卡前面版版本号小数部分
//    byte[]  m_lcdARMSVNVersion = new byte[2];  //发送卡前面版arm的源代码svn版本号（高位在前，低位在后）
//    byte   m_lcdPCBVersion;        //前面板硬件版本（0：表示无前面板）
//    byte   m_enableNetwork;        //启用网络（1：开启 0：关闭）
//    byte   m_autoObtainIPAddress;  //自动获取IP地址（1：自动获取 0：手动获取）
//    byte[]   m_localPort= new byte[2];         //本地端口号（帧数据）（8088，高位在前)
//    byte[]   m_localIPAddress= new byte[4];    //本地IP地址（帧数据）（192.168.1.100，网络号在前)
//    byte[]   m_subnetMask= new byte[4];        //子网掩码（255.255.255.0)
//    byte[]   m_gateway= new byte[4];           //网关（192.168.1.1)
//    byte   m_dataLen;              //探测发送卡返回有效数据长度
//    byte   m_syncSource;           //同步源
//    byte   m_videoSource;          //视频源
//    byte   m_portBackup;           //主从网口标志
//    //SSenderInfoDMX m_dmxInfo;      //dmx信息
//    byte[]   m_senderName= new byte[65];       //发送卡名称
//    byte[]   m_senderID= new byte[32];         //发送卡id
//    byte[]   m_startAddress2= new byte[3];     //升级第二块FPGA起始地址
//    byte[]   m_fileSize2= new byte[3];         //升级第二块FPGA文件大小
//    byte[]   m_fileLength2= new byte[2];       //升级第二块FPGA文件冗余长度
//    byte   m_bSupportCameraCorrect;  //是否支持发送卡相机校正
//    byte   m_bEnableScreenAdj;     //开启/关闭画面调整 Bit0: 0: 关闭 1:开启
//    float  m_screenHue;            //色调 [-30,30]
//    float  m_screenSaturation;     //饱和度 [0,2.0]
//    int    m_screenBrightness;     //亮度补偿 [-30,30]
//    float  m_screenContrast;       //对比度 [0,2.0]
//    boolean   m_bSenderbEncrypt;      //发送卡是否加密
//    byte[]   m_senderPassWord= new byte[15];   //发送卡加密密码
//    int  m_customerID;           //客户ID
}
