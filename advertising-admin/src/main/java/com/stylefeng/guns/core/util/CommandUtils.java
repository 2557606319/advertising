package com.stylefeng.guns.core.util;

import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.exception.GunsRestExceptionEnum;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CommandUtils {

//    //获取执行命令，音量和比特率可以不传
//    List<String> ffmpegCommand = CommandUtils.getFfmpegCommand("/Users/joey/Desktop/test-v.mp4", "/Users/joey/Desktop/test-v.ts");
//    //执行转换
//    boolean process = CommandUtils.process(ffmpegCommand);

    /**
     * 获取命令
     *具体命令的含义可以百度
     *
     * @param oldfilepath 源文件
     * @param outputPath  转换后的文件
     * @return 命令
     */
    public static List<String> getFfmpegCommand(String oldfilepath, String outputPath) {
        // 转换命令
        // ffmpeg -i test-v.mp4 -f mpegts -codec:v mpeg1video -b:v 1500k -r 30 -bf 0 -codec:a mp2 -ac 1 -b:a 128k out.ts

        File oldFile=new File(oldfilepath);

        if(!oldFile.exists())
            return null;
        File outFile=new File(outputPath);

        if(oldFile.exists())
            outFile.delete();

        List<String> command = new ArrayList<String>();
        command.add("ffmpeg");
        command.add("-i");
        command.add(oldfilepath);
        //视频解码器
        command.add("-f");
        command.add("mpegts");
        command.add("-codec:v");
        command.add("mpeg1video");
        command.add("-b:v");
        command.add("2000k");
        command.add("-r");
        command.add("30");
        command.add("-bf");
        command.add("0");
        command.add("-codec:a");
        command.add("mp2");
        command.add("-ac");
        command.add("1");
        command.add("-b:a");
        command.add("128k");
        command.add(outputPath);
        return command;
    }

    /**
     * 执行转换
     * @param command 命令
     * @return 返回是否成功
     * @throws Exception 异常
     */
    public static boolean process(List<String> command) throws Exception {

        try {

            if (null == command || command.size() == 0) {
                return false;
            }
            Process videoProcess = new ProcessBuilder(command).redirectErrorStream(true).start();
            new PrintStream(videoProcess.getErrorStream()).start();
            new PrintStream(videoProcess.getInputStream()).start();
            int exitCode = videoProcess.waitFor();
            if (exitCode == 1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new Exception("file uploadfailed", e);
        }
    }

    /**
     * 文件读取
     */
    public static class PrintStream extends Thread {
        InputStream inputStream = null;

        PrintStream(InputStream is) {
            inputStream = is;
        }

        @Override
        public void run() {
            try {
                while (this != null) {
                    int ch = inputStream.read();
                    if (ch != -1) {
                        System.out.print((char) ch);
                    } else {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
