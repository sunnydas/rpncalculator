package com.calc.rpncalc.cli.command.invoker;

import com.calc.rpncalc.cli.command.RPNCalculatorCommand;
import com.calc.rpncalc.cli.exception.RPNCalculatorException;

/**
 * Created by sundas on 4/15/2017.
 */
public interface CommandInvoker<P> {

  public void setCommand(RPNCalculatorCommand command);

  public void executeCommand() throws RPNCalculatorException;
}
