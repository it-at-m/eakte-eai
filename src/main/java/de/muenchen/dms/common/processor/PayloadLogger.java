package de.muenchen.dms.common.processor;

import de.muenchen.dms.common.route.RouteConstants;
import java.lang.reflect.Field;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.message.MessageContentsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PayloadLogger implements Processor {

  private String type;

  public PayloadLogger(String type) {
    this.type = type;
  }

  public PayloadLogger() {}

  private static final Logger log = LoggerFactory.getLogger(PayloadLogger.class);

  public void process(Exchange exchange) throws Exception {
    if (RouteConstants.REQ_IN.equals(type)) {
      logHeaders(exchange);

      logBody(exchange);

    } else if (RouteConstants.RESP_IN.equals(type)) {
      logHeaders(exchange);

      logHTTPStatusCode(exchange);

      logBody(exchange);
    }
  }

  private void logHTTPStatusCode(Exchange exchange) {
    Integer httpResponseCode =
        exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);

    if (httpResponseCode != null) {
      log.info("httpStatus: " + httpResponseCode);
    } else {
      log.info("No httpStatus");
    }
  }

  private void logHeaders(Exchange exchange) {
    Map<String, Object> headers = exchange.getMessage().getHeaders();
    if (headers != null) {
      if (RouteConstants.REQ_IN.equals(type)) {
        log.info("REQ_IN: ");
        for (Map.Entry<String, Object> entry : headers.entrySet()) {
          log.info(entry.getKey() + ": " + entry.getValue());
        }
        logUsernameFromAuthHeader(exchange);
      } else if (RouteConstants.RESP_IN.equals(type)) {
        log.info("RESP_IN: ");
        Object object = headers.get("operationName");
        if (object != null) {
          log.info("operationName: " + object);
        }
      }
    } else {
      log.info("No headers found");
    }
  }

  private void logUsernameFromAuthHeader(Exchange exchange) {

    String username = exchange.getProperty("AuthenticatedUser", String.class);

    if (username != null) {
      log.info("SST-Username: {}", username);
    } else {
      log.info("No authentication found");
    }
  }

  private void logBody(Exchange exchange) {
    Object body = exchange.getIn().getBody();
    if (RouteConstants.REQ_IN.equals(type)) {
      logAllFromPojo(body);
    } else if (RouteConstants.RESP_IN.equals(type)) {
      logMessageContentsList((MessageContentsList) body);
    }
  }

  private void logAllFromPojo(Object pojo) {
    Class<?> clazz = pojo.getClass();
    Field[] fields = clazz.getDeclaredFields();

    Integer statusValue = null;

    try {
      for (Field field : fields) {
        if ("status".equals(field.getName())) {
          field.setAccessible(true);
          Object value = field.get(pojo);
          if (value instanceof Integer sv) {
            statusValue = sv;
          }
          break;
        }
      }

      boolean statusNotZero = statusValue != null && statusValue != 0;

      for (Field field : fields) {
        field.setAccessible(true);
        Object value = field.get(pojo);
        if (value == null) {
          continue;
        }

        String fieldName = field.getName().toLowerCase();

        if (statusNotZero) {
          log.error(field.getName() + ": " + value);
        } else {
          if ("status".equalsIgnoreCase(fieldName) || "userlogin".equalsIgnoreCase(fieldName)) {
            log.info(field.getName() + ": " + value);
          }
        }
      }
    } catch (IllegalAccessException e) {
      log.trace("Zugriff nicht möglich");
    }
  }

  private void logMessageContentsList(MessageContentsList messageContentsList) {

    for (int i = 0; i < messageContentsList.size(); i++) {
      Object element = messageContentsList.get(i);

      if (element != null) {
        logAllFromPojo(element);
      } else {
        log.trace("Element is null");
      }
    }
  }
}
