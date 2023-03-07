package com.mario.input.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Java图片操作
 * https://blog.csdn.net/xietansheng/article/details/78453570
 *
 * @author yym
 */
public class BitmapUtils {

    /**
     * 读取图片
     */
    public static final BufferedImage readImage(String fileName) {
        try {
            return ImageIO.read(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建指定大小的图片
     *
     * @param srcBmp   原始图片
     * @param PageSize 图片大小 单位mm
     * @param printDPI 打印机分辨率
     */
    public synchronized static BufferedImage createPageBitmap(BufferedImage srcBmp, Point PageSize, Point printDPI) {
        //计算图像宽度、高度
        Point pageSizePixel = new Point((int) (PageSize.x / 25.4 * printDPI.x), (int) (PageSize.y / 25.4 * printDPI.y));

        int width = ((pageSizePixel.x - 1) / 8 + 1) * 8;
        int height = pageSizePixel.y;
        //创建指定大小的位图
        BufferedImage dstBmp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //绘图
        Graphics2D graphics = dstBmp.createGraphics();
        RenderingHints rh = graphics.getRenderingHints();
        graphics.drawImage(srcBmp.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        graphics.dispose();
        return dstBmp;
    }


    /**
     * 读取位图ARGB像素数据
     * @param image 图片
     * @return 返回读取的总像素数；格式为R_G_B_A
     */
    public static final byte[] readImagePiex(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        // 存放图片所有像素
        byte[] piexs = new byte[width * height * 4];
        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                //R_G_B_A
                piexs[index] = (byte) ((rgb & 0xff0000) >> 16);
                piexs[index + 1] = (byte) ((rgb & 0xff00) >> 8);
                piexs[index + 2] = (byte) (rgb & 0xff);
                piexs[index + 3] = (byte) ((rgb >> 24) & 0xff);
//                System.out.println("R=" + piexs[index] + " G=" + piexs[index + 1] + " B=" + piexs[index + 2] + " A=" + piexs[index + 3]);
                index += 4;
            }
        }
        return piexs;
    }

    /**
     * 保存图片到本地
     * @param image 图片对象
     * @param path  保存路径
     */
    public static final void saveImage(BufferedImage image, String path) {
        try {
            ImageIO.write(image, "PNG", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 图片像素数据保存为图片
     * @param width 图片宽度
     * @param height 图片高度
     * @param piexs 像素数据
     * @param path 保存路径
     */
    public static final void saveImage(int width,int height, byte[] piexs, String path) {
        try {
            //创建指定大小的图片
            BufferedImage bmp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            int[] rgb = new int[width * height];
            int index = 0;
            int totalPiex = width * height * 4;
            for (int i = 0; i < totalPiex; i=i+4) {
                //byte转int存放ARGB像素值
                rgb[index] = ((piexs[i + 3] << 24) & 0xff000000) | ((piexs[i] << 16) & 0xff0000)
                        | ((piexs[i + 1] << 8) & 0xff00) | ((piexs[i + 2]) & 0xff);
                index++;
            }
            bmp.setRGB(0, 0, width, height, rgb, 0, width);
            ImageIO.write(bmp, "PNG", new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BufferedImage srcImage = readImage("C:\\Users\\yym\\Desktop\\24.bmp");
        BufferedImage pageBitmap = createPageBitmap(srcImage, new Point(50, 50), new Point(200, 200));
        byte[] bytes = readImagePiex(pageBitmap);
        saveImage(pageBitmap.getWidth(), pageBitmap.getHeight(), bytes, "C:\\Users\\yym\\Desktop\\tmp.png");
//        saveImage(pageBitmap, "C:\\Users\\yym\\Desktop\\tmp.png");
    }
}
