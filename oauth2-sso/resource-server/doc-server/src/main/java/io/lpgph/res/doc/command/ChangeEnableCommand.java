package io.lpgph.res.doc.command;

import lombok.Value;

@Value
public class ChangeEnableCommand {
  Long id;
  Boolean discard;
}
