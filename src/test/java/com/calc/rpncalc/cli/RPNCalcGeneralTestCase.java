package com.calc.rpncalc.cli;

import com.calc.rpncalc.cli.controller.CLIController;
import com.calc.rpncalc.cli.exception.RPNCalculatorException;
import com.calc.rpncalc.cli.receiver.factory.CommandReceiverFactory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;

/**
 * Created by sundas on 4/17/2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RPNCalcGeneralTestCase {


  @Test
  public void processASimpleRowSanityTest()throws RPNCalculatorException{
    CLIController cliController = CLIController.getCLIController();
    Assert.assertTrue(cliController != null);
    String row = "5 2";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 2);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().fetchElement().equals(new BigDecimal(2).setScale(15,BigDecimal.ROUND_HALF_UP)));
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().fetchElement().equals(new BigDecimal(5).setScale(15,BigDecimal.ROUND_HALF_UP)));
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 0);
  }


  @Test
  public void processBSqrtSanityTest()throws RPNCalculatorException{
    CLIController cliController = CLIController.getCLIController();
    Assert.assertTrue(cliController != null);
    String row = "2 sqrt";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 1);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().fetchElement().equals(new BigDecimal(1.414213562373095).setScale(15,BigDecimal.ROUND_HALF_UP)));
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 0);
    row = "9 sqrt";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 1);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().fetchElement().equals(new BigDecimal(3).setScale(15,BigDecimal.ROUND_HALF_UP)));
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 0);
  }


  @Test
  public void processCMinusSanityTest()throws RPNCalculatorException{
    CLIController cliController = CLIController.getCLIController();
    Assert.assertTrue(cliController != null);
    String row = "5 2 -";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 1);
    row = "3 -";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 1);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().fetchElement().equals(new BigDecimal(0).setScale(15,BigDecimal.ROUND_HALF_UP)));
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 0);
  }


  @Test
  public void processDClearSanityTest() throws RPNCalculatorException{
    CLIController cliController = CLIController.getCLIController();
    Assert.assertTrue(cliController != null);
    String row = "5 2 -";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 1);
    row = "7 6 5";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 4);
    row = "clear";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 0);
  }


  @Test
  public void processEUndoSanityTest()throws RPNCalculatorException{
    CLIController cliController = CLIController.getCLIController();
    Assert.assertTrue(cliController != null);
    String row = "5 4 3 2";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 4);
    row = "undo undo *";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 1);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().fetchElement().equals(new BigDecimal(20).setScale(15, BigDecimal.ROUND_HALF_UP)));
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 0);
    row = "20 5 *";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 1);
    row = "undo";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 2);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().fetchElement().equals(new BigDecimal(5).setScale(15, BigDecimal.ROUND_HALF_UP)));
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().fetchElement().equals(new BigDecimal(20).setScale(15, BigDecimal.ROUND_HALF_UP)));
  }


  @Test
  public void processFDivisionMultSanityTest()throws RPNCalculatorException{
    CLIController cliController = CLIController.getCLIController();
    Assert.assertTrue(cliController != null);
    String row = "7 12 2 /";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 2);
    row = "*";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 1);
    row = "4 /";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 1);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().fetchElement().equals(new BigDecimal(10.5).setScale(15, BigDecimal.ROUND_HALF_UP)));
  }

  @Test
  public void processGMultMinusSanityTest()throws RPNCalculatorException{
    CLIController cliController = CLIController.getCLIController();
    Assert.assertTrue(cliController != null);
    String row = "1 2 3 4 5";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 5);
    row = "*";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 4);
    row = "clear 3 4 -";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 1);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().fetchElement().equals(new BigDecimal(-1).setScale(15, BigDecimal.ROUND_HALF_UP)));
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 0);
  }

  @Test
  public void processHMultSanityTest()throws RPNCalculatorException{
    CLIController cliController = CLIController.getCLIController();
    Assert.assertTrue(cliController != null);
    String row = "1 2 3 4 5";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 5);
    row = "* * * *";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 1);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().fetchElement().equals(new BigDecimal(120).setScale(15, BigDecimal.ROUND_HALF_UP)));
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 0);
  }


  @Test
  public void processIInsuffParametersSanityTest()throws RPNCalculatorException{
    CLIController cliController = CLIController.getCLIController();
    Assert.assertTrue(cliController != null);
    String row = "1 2 3 * 5 + * * 6 5";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 1);
    BigDecimal top = (BigDecimal) CommandReceiverFactory.getDefaultReceiver().fetchElement();
    Assert.assertFalse(top.equals(new BigDecimal(6).setScale(15, BigDecimal.ROUND_HALF_UP)));
    Assert.assertFalse(top.equals(new BigDecimal(5).setScale(15, BigDecimal.ROUND_HALF_UP)));
    Assert.assertTrue(top.equals(new BigDecimal(11).setScale(15, BigDecimal.ROUND_HALF_UP)));
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 0);
  }

  @Test
  public void processJInvalidInputSanityTest()throws RPNCalculatorException{
    CLIController cliController = CLIController.getCLIController();
    Assert.assertTrue(cliController != null);
    String row = "1 2 3 $%$%##$#$#%# %$%^#^#^###^%#*#%^#&& %^&&^%^& 6";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 4);
    CommandReceiverFactory.getDefaultReceiver().clearUnderlyingState();
  }


  @Test
  public void processKInvalidCommandInputSanityTest()throws RPNCalculatorException{
    CLIController cliController = CLIController.getCLIController();
    Assert.assertTrue(cliController != null);
    String row = "1 UNDO1";
    cliController.processRow(row);
    Assert.assertTrue(CommandReceiverFactory.getDefaultReceiver().getInternaDSSize() == 1);
    CommandReceiverFactory.getDefaultReceiver().clearUnderlyingState();
  }

}