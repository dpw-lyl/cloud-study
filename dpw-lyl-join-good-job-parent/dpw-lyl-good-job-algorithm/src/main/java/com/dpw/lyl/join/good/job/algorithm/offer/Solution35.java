package com.dpw.lyl.join.good.job.algorithm.offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: dengpw
 * @createTime: 2023年06月27日 09:16:22
 * @version: 1.0.0
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 * 提示：
 * 2 <= timePoints <= 2 * 104
 * timePoints[i] 格式为 "HH:MM"
 * @Description: 剑指 Offer II 035. 最小时间差（中等）
 */
public class Solution35 {


    public static void main(String[] args) {

        List<String> req = new ArrayList<>();
        req.add("04:00");
        req.add("00:00");
        req.add("22:00");
        System.out.println(new Solution35().findMinDifference(req));

    }


    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 24 * 60) {
            return 0;
        }
        timePoints.replaceAll(this::toSecond);
        timePoints.sort((o1, o2) -> {
            if (Integer.parseInt(o1) < Integer.parseInt(o2)){
                return -1;
            }
            else if (Integer.parseInt(o1) == Integer.parseInt(o2)) {
                return 0;
            }
            return 1;
        });
        int i1 = Integer.parseInt(timePoints.get(0)) + 1440;
        String s = String.valueOf(i1);
        timePoints.add(s);
        int min = 1440;
        for (int i = 1; i < timePoints.size(); ++i) {
            min = Math.min(min, Math.abs(Integer.parseInt(timePoints.get(i)) - Integer.parseInt(timePoints.get(i - 1))));
        }
        return min;
    }

    private String toSecond(String s) {
        String[] split = s.split(":");
        return String.valueOf(Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]));
    }


}
