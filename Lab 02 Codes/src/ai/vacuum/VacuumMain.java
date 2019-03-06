package ai.vacuum;
import java.util.Scanner;
import java.util.ArrayList;
public class VacuumMain {
        public static void main(String[] args) {
            Scanner input=new Scanner(System.in);
            int rows,cols,dirt;
            ArrayList<Integer> rDirt = new ArrayList<Integer>();
            ArrayList<Integer> cDirt = new ArrayList<Integer>();
            
            System.out.println("Enter the number of Rows: ");
            rows = input.nextInt();
            System.out.println("Enter the number of Columns: ");
            cols = input.nextInt();
            System.out.println("Enter the number of Dirty cells: ");
            dirt = input.nextInt();
            
            Environment env = new Environment(rows,cols,dirt);
            
            //Scan for dirt
            for(int i=0;i<rows;i++){
                if(i!=0 ){
                    env.moveDown();
                }
                for(int j=0;j<cols;j++){
                    boolean x = env.isDirty();
                    if(x==true){
                        rDirt.add(env.getRow());
                        cDirt.add(env.getColumn());
                    }
                    
                    if(j!=(cols-1)){
                    
                        if(i==0 || i%2==0){
                            env.moveRight();
                        }
                        else{
                            env.moveLeft();
                        }
                }
                }
            }
            
            env.reset();
            System.out.println("\nEnvironment before cleaning:");
            System.out.println(env.toString());

            System.out.println("\nLocations of Dirt:");
            for(int i=0;i<rDirt.size();i++){
                System.out.println("("+rDirt.get(i)+","+cDirt.get(i)+")");
            }
            
            
            //Clean dirt
            for(int i=0;i<rows;i++){ 
               if(!rDirt.isEmpty()){ 
                for(int d=0;d<rDirt.size();d++){
                    if(rDirt.get(d)==i){
                        int moves=cDirt.get(d)-env.getColumn();
                        if(moves==0){
                            env.suck();
                            System.out.println("\nClean:");
                            System.out.println(env.toString());
                        }
                        else if(moves>0){
                            for(int j=0;j<moves;j++){
                            env.moveRight();
                            System.out.println("\nMove Right:");
                            System.out.println(env.toString());
                            }
                            env.suck();
                            System.out.println("\nClean:");
                            System.out.println(env.toString());
                        }
                        else if(moves<0){
                            for(int j=0;j>moves;j--){
                            env.moveLeft();
                            System.out.println("\nMove Left:");
                            System.out.println(env.toString());
                            }
                            env.suck();
                            System.out.println("\nClean:");
                            System.out.println(env.toString());
                        }
                    rDirt.remove(d);
                    cDirt.remove(d);
                    d--;
                    }
                }
            }
               else{
                   break;
               }    
                
               if(i!=rows-1 && !rDirt.isEmpty()){
                   env.moveDown();
                   System.out.println("\nMove down:");
                   System.out.println(env.toString());
               }
            }
            
            System.out.println("\nEnvironment after cleaning:");
            System.out.println(env.toString());
            System.out.println("Score: "+env.getScore());
        }
}
