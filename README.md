tools
=====

工具箱(Java版)

***

## SubtitleAdjustTime

###功能

批量平移ass或srt字幕的时间轴，可提前或延迟，支持毫秒级平移

###用法

使用`mvn clean package`进行打jar包，然后使用下列方式进行执行：

<pre>java -jar tools-1.0-SNAPSHOT.jar {subtitleFilePath} {delayTimeInSeconds}</pre>
