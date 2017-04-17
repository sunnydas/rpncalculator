package com.sunny.rpncalc.cli.exception;

/**
 * Created by sundas on 4/15/2017.
 */
public class RPNCalculatorException extends Exception {

  public RPNCalcError getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(RPNCalcError statusCode) {
    this.statusCode = statusCode;
  }

  private RPNCalcError statusCode;

  @Override
  public String toString() {
    return "RPNCalculatorException{" +
        "statusCode=" + statusCode +
        '}';
  }

  public RPNCalculatorException() {
  }

  public RPNCalculatorException(String message, Throwable cause) {
    super(message, cause);
  }

  public RPNCalculatorException(String message) {
    super(message);
  }
}
