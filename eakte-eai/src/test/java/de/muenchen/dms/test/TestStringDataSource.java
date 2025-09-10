package de.muenchen.dms.test;

import jakarta.activation.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import org.springframework.http.MediaType;

public class TestStringDataSource implements DataSource {
  private final String name;
  private final String content;

  public TestStringDataSource(String name, String content) {
    this.name = name;
    this.content = content;
  }

  @Override
  public InputStream getInputStream() throws IOException {
    return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public OutputStream getOutputStream() throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getContentType() {
    return MediaType.TEXT_PLAIN_VALUE;
  }

  @Override
  public String getName() {
    return this.name;
  }
}
