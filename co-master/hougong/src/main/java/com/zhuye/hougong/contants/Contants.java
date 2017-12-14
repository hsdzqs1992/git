package com.zhuye.hougong.contants;

/**
 * Created by zzzy on 2017/11/20.
 */

public class Contants {
   // public static final String BASE_URL = "http://192.168.1.29/gong";
    public static final String BASE_URL = "http://jingcai.zyeo.net";
    //登录注册接口
    //退出
    public static final String logout = BASE_URL+"/index.php/home/passportapi/logout";
    //忘记密码短信验证
    public static final String forget_code = BASE_URL+"/index.php/home/passportapi/forget_code";
    //忘记密码
    public static final String forget_pass = BASE_URL+"/index.php/home/passportapi/forget_pass";
    //手机号等陆
    public static final String LOGIN_URL = BASE_URL+"/index.php/home/passportapi/mobile_login";
    //手机号注册
    public static final String REGEIST_URL = BASE_URL+"/index.php/home/passportapi/mobile_register";
    //获取短信验证码
    public static final String GET_REGEIST_URL = BASE_URL+"/index.php/home/passportapi/code";

    //获取短信验证码
    public static final String yz_code = BASE_URL+"/index.php/home/passportapi/yz_code";

    //sf_login
    public static final String sf_login = BASE_URL+"/index.php/home/passportapi/sf_login";
    //个人中心接口
    //个人资料
    public static final String information = BASE_URL+"/index.php/home/user/information";

    //意见反馈
    public static final String feedback = BASE_URL+"/index.php/home/user/feedback";
    //用户信息编辑
    public static final String edit_information = BASE_URL+"/index.php/home/user/edit_information";

    //修改用户头像
    public static final String user_face = BASE_URL+"/index.php/home/user/face";

    //用户隐身
    public static final String stealth = BASE_URL+"/index.php/home/user/stealth";

    ///语音、视频接听关闭
    public static final String YUSHI_CLODED = BASE_URL+"/index.php/home/user/closed";

    ///语音、视频接听打开
    public static final String YUSHI_OPEN = BASE_URL+"/index.php/home/user/open";

    //语音，视频收费设置
    public static final String charge = BASE_URL+"/index.php/home/user/charge";

    //我的关注
    public static final String mylove = BASE_URL+"/index.php/home/content/mylove";


    //我的粉丝
    public static final String loveme = BASE_URL+"/index.php/home/content/lovemy";

    //我的黑名单
    public static final String blacklist = BASE_URL+"/index.php/home/content/blacklist";


    //谁看过我
    public static final String wholookme = BASE_URL+"/index.php/home/content/view";

    //qianbao
    public static final String money = BASE_URL+"/index.php/home/profit/money";


    //交易|支出记录
    public static final String zhichujiaoji = BASE_URL+"/index.php/home/profit/detailed";

    //用户相册
    public static final String photos = BASE_URL+"/index.php/home/user/img";

    //相册上传
    public static final String img_upload = BASE_URL+"/index.php/home/user/img_upload";

    //相册删除
    public static final String img_del = BASE_URL+"/index.php/home/user/img_del";

    //个人中心展示页
    public static final String personcenter = BASE_URL+"/index.php/home/user/center";

    //vip列表
    public static final String viplist = BASE_URL+"/index.php/home/user/vip";

    //推广
    public static final String inv_code = BASE_URL+"/index.php/home/user/inv_code";
    //充值聊币设置
    public static final String liaobi_list = BASE_URL+"/index.php/home/user/liaobi_list";

    //主页
    public static final String lunbo = BASE_URL+"/index.php/home/hall/lunbo";
    //会场
    public static final String hall = BASE_URL+"/index.php/home/hall/hall";

    //关注
    public static final String love = BASE_URL+"/index.php/home/content/love";

    //取消关注
    public static final String del_mylove = BASE_URL+"/index.php/home/content/del_mylove";


 //举报
 public static final String report = BASE_URL+"/index.php/home/host/report";
    //lahei
    public static final String black = BASE_URL+"/index.php/home/content/black";

    //取消拉黑
    public static final String del_black = BASE_URL+"/index.php/home/content/del_black";

    //发表评论
    public static final String addcontent= BASE_URL+"/index.php/home/dynamic/add";

    //我的动态列表
    public static final String dynamiclists= BASE_URL+"/index.php/home/dynamic/lists";

    //城市列表
    public static final String cityindex= BASE_URL+"/index.php/home/city/index";

    //评论列表
    public static final String comment_list= BASE_URL+"/index.php/home/dynamic/comment_list";


    //点评点赞
    public static final String comment_zan= BASE_URL+"/index.php/home/dynamic/comment_zan";
    //点评取消点赞
    public static final String comment_qxzan= BASE_URL+"/index.php/home/dynamic/comment_qxzan";
    //搜索
    public static final String select= BASE_URL+"/index.php/home/hall/select";

    //发现
    public static final String new_dynamic= BASE_URL+"/index.php/home/dynamic/new_dynamic";

    //历史搜索记录
    public static final String history_select= BASE_URL+"/index.php/home/hall/history_select";

    //交流页面
    public static final String communication= BASE_URL+"/index.php/home/hall/communication";

    //动态点赞
    public static final String dynamic_zan= BASE_URL+"/index.php/home/dynamic/dynamic_zan";

    //取消点赞
    public static final String dynamic_qxzan= BASE_URL+"/index.php/home/dynamic/dynamic_qxzan";

    //接听判断
    public static final String answer_set= BASE_URL+"/index.php/home/hall/answer_set";

    //视频|语音接听
    public static final String avlog= BASE_URL+"/index.php/home/hall/log";

    //礼物列表
    public static final String giftlist= BASE_URL+"/index.php/home/gift/gift";

    //赠送列表记录
    public static final String gift_log= BASE_URL+"/index.php/home/gift/gift_log";

    //回赠礼物
    public static final String rebate= BASE_URL+"/index.php/home/gift/rebate";
    //通话记录
    public static final String call_record= BASE_URL+"/index.php/home/hall/call_record";


    //排行榜
    public static final String billboard= BASE_URL+"/index.php/home/host/billboard";

    //聊币购买---微信
    public static final String purchase= BASE_URL+"/index.php/home/wxpay/purchase";

    //vip充值-微信
    public static final String wxpayvip= BASE_URL+"/index.php/home/wxpay/vip";
    //提现金额展示
    public static final String tx_money= BASE_URL+"/index.php/home/setup/tx_money";

   //提现申请
   public static final String cash= BASE_URL+"/index.php/home/setup/cash";

   //主播申请
   public static final String host_apply= BASE_URL+"/index.php/home/host/host_apply";

   //主播特色
   public static final String feature= BASE_URL+"/index.php/home/host/feature";


    //主播申请上传照片
    public static final String host_pic= BASE_URL+"/index.php/home/host/host_pic";
}
