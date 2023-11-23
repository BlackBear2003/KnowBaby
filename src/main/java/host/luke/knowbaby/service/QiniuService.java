package host.luke.knowbaby.service;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import java.io.ByteArrayInputStream;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QiniuService {

  @Value("${qiniu.access-key}")
  private String accessKey;
  @Value("${qiniu.secret-key}")
  private String secretKey;
  @Value("${qiniu.bucket-name}")
  private String bucketName;
  @Value("${qiniu.host-name}")
  private String hostName;

  @PostConstruct
  public void init() {
  }


  public String uploadImg(String base64Img) {

    //构造一个带指定 Region 对象的配置类
    Configuration cfg = new Configuration(Region.region2());
    cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
    //...其他参数参考类注释
    UploadManager uploadManager = new UploadManager(cfg);

    //默认不指定key的情况下，以文件内容的hash值作为文件名
    String key = null;
    Auth auth = Auth.create(accessKey, secretKey);
    String upToken = auth.uploadToken(bucketName);
    byte[] img = Base64.decode(base64Img);
    ByteArrayInputStream inputStream = IoUtil.toStream(img);
    try {
      Response response = uploadManager.put(inputStream, key, upToken, null, null);
      //解析上传成功的结果
      DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
      return hostName + "/" + putRet.key;
    } catch (QiniuException ex) {
      ex.printStackTrace();
      return null;
    }
  }

}
