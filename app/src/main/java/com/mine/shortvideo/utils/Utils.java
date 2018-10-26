package com.mine.shortvideo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 *     desc  : Utils初始化相关
 * </pre>
 */
public class Utils {

    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }
    /**
     *  服务器返回url，通过url去获取视频的第一帧
     *  Android 原生给我们提供了一个MediaMetadataRetriever类
     *  提供了获取url视频第一帧的方法,返回Bitmap对象
     *
     *  @param videoUrl
     *  @return
     */
    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime(1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }
    public static void sendHandleMsg(int what, Object obj , Handler handler) {
        Message msg = new Message();
        msg.what = what;
        msg.obj = obj;
        handler.sendMessage(msg);
    }
    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dp * scale + 0.5f);
    }
    // 将长整型转换成时间格式
    public static String LongToHms(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        return dateFormat.format(time);
    }

    // 将长整型转换成带两位小数点的string格式
    public static String LongToPoint(long size) {
        float i = Float.parseFloat(String.valueOf(size));
        DecimalFormat fnum = new DecimalFormat("##0.00");
        if (i / 1024 / 1024 > 500) {
            return fnum.format(i / 1024 / 1024 / 1024) + " G";
        } else {
            return fnum.format(i / 1024 / 1024) + " M";
        }
    }
    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
    }
    public static boolean isUserLogin(Context context){
        String userId = MySharedData.sharedata_ReadString(context,"userId");
        if (!TextUtils.isEmpty(userId))
            return true;
        return false;

    }
    //自动生成名字（中文）
    public static String getRandomJianHan(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBK"); // 转成中文
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret += str;
        }
        return ret;
    }

    //生成随机用户名，数字和字母组成,
    public static  String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     *
     * @param s
     * @return 获得链接
     */
    public static String getLink(final String s)
    {
        if(s.startsWith("http")){
            return s;
        }
        String regex;
        final List<String> list = new ArrayList<String>();
//        regex = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
        regex ="http://.*?mp4";
        final Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        final Matcher ma = pa.matcher(s);
        while (ma.find())
        {
            list.add(ma.group());
        }
        return list.get(0);
    }
    /**
     *
     * @param s
     * @return 获得title
     */
    public static String getTitle(final String s)
    {
        if(!s.startsWith("<a href=")){
            return s;
        }
        String regex;
        final List<String> list = new ArrayList<String>();
//        regex = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
        regex =">.*?</a>";
        final Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        final Matcher ma = pa.matcher(s);
        while (ma.find())
        {
            list.add(ma.group());
        }
        return list.get(0).replaceAll(">|</a>","");
    }
    /**
     *
     * @param s
     * @return 获得图片URL
     */
    public static String getImgUrl(final String s)
    {
        if(s.startsWith("/sites")){
            return s;
        }
        String regex;
        final List<String> list = new ArrayList<String>();
//        regex = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
        regex ="/sites.*?\"";
        final Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        final Matcher ma = pa.matcher(s);
        while (ma.find())
        {
            list.add(ma.group());
        }
        if(list.size() > 0){
            return list.get(0).replaceAll("\"","");
        }else {
            return "url is null";
        }
    }
    /**
     *
     * @param s
     * @return 获得用户lable
     */
    public static String getUserLable(final String s)
    {
        if(!s.startsWith("<a href=")){
            return s;
        }
        String regex;
        String result = "";
        final List<String> list = new ArrayList<String>();
//        regex = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
        regex =">.*?</a>";
        final Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        final Matcher ma = pa.matcher(s);
        while (ma.find())
        {
            list.add(ma.group());
            result = result + ma.group().replaceAll(">|</a>","")+"  ";
        }
        return result;
    }
    /**
     *
     * @param s
     * @return 获得预约时间
     */
    public static String getBookTime(String s){
        if(!s.contains("-")){
            return s;
        }
        int index = s.indexOf("-");
        String result = s.substring(index+1);
        return result;

    }
}