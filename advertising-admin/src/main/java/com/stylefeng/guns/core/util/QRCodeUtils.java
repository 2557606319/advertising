package com.stylefeng.guns.core.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.qr.MatrixToImageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class QRCodeUtils {

    @Autowired
    GunsProperties properties;

    /**
     * 生成二维码
     *
     * @param content  二维码内容
     * @param filePath 文件路径
     * @param fileName 文件名
     * @param w        宽度
     * @param h        高度
     * @throws Exception
     */
    public static void createQR(String content, String filePath, String fileName, int w, int h) throws Exception {
//        String filePath = properties.getWebServerPath() + "/web-img/";
//        String fileName = DateUtil.getDays() + "/" + UUID.randomUUID().toString() + ".png";// 图像类型
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 2);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, w, h, hints);// 生成矩阵
        File file = new File(filePath + fileName);
        if (!file.exists()) file.mkdirs();
        Path path = FileSystems.getDefault().getPath(file.getPath());
        MatrixToImageWriter.writeToPath(bitMatrix, "png", path);// 输出图像
    }

    /**
     * 为图片添加图片水印
     *
     * @param watermarkUrl 水印图
     * @param sourceUrl    原图
     * @param outputUrl    目标图
     * @param x            y  水印位置
     * @param w            h  水印高宽
     * @return
     * @throws IOException
     */
    public static void markImgMark(String watermarkUrl, String sourceUrl, String outputUrl, int x, int y, int w, int h) throws IOException {
        File file = new File(sourceUrl);
        Image img = ImageIO.read(file);
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        ImageIcon imgIcon = new ImageIcon(watermarkUrl);
        Image con = imgIcon.getImage();
        float clarity = 1f;//透明度
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, clarity));
        g.drawImage(con, x, y, w, h, null);//水印的位置，大小
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        g.dispose();
        File sf = new File(outputUrl);
        if (!sf.exists()) sf.mkdirs();
        ImageIO.write(bi, "jpg", sf); // 保存图片
    }


    public static void main(String[] args) throws IOException {
        //tg1-small = 755, 1425
        //tg2-small = 60, 1475

//        QRCodeUtils.markImgMark("/usr/local/var/www/advertising/images/wx-qr.jpg",
//                "/usr/local/var/www/advertising/images/tg2-small.jpg",
//                "/usr/local/var/www/advertising/test.jpg");
    }

}