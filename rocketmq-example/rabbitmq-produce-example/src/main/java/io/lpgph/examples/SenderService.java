
package io.lpgph.examples;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SenderService {

    private final Processor source;

    public void send(String msg) throws Exception {
        source.output().send(MessageBuilder.withPayload(msg).build());
    }

}
