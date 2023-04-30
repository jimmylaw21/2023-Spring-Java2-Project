//package cse.java2.project.utils;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.web.client.ResponseExtractor;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.zip.GZIPInputStream;
//
//public class GzipResponseInterceptor implements ResponseExtractor<InputStream> {
//
//  @Override
//  public InputStream extractData(ClientHttpResponse response) throws IOException {
//    HttpHeaders headers = response.getHeaders();
//    if (headers.getContentEncoding() != null && headers.getContentEncoding().contains("gzip")) {
//      return new GZIPInputStream(response.getBody());
//    } else {
//      return response.getBody();
//    }
//  }
//}
