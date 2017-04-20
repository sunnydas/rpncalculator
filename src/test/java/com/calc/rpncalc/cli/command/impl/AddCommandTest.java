package com.calc.rpncalc.cli.command.impl;


import com.calc.rpncalc.cli.exception.RPNCalculatorException;
import com.calc.rpncalc.cli.receiver.CommandReceiver;
import com.calc.rpncalc.cli.receiver.factory.CommandReceiverFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by sundas on 4/19/2017.
 */
public class AddCommandTest {


  private CommandReceiver commandReceiver;

  @Before
  public void setup(){
    commandReceiver = CommandReceiverFactory.getDefaultReceiver();
  }


  @Test
  public void setStateTest(){
    AddCommand addCommand = new AddCommand();
    try {
      addCommand.setState(new Object());
      Assert.assertFalse(addCommand != null);
    } catch (UnsupportedOperationException e){
      Assert.assertNotNull(e);
    }
  }


  @Test
  public void testExecuteInsufficientParameters() {
    AddCommand addCommand = new AddCommand();
    try {
      addCommand.execute();
      Assert.assertFalse(addCommand != null);
    } catch(RPNCalculatorException e){
      Assert.assertNotNull(e);
    }
  }

  @Test
  public void testExecuteOperand1Missing() {
    AddCommand addCommand = new AddCommand();
    commandReceiver.addElement(new BigDecimal(5));
    try {
      addCommand.execute();
      Assert.assertFalse(addCommand != null);
    } catch(RPNCalculatorException e){
      Assert.assertNotNull(e);
    }finally {
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testExecuteNormal() {
    AddCommand addCommand = new AddCommand();
    commandReceiver.addElement(new BigDecimal(5));
    commandReceiver.addElement(new BigDecimal(5));
    try {
      addCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(10).setScale(15, BigDecimal.ROUND_HALF_UP)));
    } catch(RPNCalculatorException e){
      Assert.assertFalse(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testExecuteInvalidOperand1() {
    AddCommand addCommand = new AddCommand();
    commandReceiver.addElement(null);
    commandReceiver.addElement(new BigDecimal(5));
    try {
      addCommand.execute();
      Assert.assertFalse(commandReceiver.getInternaDSSize() == 1);
    } catch(RPNCalculatorException e){
      Assert.assertTrue(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

  @Test
  public void testExecuteInvalidOperand2() {
    AddCommand addCommand = new AddCommand();
    commandReceiver.addElement(new BigDecimal(5));
    commandReceiver.addElement(null);
    try {
      addCommand.execute();
      Assert.assertFalse(commandReceiver.getInternaDSSize() == 1);
    } catch(RPNCalculatorException e){
      Assert.assertTrue(e != null);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }


  @Test
  public void testUndoNormal() {
    AddCommand addCommand = new AddCommand();
    commandReceiver.addElement(new BigDecimal(5));
    commandReceiver.addElement(new BigDecimal(5));
    try {
      addCommand.execute();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 1);
      Assert.assertTrue(commandReceiver.fetchElement().equals(new BigDecimal(10).setScale(15, BigDecimal.ROUND_HALF_UP)));
      addCommand.undo();
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
    AddCommand addCommand = new AddCommand();
    try {
      addCommand.execute();
    } catch(RPNCalculatorException e){
      Assert.assertTrue(e != null);
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 0);
      addCommand.undo();
      Assert.assertTrue(commandReceiver.getInternaDSSize() == 0);
    } finally{
      commandReceiver.clearUnderlyingState();
    }
  }

}
