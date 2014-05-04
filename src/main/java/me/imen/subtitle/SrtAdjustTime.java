package me.imen.subtitle;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: mengmeng.cheng
 * Date: 5/3/14
 * Time: 11:08 PM
 * Email: chengmengmeng@gmail.com
 */
public class SrtAdjustTime implements AdjustTime {
    private static final SrtAdjustTime instance = new SrtAdjustTime();

    private static final String SUFFIX = "_adjusted";
    private static final String NEWLINE = "\n";
    private static final String DELIMITER = " --> ";

    // 时间轴格式：00:01:48,850 --> 00:01:51,110
    private static final String regex = "\\d{2}:\\d{2}:\\d{2},\\d{3}.{5}\\d{2}:\\d{2}:\\d{2},\\d{3}";

    private static final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss,SSS");

    public static SrtAdjustTime getInstance() {
        return instance;
    }

    /**
     * 处理字幕文件
     *
     * @param fileName 字幕文件绝对路径
     * @param delay 延时毫秒数
     * @throws Exception
     */
    @Override
    public void process(String fileName, int delay) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + SUFFIX));
        String temp;

        Pattern pattern = Pattern.compile(regex);

        while ((temp = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(temp);

            if (matcher.find()) {
                String match = matcher.group();
                String [] tokens = match.split(DELIMITER);
                String adjusted = this.adjustTime(tokens[0], delay) + DELIMITER + this.adjustTime(tokens[1], delay);
                temp = temp.replace(match, adjusted);
            }

            writer.write(temp + NEWLINE);
        }

        reader.close();
        writer.close();
    }

    /**
     * 对字幕时间进行延迟处理
     *
     * @param time 延迟前字幕时间
     * @param delay 延迟毫秒数
     * @return 延迟后字幕时间
     * @throws Exception
     */
    private String adjustTime(String time, int delay) throws Exception {
        Calendar cal = Calendar.getInstance();

        cal.setTime(df.parse(time));
        cal.add(Calendar.MILLISECOND, delay);

        return df.format(cal.getTime());
    }
}
