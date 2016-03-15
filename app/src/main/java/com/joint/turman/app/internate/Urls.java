package com.joint.turman.app.internate;

import java.io.Serializable;

/**
 * Created by dqf on 2016/3/8.
 */
public class Urls implements Serializable {
    private static final long serialVersionUID = -1453773382865080478L;

    public final static String HTTP = "http://";
    public final static String HTTPS = "https://";

    private final static String URL_SPLITTER = "/";
    private final static String URL_UNDERLINE = "_";

//    public final static String HOST = "cst.9joint-eco.com";
    public final static String HOST = "10.10.11.24:8089";

    private final static String PROJECTNAME = "ec-web";
    private final static String NAMESPACE_APP = "app";
    private final static String NAMESPACE_COM = "com";

    //http://cst.9joint-eco.com/
    public final static String URL_API_HOST = HTTP + HOST + URL_SPLITTER;
    //http://cst.9joint-eco.com/ec-web/app/
    public final static String URL_APP_API_HOST = HTTP + HOST + URL_SPLITTER + PROJECTNAME + URL_SPLITTER + NAMESPACE_APP + URL_SPLITTER;

    //http://cst.9joint-eco.com/ec-web/com/
    public final static String URL_COM_API_HOST = HTTP + HOST + URL_SPLITTER + PROJECTNAME + URL_SPLITTER + NAMESPACE_COM + URL_SPLITTER;
    //http://cst.9joint-eco.com/ec-web/app/%s
    public final static String DEV_API_URL = URL_APP_API_HOST + "%s";

    //用户
    public final static String LOGIN_VALIDATE_HTTP = "login.action";//登陆
    public final static String LOGIN_AUTHC_HTTP = "account!authc.action";//自动登陆
    public final static String USER_REGISTER = "account!register.action";//注册

    public final static String USER_ACTION = "users!getUserAction.action";//用户权限

    //用户信息
    public final static String MY_INFO = "users!getUserInfo.action";
    //用户列表
    public final static String USER_LIST = "users!getList.action";
    //用户拼音分组列表
    public final static String USER_PY_GROUP_LIST = "users!getPYGroupList.action";
    //用户下部门
    public final static String USER_DEPARTMENT_LIST = "users!getUserDepartmentList.action";
    //部门下用户
    public final static String DEPARTMENT_USER_LIST = "users!getDepartmentUserList.action";

    //修改用户信息
    public final static String MY_INFO_HEADER_UPDATE = "users!updateUserHeader.action";
    public final static String MY_INFO_NAME_UPDATE = "users!updateUserName.action";
    public final static String MY_INFO_SEX_UPDATE = "users!updateUserSex.action";
    //绑定手机
    public final static String MY_INFO_MOBILE_BINDING = "users!bindingUserMobile.action";
    //账户转移
    public final static String MY_INFO_ACCOUNT_IN_CHECK = "users!accountInCompanyNo.action";
    public final static String MY_INFO_ACCOUNT_IN = "users!accountIn.action";
    //删除账户
    public final static String MY_INFO_ACCOUNT_DELETE = "users!accountDelete.action";
    //修改密码
    public final static String MY_INFO_PASSWORD_UPDATE = "users!updatePassword.action";

    //系统字典
    public final static String DICT_LIST = "dict!getDictList.action";

    //附件
    public final static String FILE_PUBLIC = URL_COM_API_HOST + "file.action";

    //消息
    public final static String PUSHLOG_LIST = "push-log!getList.action";

    //评论
    public final static String COMMENT_CREATE = "comment!create.action";
    public final static String COMMENT_APPROVE = "comment!approve.action";
    public final static String COMMENT_LIST = "comment!getList.action";
    public final static String COMMENT_LIST_ABOUTME = "comment!getAboutMeList.action";
    public final static String COMMENT_LIST_TARGET = "comment!getAboutTargetList.action";
    public final static String COMMENT_METHOD = "comment!method.action";

    //日历
    public final static String CALENDAR_DATE_LIST = "users!getCalendarDateList.action";
    public final static String CALENDAR_LIST = "users!getCalendarList.action";

    //企业公告
    public final static String ANNOUNCEMENT_DETAIL = "announcement!getDetail.action";

    //签到
    public final static String SIGNIN_CREATE = "signin!create.action";

    //客户
    public final static String CLIENT_DETAIL = "client!getDetail.action";
    public final static String CLIENT_LIST = "client!getList.action";

    //项目反馈
    public final static String PROBACK_DETAIL = "proback!getDetail.action";
    public final static String PROBACK_LIST = "proback!getList.action";
    public final static String PROBACK_LIST_ABOUTME = "proback!getListAboutMe.action";
    public final static String PROBACK_LIST_PROINFO = "proback!getListAboutProinfo.action";
    public final static String PROBACK_PHOTO_UPLOAD = "proback!uploadPhoto.action";
    public final static String PROBACK_CREATE = "proback!create.action";
    public final static String PROBACK_INIT = "proback!initDetail.action";

    //项目请假
    public final static String PROLEAVE_DETAIL = "proleave!getDetail.action";
    public final static String PROLEAVE_CREATE = "proleave!create.action";
    public final static String PROLEAVE_INIT = "proleave!initDetail.action";

    //项目签到
    public final static String PROSIGNIN_DETAIL = "prosignin!getDetail.action";

    //联系人
    public final static String LINKMAN_DETAIL = "linkman!getDetail.action";
    public final static String LINKMAN_LIST = "linkman!getList.action";

    //项目信息
    public final static String PROINFO_DETAIL = "proinfo!getDetail.action";
    public final static String PROINFO_LIST = "proinfo!getList.action";
    public final static String PROINFO_LIST_RELATED = "proinfo!getListRelated.action";
    public final static String PROINFO_ASSIGN = "proinfo!assign.action";

    //企业公告
    public final static String ANNOUNCE_DETAIL = "announce!getDetail.action";
    public final static String ANNOUNCE_LIST = "announce!getList.action";
    public final static String ANNOUNCE_LIST_RELATED = "announce!getListRelated.action";

    //标记文档为已读
    public final static String MARK_DOC_READED = "readinfo!create.action";

    //知识库
    public final static String KNOWLEDGE_DETAIL = "knowledge!getDetail.action";
    public final static String KNOWLEDGE_LIST = "knowledge!getList.action";

    //知识库的图文说明
    public final static String KNOWLEDGE_WEBVIEW = URL_APP_API_HOST + "knowledge!webview.action?keyId=";
    //知识库的在线预览 knowledge!fileview.action
    public final static String KNOWLEDGE_FILEVIEW = "knowledge!fileview.action";

    public final static String FEEDBACK = "users!feedback.action";
    public final static String UPDATE_VERSION = URL_COM_API_HOST+"MobileAppVersion_android.xml";
    public final static String UPDATE_LOG = URL_COM_API_HOST+"update.htm";
    public final static String HELP = URL_APP_API_HOST+"help/help_index.html";

    //履历
    public final static String RESUME_LIST = "resume!getList.action";

}
