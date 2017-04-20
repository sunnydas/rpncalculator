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
public class StoreCommandTest {

  private CommandReceiver commandReceiver;

  @Before
  public void setup(){
    commandReceiver = CommandReceiverFactory.getDefaultReceiver();
  }


  @Test
  public void setStateTest(){
    StoreCommand storeCommand = new StoreCommand();
    try {
      storeCommand.setState(new Object());
      Assert.assertTrue(storeCommand != null);
    } catch (UnsupportedOperationException e){
      Assert.assertFalse(e != null);
    }
  }


  @Test
  public void testExecuteNormal() {
    StoreCommand storeCommand = new StoreCommand();
    try {
      storeCommand.setState("12");
      storeCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(12).setScale(15, BigDecimal.ROUND_HALF_UP)));
    } catch(RPNCalculatorException e){
      Assert.assertFalse(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testExecuteError() {
    StoreCommand storeCommand = new StoreCommand();
    try {
      storeCommand.setState("test_dummy");
      storeCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(12).setScale(15, BigDecimal.ROUND_HALF_UP)));
    } catch(RPNCalculatorException e) {
      Assert.assertFalse(e != null);
    } catch (NumberFormatException e){
      Assert.assertNotNull(e);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testUndoNormal() {
    StoreCommand storeCommand = new StoreCommand();
    try {
      storeCommand.setState("12");
      storeCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(12).setScale(15, BigDecimal.ROUND_HALF_UP)));
      storeCommand.undo();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 0);
    } catch(RPNCalculatorException e) {
      Assert.assertFalse(e != null);
    } catch (UnsupportedOperationException e){
      Assert.assertTrue(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }
}
