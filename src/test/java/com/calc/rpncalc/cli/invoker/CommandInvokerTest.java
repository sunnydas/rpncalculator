package com.calc.rpncalc.cli.invoker;

import com.calc.rpncalc.cli.command.impl.AddCommand;
import com.calc.rpncalc.cli.command.impl.PrintStateCommand;
import com.calc.rpncalc.cli.command.invoker.CommandInvoker;
import com.calc.rpncalc.cli.command.invoker.factory.CommandInvokerFactory;
import com.calc.rpncalc.cli.exception.RPNCalculatorException;
import com.calc.rpncalc.cli.receiver.CommandReceiver;
import com.calc.rpncalc.cli.receiver.factory.CommandReceiverFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by sundas on 4/20/2017.
 */
public class CommandInvokerTest {

  private CommandReceiver commandReceiver;

  @Before
  public void setup() {
    commandReceiver = CommandReceiverFactory.getDefaultReceiver();
  }

  @Test
  public void testNormalInvoke()throws RPNCalculatorException{
    CommandInvoker commandInvoker = CommandInvokerFactory.getDefaultInvoker();
    commandReceiver.addElement(new BigDecimal(5).setScale(15,BigDecimal.ROUND_HALF_UP));
    commandReceiver.addElement(new BigDecimal(10).setScale(15,BigDecimal.ROUND_HALF_UP));
    commandInvoker.setCommand(new AddCommand());
    commandInvoker.executeCommand();
    Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
    Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(15).setScale(15,BigDecimal.ROUND_HALF_UP)));
  }
}
