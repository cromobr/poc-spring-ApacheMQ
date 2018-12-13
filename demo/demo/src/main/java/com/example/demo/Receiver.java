package com.example.demo;

import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }

  @JmsListener(destination = "${queue.boot}")
  public void receive(ActiveMQBytesMessage message) throws UnknownHostException {
	  
	String temp = new String(message.getContent().getData());
    LOGGER.info("received message='{}'",temp );
    MongoBD.insert(temp);
    latch.countDown();
  }
}