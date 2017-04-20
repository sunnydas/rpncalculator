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
public class ClearCommandTest {

  private CommandReceiver commandReceiver;

  @Before
  public void setup(){
    commandReceiver = CommandReceiverFactory.getDefaultReceiver();
  }


  @Test
  public void setStateTest(){
    ClearCommand clearCommand = new ClearCommand();
    try {
      clearCommand.setState(new Object());
      Assert.assertTrue(clearCommand != null);
    } catch (UnsupportedOperationException e){
      Assert.assertTrue(e != null);
    }
  }


  @Test
  public void testExecuteNormal() {
    ClearCommand clearCommand = new ClearCommand();
    try {
      commandReceiver.addElement(new BigDecimal(5).setScale(15, BigDecimal.ROUND_HALF_UP));
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      clearCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 0);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }


  @Test
  public void testUndoNormal() {
    ClearCommand clearCommand = new ClearCommand();
    try {
      clearCommand.setState("12");
      clearCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(12).setScale(15, BigDecimal.ROUND_HALF_UP)));
      clearCommand.undo();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 0);
    } catch(UnsupportedOperationException e) {
      Assert.assertTrue(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }
}
