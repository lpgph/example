
package io.lpgph.examples;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Service;

@Service
public class ReceiveService {

    @StreamListener(Processor.INPUT)
    public void receiveInput1(String receiveMsg) {
        System.out.println("input1 receive: " + receiveMsg);
    }

}
