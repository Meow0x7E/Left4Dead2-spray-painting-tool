package com.github.Meow0x7E;

import com.github.Meow0x7E.simpleLog.LogLevel;
import com.github.Meow0x7E.simpleLog.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Left4Dead2SprayPaintingTool {
    public static Logger logger;

    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            logger = Logger.getLogger(LogLevel.INFO, Left4Dead2SprayPaintingTool.class);
        } else switch (args[0].toUpperCase()) {
            case "DEBUG" -> logger = Logger.getLogger(LogLevel.DEBUG, Left4Dead2SprayPaintingTool.class);
            case "WARN" -> logger = Logger.getLogger(LogLevel.WARN, Left4Dead2SprayPaintingTool.class);
            case "ERROR" -> logger = Logger.getLogger(LogLevel.ERROR, Left4Dead2SprayPaintingTool.class);
            case "FATAL" -> logger = Logger.getLogger(LogLevel.FATAL, Left4Dead2SprayPaintingTool.class);
            default -> logger = Logger.getLogger(LogLevel.INFO, Left4Dead2SprayPaintingTool.class);
        }

        List<String> filePathList = getFilePathVTFOnly(".\\");
        if (filePathList == null) {
            logger.fatal("失败：未发现任何.vtf结尾的文件");
            System.exit(1);
        }

        try {
            int fileSize = filePathList.size() - 1;
            File automaticSwitching = new File(".\\automatic_switching.cfg");
            File spraysManifest = new File(".\\scripts\\sprays_manifest.txt");

            if (!spraysManifest.getParentFile().exists()) spraysManifest.getParentFile().mkdir();

            BufferedWriter automaticSwitchingWriter = new BufferedWriter(new FileWriter(automaticSwitching, false));
            BufferedWriter spraysManifestWriter = new BufferedWriter(new FileWriter(spraysManifest, false));
            StringBuilder automaticSwitchingBuilder = new StringBuilder();
            StringBuilder spraysManifestBuilder = (new StringBuilder()).append("sprays_manifest\n{\n");

            for (int i = 0; i <= fileSize; ++i) {
                File vtfFile = new File(filePathList.get(i));
                String vtfPath = vtfFile.getPath().substring(2);
                String vtfName = vtfFile.getName();
                File vmtFile = new File(vtfPath.replaceFirst("vtf$", "vmt"));
                String vmtPath = vmtFile.getPath();
                String vmtName = vmtFile.getName();
                if (vmtFile.exists()) {
                    logger.info("%s已存在！已忽略对\"%s\"的创建", vmtPath, vmtName);
                } else {
                    BufferedWriter vmtWriter = new BufferedWriter(new FileWriter(vmtFile, true));
                    vmtWriter.write("VertexlitGeneric\n{\n$basetexture \"" + vmtPath + "\"\n$vertexcolor 1\n$vertexalpha 1\n$translucent 1\n}\n");
                    vmtWriter.flush();
                    vmtWriter.close();
                    logger.info("未发现\"%s\"，已创建并写入数据", vmtPath);
                }

                automaticSwitchingBuilder.append(String.format("alias \"image_%d\" \"cl_logofile %s;image_%d\"\n", i, vtfPath, i == fileSize ? 0 : i + 1));
                spraysManifestBuilder.append(String.format("\"%s\"\t\"%s\"\n", vtfName.substring(0, vtfName.length() - 4), vtfPath));
                if (i % 100 == 0) {
                    automaticSwitchingWriter.write(automaticSwitchingBuilder.toString());
                    automaticSwitchingWriter.flush();
                    automaticSwitchingBuilder.setLength(0);
                    spraysManifestWriter.write(spraysManifestBuilder.toString());
                    spraysManifestWriter.flush();
                    spraysManifestBuilder.setLength(0);
                }
            }

            automaticSwitchingWriter.write(automaticSwitchingBuilder.append("image_0").toString());
            automaticSwitchingWriter.flush();
            automaticSwitchingWriter.close();
            logger.info("" + automaticSwitching + "写入完成");
            spraysManifestWriter.write(spraysManifestBuilder.append('}').toString());
            spraysManifestWriter.flush();
            spraysManifestWriter.close();
            logger.info("" + spraysManifest + "写入完成");
        } catch (IOException var19) {
            logger.fatal(var19.toString());
            logger.fatal("程序已终止！");
            System.exit(1);
        }

        logger.info("完成!");
        System.exit(0);
    }

    /**
     * 获取指定路径下所有扩展名为 ".vtf" 的文件路径列表。
     *
     * @param path 文件夹路径
     * @return 由所有扩展名为 ".vtf" 文件的路径组成的列表
     */
    private static List<String> getFilePathVTFOnly(String path) {
        return getFilePathVTFOnly(new File(path));
    }

    /**
     * 获取指定文件夹及其子文件夹下所有扩展名为 ".vtf" 的文件路径列表。
     *
     * @param f 文件夹路径
     * @return 由所有扩展名为 ".vtf" 文件的路径组成的列表
     */
    private static List<String> getFilePathVTFOnly(File f) {
        List<String> filePathList = new ArrayList<>();
        File[] files = f.listFiles();
        if (files == null) return null;

        for (File file : files) {
            if (file.isDirectory()) {
                List<String> temp = getFilePathVTFOnly(file);
                if (temp != null) filePathList.addAll(temp);
            } else if (file.isFile()) {
                Pattern p = Pattern.compile("\\.vtf$");
                if (p.matcher(file.getName()).find()) filePathList.add(file.toString());
            }
        }
        return filePathList;
    }
}