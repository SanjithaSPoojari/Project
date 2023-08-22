import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class BusSeat{
    private int seatNumber;
    private String passengerName;

    public BusSeat(int seatNumber){
        this.seatNumber=seatNumber;
        this.passengerName=""; 
    }

    public int getSeatNumber(){
        return seatNumber;
    }

    public String getPassengerName(){
        return passengerName;
    } 

    public void reserveSeat(String passengerName){
        if(this.passengerName==""){
            this.passengerName=passengerName;
            System.out.println("Seat "+seatNumber+" has been reserved for "+passengerName);
            System.out.println("Booking is confirmed");
        }
        else{
            System.out.println("Sorry,seat "+seatNumber+" is already reserved");
        }
    }

    public void cancelSeat(){
        this.passengerName="";
        System.out.println("Seat "+seatNumber+" has been cancelled");
    }
}

class BusReservationSystem{
    ArrayList<BusSeat>seats=new ArrayList<BusSeat>();
    public BusReservationSystem(){
        for(int i=1;i<=24;i++){
            seats.add(new BusSeat(i));
            
        }
    }

    public void reserveSeat(int seatNumber, String passengerName){
        BusSeat seat=getSeatByNumber(seatNumber);
        if(seat!=null){
            seat.reserveSeat(passengerName);
        }
        else{
           System.out.println(("Invalid seat number"));
        }
    }

    public void cancelSeat(int seatNumber){
        BusSeat seat=getSeatByNumber(seatNumber);
        if(seat!=null){
            seat.cancelSeat();
            
        }
        else{
            System.out.println("Invalid seat number");
        }
    }

    public void displaySeatMap(){
        System.out.println("Bus seat map:");
        for(BusSeat seat : seats){
            if(seat.getPassengerName()==""){
                System.out.println("Seat "+seat.getSeatNumber()+":Available");
            }
            else{
                System.out.println("Seat "+seat.getSeatNumber()+":"+seat.getPassengerName());
            }
        }
    }

    private BusSeat getSeatByNumber(int seatNumber){
        for(BusSeat seat:seats){
            if(seat.getSeatNumber()==seatNumber){
                return seat;
            }
        }
        return null;
    }

    public void saveSeatMapToFile(String fileName){
        try{
            FileWriter writer=new FileWriter(fileName);
            for(BusSeat seat:seats){
                writer.write("Seat "+seat.getSeatNumber()+": "+seat.getPassengerName()+"\n");
            }
            writer.close();
            System.out.println("Seat map have been saved to "+fileName);
        }
        catch(IOException e){
            System.out.println("An error occured while saving the seat map");
        }
    }
}

public class Bus {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            BusReservationSystem reservationSystem=new BusReservationSystem();
            while(true){
                System.out.println("1. Reserve a seat");
                System.out.println("2. Cancel a seat");
                System.out.println("3. Display a seat");
                System.out.println("4. Save seat map to file");
                System.out.println("5. Exit");
                System.out.println("Enter your choice: ");
                int choice=scanner.nextInt();
                switch(choice){
                    case 1 : System.out.println("Enter seat number(1-24):");
                            int seatNumber=scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter passenger name");
                            String passengerName=scanner.nextLine();
                            
                            reservationSystem.reserveSeat(seatNumber, passengerName);
                            break;

                    case 2 : System.out.println("Enter seat number(1-24): ");
                            seatNumber=scanner.nextInt();
                            reservationSystem.cancelSeat(seatNumber);
                            break;

                    case 3 : reservationSystem.displaySeatMap();
                            break;

                    case 4 : System.out.println("Enter file name: ");
                            String fileName=scanner.next();
                            reservationSystem.saveSeatMapToFile(fileName);
                            break;

                    case 5 : System.out.println("Thank you!");
                            System.exit(0);
                            break;

                    default: System.out.println("Invlaid choice. Please try again");
                }
            }
        }
    }
}
