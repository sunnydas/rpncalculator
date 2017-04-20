package com.calc.rpncalc.cli.command.impl;

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
public class PrintStateCommandTest {

  private CommandReceiver commandReceiver;

  @Before
  public void setup(){
    commandReceiver = CommandReceiverFactory.getDefaultReceiver();
  }


  @Test
  public void setStateTest(){
    PrintStateCommand printStateCommand = new PrintStateCommand();
    try {
      printStateCommand.setState(new Object());
      Assert.assertFalse(printStateCommand != null);
    } catch (UnsupportedOperationException e){
      Assert.assertNotNull(e);
    }
  }

  @Test
  public void testExecuteNormal() {
    PrintStateCommand printStateCommand = new PrintStateCommand();
    commandReceiver.addElement(new BigDecimal(16).setScale(15,BigDecimal.ROUND_HALF_UP));
    try {
      printStateCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(16).setScale(15, BigDecimal.ROUND_HALF_UP)));
    } catch(RPNCalculatorException e){
      Assert.assertFalse(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testUndoNormal() {
    PrintStateCommand printStateCommand = new PrintStateCommand();
    commandReceiver.addElement(new BigDecimal(16).setScale(15,BigDecimal.ROUND_HALF_UP));
    try {
      printStateCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(16).setScale(15, BigDecimal.ROUND_HALF_UP)));
      printStateCommand.undo();
      Assert.assertFalse(commandReceiver.getInternaDSSize() == 1);
      Assert.assertFalse(commandReceiver.fetchElement().equals(new BigDecimal(16).setScale(15, BigDecimal.ROUND_HALF_UP)));
    } catch(RPNCalculatorException e) {
      Assert.assertFalse(e != null);
    } catch (UnsupportedOperationException e){
      Assert.assertTrue(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }
}
