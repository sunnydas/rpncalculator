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
public class MultiplyCommandTest {


  private CommandReceiver commandReceiver;

  @Before
  public void setup(){
    commandReceiver = CommandReceiverFactory.getDefaultReceiver();
  }


  @Test
  public void setStateTest(){
    MultiplyCommand multiplyCommand = new MultiplyCommand();
    try {
      multiplyCommand.setState(new Object());
      Assert.assertFalse(multiplyCommand != null);
    } catch (UnsupportedOperationException e){
      Assert.assertNotNull(e);
    }
  }


  @Test
  public void testExecuteInsufficientParameters() {
    MultiplyCommand multiplyCommand = new MultiplyCommand();
    try {
      multiplyCommand.execute();
      Assert.assertFalse(multiplyCommand != null);
    } catch(RPNCalculatorException e){
      Assert.assertNotNull(e);
    }
  }

  @Test
  public void testExecuteOperand1Missing() {
    MultiplyCommand multiplyCommand = new MultiplyCommand();
    commandReceiver.addElement(new BigDecimal(5));
    try {
      multiplyCommand.execute();
      Assert.assertFalse(multiplyCommand != null);
    } catch(RPNCalculatorException e){
      Assert.assertNotNull(e);
    }finally {
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testExecuteNormal() {
    MultiplyCommand multiplyCommand = new MultiplyCommand();
    commandReceiver.addElement(new BigDecimal(5));
    commandReceiver.addElement(new BigDecimal(5));
    try {
      multiplyCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(25).setScale(15, BigDecimal.ROUND_HALF_UP)));
    } catch(RPNCalculatorException e){
      Assert.assertFalse(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testExecuteInvalidOperand1() {
    MultiplyCommand multiplyCommand = new MultiplyCommand();
    commandReceiver.addElement(null);
    commandReceiver.addElement(new BigDecimal(5));
    try {
      multiplyCommand.execute();
      Assert.assertFalse(commandReceiver.getInternaDSSize() == 1);
    } catch(RPNCalculatorException e){
      Assert.assertTrue(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testExecuteInvalidOperand2() {
    MultiplyCommand multiplyCommand = new MultiplyCommand();
    commandReceiver.addElement(new BigDecimal(5));
    commandReceiver.addElement(null);
    try {
      multiplyCommand.execute();
      Assert.assertFalse(commandReceiver.getInternaDSSize() == 1);
    } catch(RPNCalculatorException e){
      Assert.assertTrue(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }


  @Test
  public void testUndoNormal() {
    MultiplyCommand multiplyCommand = new MultiplyCommand();
    commandReceiver.addElement(new BigDecimal(5));
    commandReceiver.addElement(new BigDecimal(5));
    try {
      multiplyCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(25).setScale(15, BigDecimal.ROUND_HALF_UP)));
      multiplyCommand.undo();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 2);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(5).setScale(15, BigDecimal.ROUND_HALF_UP)));
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(5).setScale(15, BigDecimal.ROUND_HALF_UP)));
    } catch(RPNCalculatorException e){
      Assert.assertFalse(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }


  @Test
  public void testUndoMissingOperand() {
    MultiplyCommand multiplyCommand = new MultiplyCommand();
    try {
      multiplyCommand.execute();
    } catch(RPNCalculatorException e){
      Assert.assertTrue(e != null);
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 0);
      multiplyCommand.undo();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 0);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

}
