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
public class DivideCommandTest {


  private CommandReceiver commandReceiver;

  @Before
  public void setup(){
    commandReceiver = CommandReceiverFactory.getDefaultReceiver();
  }


  @Test
  public void setStateTest(){
    DivideCommand divideCommand = new DivideCommand();
    try {
      divideCommand.setState(new Object());
      Assert.assertFalse(divideCommand != null);
    } catch (UnsupportedOperationException e){
      Assert.assertNotNull(e);
    }
  }


  @Test
  public void testExecuteInsufficientParameters() {
    DivideCommand divideCommand = new DivideCommand();
    try {
      divideCommand.execute();
      Assert.assertFalse(divideCommand != null);
    } catch(RPNCalculatorException e){
      Assert.assertNotNull(e);
    }
  }

  @Test
  public void testExecuteOperand1Missing() {
    DivideCommand divideCommand = new DivideCommand();
    commandReceiver.addElement(new BigDecimal(5));
    try {
      divideCommand.execute();
      Assert.assertFalse(divideCommand != null);
    } catch(RPNCalculatorException e){
      Assert.assertNotNull(e);
    }finally {
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testExecuteNormal() {
    DivideCommand divideCommand = new DivideCommand();
    commandReceiver.addElement(new BigDecimal(10));
    commandReceiver.addElement(new BigDecimal(5));
    try {
      divideCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(2).setScale(15, BigDecimal.ROUND_HALF_UP)));
    } catch(RPNCalculatorException e){
      Assert.assertFalse(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testExecuteInvalidOperand1() {
    DivideCommand divideCommand = new DivideCommand();
    commandReceiver.addElement(null);
    commandReceiver.addElement(new BigDecimal(5));
    try {
      divideCommand.execute();
      Assert.assertFalse(commandReceiver.getInternaDSSize() == 1);
    } catch(RPNCalculatorException e){
      Assert.assertTrue(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testExecuteInvalidOperand2() {
    DivideCommand divideCommand = new DivideCommand();
    commandReceiver.addElement(new BigDecimal(5));
    commandReceiver.addElement(null);
    try {
      divideCommand.execute();
      Assert.assertFalse(commandReceiver.getInternaDSSize() == 1);
    } catch(RPNCalculatorException e){
      Assert.assertTrue(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }


  @Test
  public void testUndoNormal() {
    DivideCommand divideCommand = new DivideCommand();
    commandReceiver.addElement(new BigDecimal(10));
    commandReceiver.addElement(new BigDecimal(5));
    try {
      divideCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(2).setScale(15, BigDecimal.ROUND_HALF_UP)));
      divideCommand.undo();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 2);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(5).setScale(15, BigDecimal.ROUND_HALF_UP)));
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(10).setScale(15, BigDecimal.ROUND_HALF_UP)));
    } catch(RPNCalculatorException e){
      Assert.assertFalse(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }


  @Test
  public void testUndoMissingOperand() {
    DivideCommand divideCommand = new DivideCommand();
    try {
      divideCommand.execute();
    } catch(RPNCalculatorException e){
      Assert.assertTrue(e != null);
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 0);
      divideCommand.undo();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 0);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

}
