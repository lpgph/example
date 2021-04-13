package io.lpgph.ddd.utils;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Slf4j
public final class MediaUtil {
  private static final transient String COVER_FORMAT = "jpg";

  /** 获取媒体时长 */
  public static long duration(File file) {
    long duration = 0L;
    FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file);
    try {
      ff.start();
      duration = ff.getLengthInTime() / (1000 * 1000);
      log.info("width x height: {} x {}", ff.getImageWidth(), ff.getImageHeight());
      ff.stop();
    } catch (FrameGrabber.Exception e) {
      e.printStackTrace();
    }
    return duration;
  }

  /** 根据帧数生成图片输出流 */
  public static InputStream image(Frame frame) {
    try {
      Java2DFrameConverter converter = new Java2DFrameConverter();
      BufferedImage srcImage = converter.getBufferedImage(frame);
      int srcImageWidth = srcImage.getWidth();
      int srcImageHeight = srcImage.getHeight();
      // 对截图进行等比例缩放(缩略图)
      int width = 480;
      int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
      BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
      thumbnailImage
          .getGraphics()
          .drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      ImageIO.write(thumbnailImage, COVER_FORMAT, os);
      return new ByteArrayInputStream(os.toByteArray());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 获得媒体封面
   *
   * @param media 源视频文件
   */
  public static Frame frame(File media) {
    FFmpegFrameGrabber ff = new FFmpegFrameGrabber(media);
    try {
      ff.start();
      Frame frame = ff.grabImage();
      ff.stop();
      return frame;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 获得视频指定帧
   *
   * @param video 源视频文件
   */
  public static Frame videoFrame(File video, int frameNumber) {
    FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
    try {
      ff.start();
      int i = 0;
      int length = ff.getLengthInFrames();
      Frame frame = null;
      while (i < length) {
        frame = ff.grabFrame();
        if ((i > frameNumber) && (frame.image != null)) {
          break;
        }
        i++;
      }
      ff.stop();
      return frame;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
