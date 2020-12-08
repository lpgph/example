package io.lpgph.examples;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("sender")
public class SenderController {

    private final SenderService senderService;

    @GetMapping("msg")
    public void sender(String msg) throws Exception {
        senderService.send(msg);
    }

}
