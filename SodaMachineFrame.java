/**
* This is the frame class that a soda machine class
* will use as its GUI.
*
* @author Logan Gardner
* @version Program 7
*/

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.EtchedBorder;
import java.text.NumberFormat;

public class SodaMachineFrame extends JFrame
{
   private SodaMachine sMachine;
   private JButton[] button;
   private JButton coinReturn = new JButton("Coin Return");
   private JTextField depositedTextField = new JTextField(6);
   private JTextField changeTextField = new JTextField(6);
   private JTextField salesTextField = new JTextField(6);
   
   public SodaMachineFrame(SodaMachine s)
   {
      sMachine = s;
      button = new JButton[sMachine.getCount()];
      
      CenterPanel center = new CenterPanel(s);
      EastPanel east = new EastPanel(center);
      WestPanel west = new WestPanel(east);
      
      setResizable(false);
      
      pack();
   }
   
   private class CenterPanel extends JPanel
   {
      private int totalSales = 0;
      
      
      private SodaMachine sodaMachine;
      
      public CenterPanel(SodaMachine s)
      {
         sodaMachine = s;
         JPanel centerPanel = makeCenterPanel(s);
         getContentPane().add(centerPanel, BorderLayout.CENTER);
      }
      
      private JPanel makeCenterPanel(SodaMachine s)
      {
         //middle top panel
         JPanel panel = new JPanel();
      
         panel.setPreferredSize(new Dimension(150,300));
         panel.setBorder(new EtchedBorder());
      
         panel.setLayout(new GridLayout(3,1));
         
         JPanel depositPanel = new JPanel();
         depositPanel.add(new JLabel("Amount Deposited", SwingConstants.CENTER));
         depositedTextField.setEditable(false);
         depositedTextField.setHorizontalAlignment(JTextField.RIGHT);
         depositPanel.add(depositedTextField);
         
         NumberFormat numForm = NumberFormat.getCurrencyInstance();
      
         depositedTextField.setText(numForm.format(s.getDeposits()));
         
         depositPanel.add(coinReturn);
         coinReturn.setEnabled(false);
         
         class CoinReturnHandler implements java.awt.event.ActionListener
         {
            public void actionPerformed(java.awt.event.ActionEvent event)
            {
               NumberFormat numForm = NumberFormat.getCurrencyInstance();
               
               int returns = sodaMachine.returnDeposits();
               
               coinReturn.setEnabled(false);
               changeTextField.setText(numForm.format( (double)returns/100 ));
               depositedTextField.setText(numForm.format( (double)sodaMachine.getDeposits()/100 ));
               
               int i;
               
               for(i=0;i<sMachine.getCount();i++)
               {
                  button[i].setEnabled(false);
               }
               
                  
            }
         }
         
         CoinReturnHandler coinReturnHandler = new CoinReturnHandler();
         coinReturn.addActionListener(coinReturnHandler);
         
         panel.add(depositPanel);
         
         
         //middle panel
         JPanel changeReturnedPanel = new JPanel();
      
         changeReturnedPanel.add(new JLabel("Change Returned", SwingConstants.CENTER));
      
         changeTextField.setHorizontalAlignment(JTextField.RIGHT);
         changeTextField.setEditable(false);
         changeTextField.setText(numForm.format(0));
         changeReturnedPanel.add(changeTextField);
      
         changeReturnedPanel.setBorder(new EtchedBorder());
         
         panel.add(changeReturnedPanel);
         
         
         //middle bottom panel
         JPanel totalSalesPanel = new JPanel();
      
         totalSalesPanel.add(new JLabel("Total Sales", SwingConstants.CENTER));
      
         salesTextField.setHorizontalAlignment(JTextField.RIGHT);
         salesTextField.setEditable(false);
         salesTextField.setText(numForm.format( (double)sMachine.getSales()/100 ));
         totalSalesPanel.add(salesTextField);
      
         totalSalesPanel.setBorder(new EtchedBorder());
         panel.add(totalSalesPanel);
         
         
         return panel;
      }
      
      
   }
   
   
   
   
   
   private class EastPanel extends JPanel
   {
      
      public EastPanel(CenterPanel c)
      {
         JPanel eastPanel = makeEastPanel(c);
         getContentPane().add(eastPanel, BorderLayout.EAST);
      }
      
      private JPanel makeEastPanel(CenterPanel c)
      {
         JPanel panel = new JPanel();
      
         panel.setPreferredSize(new Dimension(150,300));
         panel.setBorder(new EtchedBorder());
      
         panel.setLayout(new GridLayout(5,1));
         
         final JButton button1 = new JButton("Nickel");
         panel.add(button1);
      
         final JButton button2 = new JButton("Dime");
         panel.add(button2);
      
         final JButton button3 = new JButton("Quarter");
         panel.add(button3);
      
         final JButton button4 = new JButton("Half Dollar");
         panel.add(button4);
      
         final JButton button5 = new JButton("Dollar");
         panel.add(button5);
         
         
         
         
         
         class MoneyHandler implements java.awt.event.ActionListener
         {
            private NumberFormat numForm = NumberFormat.getCurrencyInstance();
            
            public void actionPerformed(java.awt.event.ActionEvent event)
            {
               int i;
                  
               if ( event.getSource() == button1 )
               {
                  sMachine.deposit(5);
                  changeTextField.setText(numForm.format(0));
                  coinReturn.setEnabled(true);
                  
                  if(sMachine.sufficientFunds())
                  {
                     for(i=0;i<sMachine.getCount();i++)
                     {
                        if ( sMachine.getSodaCount(i) == 0)
                        {
                           button[i].setEnabled(false);
                        }
                        else
                        {
                           button[i].setEnabled(true);
                        }
                     }
                  }
                  
               }
               else if ( event.getSource() == button2 )
               {
                  sMachine.deposit(10);
                  changeTextField.setText(numForm.format(0));
                  coinReturn.setEnabled(true);
                  
                  if(sMachine.sufficientFunds())
                  {
                     for(i=0;i<sMachine.getCount();i++)
                     {
                        if ( sMachine.getSodaCount(i) == 0)
                        {
                           button[i].setEnabled(false);
                        }
                        else
                        {
                           button[i].setEnabled(true);
                        }
                     }
                  }
                  
               }
               else if ( event.getSource() == button3 )
               {
                  sMachine.deposit(25); 
                  changeTextField.setText(numForm.format(0));
                  coinReturn.setEnabled(true);
                  
                  if(sMachine.sufficientFunds())
                  {
                    for(i=0;i<sMachine.getCount();i++)
                     {
                        if ( sMachine.getSodaCount(i) == 0)
                        {
                           button[i].setEnabled(false);
                        }
                        else
                        {
                           button[i].setEnabled(true);
                        }
                     }
                  }
                  
               }
               else if ( event.getSource() == button4 )
               {
                  sMachine.deposit(50);
                  changeTextField.setText(numForm.format(0));
                  coinReturn.setEnabled(true);
                  
                  if(sMachine.sufficientFunds())
                  {
                     for(i=0;i<sMachine.getCount();i++)
                     {
                        if ( sMachine.getSodaCount(i) == 0)
                        {
                           button[i].setEnabled(false);
                        }
                        else
                        {
                           button[i].setEnabled(true);
                        }
                     }
                  }
                  
               }
               else if ( event.getSource() == button5 )
               {
                  sMachine.deposit(100);
                  changeTextField.setText(numForm.format(0));
                  coinReturn.setEnabled(true);
                  
                  if(sMachine.sufficientFunds())
                  {
                     for(i=0;i<sMachine.getCount();i++)
                     {
                        if ( sMachine.getSodaCount(i) == 0)
                        {
                           button[i].setEnabled(false);
                        }
                        else
                        {
                           button[i].setEnabled(true);
                        }
                     }
                  }
                  
               }
            
               NumberFormat numForm = NumberFormat.getCurrencyInstance();
      
               depositedTextField.setText(numForm.format( (double)sMachine.getDeposits()/100 ));
            }
         }
         
         MoneyHandler moneyHandler = new MoneyHandler();
         button1.addActionListener(moneyHandler);
         button2.addActionListener(moneyHandler);
         button3.addActionListener(moneyHandler);
         button4.addActionListener(moneyHandler);
         button5.addActionListener(moneyHandler);
         
         return panel;
      }
      
      
   }
   
   
   
   
   private class WestPanel extends JPanel
   {
      
      public WestPanel(EastPanel e)
      {
         JPanel westPanel = makeWestPanel(e);
         getContentPane().add(westPanel, BorderLayout.WEST);
      }
      
      private JPanel makeWestPanel(EastPanel e)
      {
         
         
         JPanel panel = new JPanel();
         panel.setPreferredSize(new Dimension(150,300));
         panel.setBorder(new EtchedBorder());
         
         final int amountSodas = sMachine.getCount();
      
         panel.setLayout(new GridLayout(amountSodas,1));
      
         
         int i;
         for(i=0;i<amountSodas;i++)
         {
            button[i] = new JButton(sMachine.getSodaName(i));
            panel.add(button[i]);
            
            button[i].setEnabled(false);
         }
         
         
         
         class SodaHandler implements java.awt.event.ActionListener
         {
            private NumberFormat numForm = NumberFormat.getCurrencyInstance();
            
            public void actionPerformed(java.awt.event.ActionEvent event)
            {  
               coinReturn.setEnabled(false);
               
               int i;
               for(i=0;i<amountSodas;i++)
               {
                  button[i].setEnabled(false);
               }
               
               int change = 0;
               
               for(i=0;i<amountSodas;i++)
               {
                  if (event.getSource() == button[i])
                  {
                     change = sMachine.dispenseSoda(i);
                     
                  }
               }
               NumberFormat numForm = NumberFormat.getCurrencyInstance();
               salesTextField.setText(numForm.format((double)sMachine.getSales()/100));
               
               changeTextField.setText(numForm.format( (double)change/100 ));
               depositedTextField.setText(numForm.format( (double)sMachine.getDeposits()/100 ));
               
            }
         }
         
         SodaHandler sodaHandler = new SodaHandler();
         for(i=0;i<amountSodas;i++)
         {
            button[i].addActionListener(sodaHandler);
         }
         
         return panel;
      }
   }
}