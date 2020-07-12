import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;

/**
 * Created by zcqshine on 15/11/11.
 */
public class Test {
    public static HttpResponse post() throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://localhost/pokdeng/testFile.php");
        InputStream inputStream = new FileInputStream(new File("/Users/zcqshine/downloads/Screen.png"));
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addBinaryBody("upfile", inputStream, ContentType.create("application/x-www-form-urlencoded"), "filename");
        HttpEntity entity = builder.build();
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        return response;
    }

    public static void main(String[] args) throws IOException {
        HttpResponse resp = Test.post();
        InputStream inputStream = resp.getEntity().getContent();
        byte[] b = new byte[1024];
        inputStream.read(b);
        inputStream.close();

        System.out.println(new String(b));

    }
}
