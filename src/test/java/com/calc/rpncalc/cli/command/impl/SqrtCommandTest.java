package com.calc.rpncalc.cli.command.impl;


import com.calc.rpncalc.cli.exception.RPNCalculatorException;
import com.calc.rpncalc.cli.receiver.CommandReceiver;
import com.calc.rpncalc.cli.receiver.factory.CommandReceiverFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by sundas on 4/19/2017.
 */
public class SqrtCommandTest {


  private CommandReceiver commandReceiver;

  @Before
  public void setup(){
    commandReceiver = CommandReceiverFactory.getDefaultReceiver();
  }


  @Test
  public void setStateTest(){
    SqrtCommand sqrtCommand = new SqrtCommand();
    try {
      sqrtCommand.setState(new Object());
      Assert.assertFalse(sqrtCommand != null);
    } catch (UnsupportedOperationException e){
      Assert.assertNotNull(e);
    }
  }


  @Test
  public void testExecuteInsufficientParameters() {
    SqrtCommand sqrtCommand = new SqrtCommand();
    try {
      sqrtCommand.execute();
      Assert.assertFalse(sqrtCommand != null);
    } catch(RPNCalculatorException e){
      Assert.assertNotNull(e);
    }
  }


  @Test
  public void testExecuteNormal() {
    SqrtCommand sqrtCommand = new SqrtCommand();
    commandReceiver.addElement(new BigDecimal(16));
    try {
      sqrtCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(4).setScale(15, BigDecimal.ROUND_HALF_UP)));
    } catch(RPNCalculatorException e){
      Assert.assertFalse(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testExecuteInvalidOperand1() {
    SqrtCommand sqrtCommand = new SqrtCommand();
    commandReceiver.addElement(null);
    try {
      sqrtCommand.execute();
      Assert.assertFalse(commandReceiver.getInternaDSSize() == 1);
    } catch(RPNCalculatorException e){
      Assert.assertTrue(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testUndoNormal() {
    SqrtCommand sqrtCommand = new SqrtCommand();
    commandReceiver.addElement(new BigDecimal(16));
    try {
      sqrtCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(4).setScale(15, BigDecimal.ROUND_HALF_UP)));
      sqrtCommand.undo();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(16).setScale(15, BigDecimal.ROUND_HALF_UP)));
    } catch(RPNCalculatorException e){
      Assert.assertFalse(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }


  @Test
  public void testUndoMissingOperand() {
    SqrtCommand sqrtCommand = new SqrtCommand();
    try {
      sqrtCommand.execute();
    } catch(RPNCalculatorException e){
      Assert.assertTrue(e != null);
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 0);
      sqrtCommand.undo();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 0);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

}