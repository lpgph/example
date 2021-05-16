package io.lpgph.res.doc.command;

import lombok.Value;

@Value
public class ChangeDiscardCommand {
  Long id;
  Boolean discard;
}
