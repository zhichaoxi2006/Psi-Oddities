package com.zhichaoxi.psi_oddities.util;

public class NumberFormatter {

    /**
     * 将数字转换为简洁格式（如：1.5k, 2.3M, 1.2B）
     * @param number 要格式化的数字
     * @return 格式化后的字符串
     */
    public static String formatNumber(long number) {
        if (number < 1000) {
            return String.valueOf(number);
        }

        String[] units = {"", "k", "M", "B", "T"};
        int unitIndex = 0;
        double num = number;

        while (num >= 1000.0 && unitIndex < units.length - 1) {
            num /= 1000.0;
            unitIndex++;
        }

        // 格式化数字，移除不必要的小数部分
        if (num == (long) num) {
            return String.format("%d%s", (long) num, units[unitIndex]);
        } else {
            return String.format("%.1f%s", num, units[unitIndex]);
        }
    }

    /**
     * 重载方法，支持double类型
     */
    public static String formatNumber(double number) {
        if (Math.abs(number) < 1000) {
            return String.format("%.0f", number);
        }

        String[] units = {"", "k", "M", "B", "T"};
        int unitIndex = 0;
        double num = number;

        while (Math.abs(num) >= 1000.0 && unitIndex < units.length - 1) {
            num /= 1000.0;
            unitIndex++;
        }

        // 格式化数字，根据数值大小决定小数位数
        if (Math.abs(num) < 10) {
            return String.format("%.2f%s", num, units[unitIndex]);
        } else if (Math.abs(num) < 100) {
            return String.format("%.1f%s", num, units[unitIndex]);
        } else {
            return String.format("%.0f%s", num, units[unitIndex]);
        }
    }
}