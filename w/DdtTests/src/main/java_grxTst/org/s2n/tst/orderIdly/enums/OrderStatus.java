package org.s2n.tst.orderIdly.enums;

public enum OrderStatus
{
  COMPLETED(0), 
  NEW(1), 
  PRIMARY_CANCELLATION_PENDING(2), 
  SECONDARY_CANCELLATION_PENDING(3), 
  PRIMARY_CANCELLED(4), 
  SECONDARY_CANCELLED(5);

  private int statusCode;

  private OrderStatus(int code) {
    this.statusCode = code;
  }

  public int getStatusCode() {
    return this.statusCode;
  }
}
