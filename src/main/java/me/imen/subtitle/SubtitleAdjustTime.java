package me.imen.subtitle;

import me.imen.util.FileUtil;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * User: mengmeng.cheng
 * Date: 5/4/14
 * Time: 2:10 PM
 * Email: chengmengmeng@gmail.com
 */
public class SubtitleAdjustTime {
    private static final Logger logger = Logger.getLogger(SubtitleAdjustTime.class);

    /**
     * 功能入口，获取字幕文件列表，按指定延迟批量处理字幕文件
     *
     * @param args 参数列表(包括字幕文件所在路径和延迟秒数)
     * @throws Exception
     */
    public static void main(String [] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: java -jar tools-1.0-SNAPSHOT.jar {subtitleFilePath} {delayTimeInSeconds}");
            System.exit(-1);
        }

        String filePath = args[0];
        String delay = args[1];
        int delayInMills = (int)(1000 * Float.valueOf(args[1]));

        logger.info("begin to process ...");

        List<String> fileList = FileUtil.getFileList(filePath);

        if (fileList != null && !fileList.isEmpty()) {
            for (String fileName : fileList) {
                process(fileName, delayInMills);

                logger.info("processed: " + fileName + "\t" + delay);
            }
        }

        logger.info("finish process ...");
    }

    /**
     * 处理字幕文件
     *
     * @param fileName 字幕文件的绝对路径
     * @param delayInMills 时间延迟毫秒数
     * @throws Exception
     */
    private static void process(String fileName, int delayInMills) throws Exception {
        if (fileName.endsWith(".ass")) AssAdjustTime.getInstance().process(fileName, delayInMills);
        if (fileName.endsWith(".srt")) SrtAdjustTime.getInstance().process(fileName, delayInMills);
    }
}
