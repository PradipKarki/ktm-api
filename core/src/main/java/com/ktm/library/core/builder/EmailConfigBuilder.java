package com.ktm.library.core.builder;

import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class EmailConfigBuilder implements ApiBuilder<Mailer> {

  @Value("${simplejavamail.smtp.host}")
  private String host;

  @Value("${simplejavamail.smtp.port}")
  private int port;

  @Value("${simplejavamail.smtp.username}")
  private String username;

  @Value("${simplejavamail.smtp.password}")
  private String password;

  @Value("${simplejavamail.transportstrategy}")
  private String transportStrategy;

  @Value("${simplejavamail.defaults.poolsize}")
  private int poolSize;

  @Value("${simplejavamail.defaults.sessiontimeoutmillis}")
  private int sessionTimeoutMillis;

  @Value("${simplejavamail.javaxmail.debug}")
  private boolean debug;

  @Value("${simplejavamail.transport.mode.logging.only}")
  private boolean transportLoggingOnly;

  @Override
  public Mailer getInstance() {
    return MailerBuilder.withSMTPServer(host, port, username, password)
        .withTransportStrategy(TransportStrategy.SMTPS)
        .withThreadPoolSize(poolSize)
        .withSessionTimeout(sessionTimeoutMillis)
        .withDebugLogging(debug)
        .withTransportModeLoggingOnly(transportLoggingOnly)
        .buildMailer();
  }
}
