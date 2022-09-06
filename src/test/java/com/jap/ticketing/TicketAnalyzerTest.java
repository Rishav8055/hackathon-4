package com.jap.ticketing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TicketAnalyzerTest {
    TicketAnalyzer ticketAnalyzer =null;
    Ticket ticket;
    String fileName="src/main/resources/sample.csv";

    @Before
    public void setUp(){
        ticketAnalyzer=new TicketAnalyzer();
    }
    @After
    public void tearDown(){
        ticketAnalyzer=null;
        ticket=null;
    }
    @Test
    public void giveWrongDataThrowsNumberFormatException(){
        ticketAnalyzer.readFile(fileName);
    }
    @Test
    public void readFile(){
        List<Ticket> output =ticketAnalyzer.readFile(fileName);
        assertEquals("sales record",49,output.size());
    }
    @Test
    public void sortDataByDistance(){
        List<Ticket> output =ticketAnalyzer.readFile(fileName);
        assertEquals(49.5,ticketAnalyzer.sortDataByDistance(output).get(0).getTravelled_KM(),0);

    }
    @Test
    public  void CollectionAmount(){
        List<Ticket> output =ticketAnalyzer.readFile(fileName);
        assertEquals(10348.0,ticketAnalyzer.CollectionAmount(output),0);
    }

}
