package com.jap.ticketing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class TicketAnalyzer {
    public List<Ticket> readFile(String filename) {
        List<Ticket> tickets = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(",");
                String schedule_no = strings[0];
                String route_no = strings[1];
                int     ticket_from_stop_id=Integer.parseInt(strings[2]);
                int     ticket_from_stop_seq_no=Integer.parseInt(strings[3]);
                int     ticket_till_stop_id=Integer.parseInt(strings[4]);
                int     ticket_till_stop_seq_no=Integer.parseInt(strings[5]);
                String  ticket_date=strings[6];
                String  ticket_time=strings[7];
                double  total_ticket_amount=Double.parseDouble(strings[8]);
                double  travelled_KM=Double.parseDouble(strings[9]);


                tickets.add(new Ticket(schedule_no,route_no,ticket_from_stop_id,ticket_from_stop_seq_no,ticket_till_stop_id,ticket_till_stop_seq_no,ticket_date,ticket_time,total_ticket_amount,travelled_KM));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    public List<Ticket> sortDataByDistance(List<Ticket> tickets) {
        tickets.sort((o1, o2) -> {
            if (o1.getTravelled_KM() == o2.getTravelled_KM()) {
                return 0;
            } else if (o1.getTravelled_KM() < o2.getTravelled_KM()) {
                return 1;
            } else {

                return -1;
            }

        });

        return tickets;
    }

    public double CollectionAmount(List<Ticket> ticket){

        CollectionAmountCalculate collectionAmountCalculate = ticket1 -> {
            int sum=0;
            Iterator<Ticket>ticketIterator= ticket1.iterator();
            while (ticketIterator.hasNext()){
                Ticket ticket2=ticketIterator.next();
                sum+=ticket2.getTotal_ticket_amount();

            }
            return sum;
        };
        return collectionAmountCalculate.CollectionAmount(ticket);
    }

    public static void main(String[] args) {
        TicketAnalyzer ticketAnalyzer=new TicketAnalyzer();
        String filename = "src/main/resources/sample.csv";
        List<Ticket> list = ticketAnalyzer.readFile(filename);
        System.out.println("-----------------------");
        for (Ticket ticket : list){
            System.out.println(ticket);
        }
        System.out.println(" sorting");
        List<Ticket> list2 = ticketAnalyzer.sortDataByDistance(list);
        for (Ticket ticket : list2){
            System.out.println(ticket);
        }
        System.out.println(" Collection amount = " + ticketAnalyzer.CollectionAmount(list));

        }
    }

