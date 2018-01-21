package com.apple.ist.grx.security.response;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="GRXAuthenticationResponse", namespace="com.apple.ist.grx.security.response")
@XmlRootElement(name="GRXAuthenticationResponse")
public class GRXAuthenticationResponse
  implements Serializable
{
  private static final long serialVersionUID = -1728974784153349454L;
  private String sessionId;
  private Long personId;

  public String getSessionId()
  {
    return this.sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public Long getPersonId() {
    return this.personId;
  }

  public void setPersonId(Long personId) {
    this.personId = personId;
  }
}