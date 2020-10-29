package util;

public class Contador extends Thread{
    private static Contador clock;
    private int segundos;
    private int minutos;
    private int horas;

    private Contador() {
        this.segundos = 0;
        this.minutos = 0;
        this.horas = 0;
    }
    
    public static synchronized Contador getInstance(){
        if(clock == null){
            clock = new Contador();
            clock.start();
        }
        return clock;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Contador parou.");
                break;
            }
            segundos++;
            if(segundos==60){
                segundos = 0;
                minutos++;
            }
            if(minutos == 60){
                minutos = 0;
                horas++;
            }
        }
            
    }
    
    public int getAbsoluteTime(){
        return segundos;
    }
    
    public String getTime(){
        return ""+horas+"h - "+minutos+"min - "+segundos+"s";
    }
    
    
}