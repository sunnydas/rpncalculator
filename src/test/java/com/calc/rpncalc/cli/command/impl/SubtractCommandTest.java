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
public class SubtractCommandTest {


  private CommandReceiver commandReceiver;

  @Before
  public void setup(){
    commandReceiver = CommandReceiverFactory.getDefaultReceiver();
  }


  @Test
  public void setStateTest(){
    MinusCommand minusCommand = new MinusCommand();
    try {
      minusCommand.setState(new Object());
      Assert.assertFalse(minusCommand != null);
    } catch (UnsupportedOperationException e){
      Assert.assertNotNull(e);
    }
  }


  @Test
  public void testExecuteInsufficientParameters() {
    MinusCommand minusCommand = new MinusCommand();
    try {
      minusCommand.execute();
      Assert.assertFalse(minusCommand != null);
    } catch(RPNCalculatorException e){
      Assert.assertNotNull(e);
    }
  }

  @Test
  public void testExecuteOperand1Missing() {
    MinusCommand minusCommand = new MinusCommand();
    commandReceiver.addElement(new BigDecimal(5));
    try {
      minusCommand.execute();
      Assert.assertFalse(minusCommand != null);
    } catch(RPNCalculatorException e){
      Assert.assertNotNull(e);
    }finally {
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testExecuteNormal() {
    MinusCommand minusCommand = new MinusCommand();
    commandReceiver.addElement(new BigDecimal(5));
    commandReceiver.addElement(new BigDecimal(5));
    try {
      minusCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(0).setScale(15, BigDecimal.ROUND_HALF_UP)));
    } catch(RPNCalculatorException e){
      Assert.assertFalse(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testExecuteInvalidOperand1() {
    MinusCommand minusCommand = new MinusCommand();
    commandReceiver.addElement(null);
    commandReceiver.addElement(new BigDecimal(5));
    try {
      minusCommand.execute();
      Assert.assertFalse(commandReceiver.getInternaDSSize() == 1);
    } catch(RPNCalculatorException e){
      Assert.assertTrue(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testExecuteInvalidOperand2() {
    MinusCommand minusCommand = new MinusCommand();
    commandReceiver.addElement(new BigDecimal(5));
    commandReceiver.addElement(null);
    try {
      minusCommand.execute();
      Assert.assertFalse(commandReceiver.getInternaDSSize() == 1);
    } catch(RPNCalculatorException e){
      Assert.assertTrue(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }


  @Test
  public void testUndoNormal() {
    MinusCommand minusCommand = new MinusCommand();
    commandReceiver.addElement(new BigDecimal(5));
    commandReceiver.addElement(new BigDecimal(5));
    try {
      minusCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(0).setScale(15, BigDecimal.ROUND_HALF_UP)));
      minusCommand.undo();
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
    MinusCommand minusCommand = new MinusCommand();
    try {
      minusCommand.execute();
    } catch(RPNCalculatorException e){
      Assert.assertTrue(e != null);
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 0);
      minusCommand.undo();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 0);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

}
