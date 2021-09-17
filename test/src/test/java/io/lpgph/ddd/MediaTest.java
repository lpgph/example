package io.lpgph.ddd;

import io.lpgph.ddd.utils.MediaUtil;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Map;

@Slf4j
public class MediaTest {

  @Test
  public void readVideo() throws IOException {
    String coverPath = "/home/lucas/Templates/test/test.jpg";
    File file = new File("/home/lucas/Templates/yanwuxie.mkv");
    InputStream inputStream = MediaUtil.image(MediaUtil.frame(file));
    OutputStream out = new FileOutputStream(coverPath);
    assert inputStream != null;
    out.write(inputStream.readAllBytes());
    long duration = MediaUtil.duration(file);
    log.info("duration {}", duration);
  }

  @Test
  public void readAudio() {
    //    File file = new File("/home/lucas/Templates/yanwuxie.mkv");
    //    File file = new File("/home/lucas/Templates/12333.aac");
    File file = new File("/home/lucas/Templates/Ahxello-Frisbee.mp3");
    log.info("file name {} ", file.getName());
    log.info("file size {} ", file.length());
    FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file);
    log.info("\nformat: {} \n", ff.getFormat());
    try {
      ff.start();
      Map<String, String> metadata = ff.getMetadata();
      log.info("\n{}\n\n\n", JsonUtil.toJson(metadata));
      log.info(
          "\n数据：\n{}\n{}\n{}\n{}\n",
          metadata.get("artist"),
          metadata.get("title"),
          metadata.get("album"),
          metadata.get("comment"));
      Frame frame = ff.grabImage();
      if (frame != null) {
        InputStream inputStream = MediaUtil.image(frame);
        OutputStream out = new FileOutputStream("/home/lucas/Templates/test/cover.jpg");
        assert inputStream != null;
        out.write(inputStream.readAllBytes());
      }
      ff.stop();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
