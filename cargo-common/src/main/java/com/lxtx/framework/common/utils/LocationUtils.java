package com.lxtx.framework.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.awt.geom.Point2D;
import java.math.BigDecimal;

/**
 * 距离计算工具类
 *
 * @since 2019-04-24
 */
public class LocationUtils {
    private static final double EARTH_RADIUS = 6371393; // 地球平均半径,单位：m

    /**
     * 通过AB点经纬度获取距离
     *
     * @param pointA A点(经，纬)
     * @param pointB B点(经，纬)
     * @return 距离(单位 ： 米)
     */
    public static double getDistance(Point2D pointA, Point2D pointB) {
        // 经纬度（角度）转弧度。弧度用作参数，以调用Math.cos和Math.sin
        double radiansAX = Math.toRadians(pointA.getX()); // A经弧度
        double radiansAY = Math.toRadians(pointA.getY()); // A纬弧度
        double radiansBX = Math.toRadians(pointB.getX()); // B经弧度
        double radiansBY = Math.toRadians(pointB.getY()); // B纬弧度

        // 公式中“cosβ1cosβ2cos（α1-α2）+sinβ1sinβ2”的部分，得到∠AOB的cos值
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
                + Math.sin(radiansAY) * Math.sin(radiansBY);
        double acos = Math.acos(cos); // 反余弦值
        return EARTH_RADIUS * acos; // 最终结果
    }

    /**
     * 通过AB点经纬度获取距离, 并格式化
     * @param longitudeA 经度A
     * @param latitudeA 纬度A
     * @param longitudeB 经度B
     * @param latitudeB 纬度B
     * @return
     */
    public static String getDistanceFormat(double longitudeA, double latitudeA, double longitudeB, double latitudeB) {
        Point2D pointA = new Point2D.Double(longitudeA, latitudeA);
        Point2D pointB = new Point2D.Double(longitudeB, latitudeB);
        double distance = NumberUtils.keepScale(getDistance(pointA, pointB), 2);
        String formatDis = null;

        if (distance <= 1000) {
            formatDis = distance + "m";
        } else if (distance > 1000) {
            formatDis = NumberUtils.keepScale(distance/1000, 2) + "km";
        }

        return formatDis;
    }

    /**
     * 格式化距离 大于 1000 米使用km
     *
     * @param distance
     * @return
     */
    public static String distanceFormat(String distance) {
        if (StringUtils.isBlank(distance)) {
            return null;
        }
        BigDecimal decimal = new BigDecimal(distance);
        int intValue = decimal.intValue();
        String formatDis = null;
        if (intValue < 1000) {
            formatDis = intValue + "m";
        } else if (intValue >= 1000) {
            formatDis = NumberUtils.keepScale(intValue / 1000, 2) + "km";
        }
        return formatDis;
    }


    public static void main(String[] args) {
        Point2D pointA = new Point2D.Double(113.273979,23.135941);// 珠江国际
        Point2D pointB = new Point2D.Double(113.273499,23.134961);// 越秀区政府
        System.out.println(getDistance(pointA, pointB));// 119.521m

        System.out.println(getDistanceFormat(113.273979, 23.135941, 113.273499, 23.134961));
        System.out.println(getDistanceFormat(111.273979, 23.135941, 113.273499, 23.134961));
    }

}
