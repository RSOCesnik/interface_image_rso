package si.fri.project.imageInterface;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;


public class ClientRSO {


    static {
        disableSslVerification();
    }

    private static void disableSslVerification() {
        try
        {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }


    public static String get_images() throws IOException {
        return IOUtils.toString(new URL("http://35.246.69.45/image-catalog/v1/catalog/"), StandardCharsets.UTF_8);
    }

    public static String get_image_and_comments(String id) throws IOException {
        return IOUtils.toString(new URL("http://35.246.69.45/image-comments/v1/comments/image/"+id), StandardCharsets.UTF_8);
    }

    public static String upload_picture(String title,String desc,String image) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://35.246.69.45/image-upload/v1/uploads");
        StringEntity params =new StringEntity("{\"title\":\""+title+"\",\"description\":\""+desc+"\",\"data\":\"data:image/jpeg;base64,"+image+"\"} ");
        post.setEntity(params);

        post.setEntity(params);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(post);
        client.close();
        return response.toString();
    }


    public static String add_comment(String id, String data) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://35.246.69.45/image-comments/v1/comments/add");
        StringEntity params =new StringEntity("{\"imageId\":\""+id+"\",\"commentData\":\""+data+"\"} ");
        post.setEntity(params);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(post);
        client.close();
        return response.toString();
    }

    public static HttpResponse delete_comment(String id) throws IOException {
        String deleteEndpoint = "http://35.246.69.45/image-comments/v1/comments/delete/"+id;

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpDelete httpDelete = new HttpDelete(deleteEndpoint);
        HttpResponse response = httpclient.execute(httpDelete);

        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println("Response : \n"+result.append(line));
        }
        return response;

    }

}
