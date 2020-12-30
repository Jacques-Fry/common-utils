package com.jacques.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.io.IOException;

/**
 * IP工具
 *
 * @info IP工具
 * @version 1.0.0
 * @author Jacques·Fry
 * @date 2020/12/30 10:37
 */
public final class IpUtil {

    /**
     * 百度配置
     */
    public static final String ak = "AGYEv67ipuHkItD10WnvXwgtjkLoyQ4z";
    public static final String coor = "bd09ll";


    /**
     * 计算距离
     *
     * @author: Jacques Fry
     * @date: 2020/12/30 10:36
     * @param longitudeFrom 经度
     * @param latitudeFrom 纬度
     * @param longitudeTo 目的经度
     * @param latitudeTo 目的纬度
     * @return double
     */
    public static double getDistance(double longitudeFrom, double latitudeFrom, double longitudeTo, double latitudeTo) {
        GlobalCoordinates source = new GlobalCoordinates(latitudeFrom, longitudeFrom);
        GlobalCoordinates target = new GlobalCoordinates(latitudeTo, longitudeTo);
        return new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, source, target).getEllipsoidalDistance();
    }

    /**
     * 解析ip地址
     *
     * @author: Jacques Fry
     * @date: 2020/12/30 10:42
     * @param ip IP
     * @return java.lang.String
     */
    public static String parseIpAddress(String ip) {
        try {
            // 创建Httpclient对象
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = String.format("https://api.map.baidu.com/location/ip?ak=%s&ip=%s&coor=%s", ak, ip, coor);
            HttpGet httpGet = new HttpGet(url);
            String content;
            CloseableHttpResponse response = null;
            try {
                // 执行请求
                response = httpclient.execute(httpGet);
                // 判断返回状态是否为200
                if (response.getStatusLine().getStatusCode() == 200) {
                    // 解析响应体
                    content = EntityUtils.toString(response.getEntity(), "UTF-8");
                } else {
                    return null;
                }
            } finally {
                if (response != null) {
                    response.close();
                }
                // 关闭浏览器
                httpclient.close();
            }
            return content;
        } catch (IOException ioException) {
            throw new RuntimeException("ip解析失败");
        }
    }
}
