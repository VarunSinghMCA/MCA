// INSIDE FOLDER - VMM

// VMM/interfaces/vehicle
// package VMM.interfaces
interface Vehicle{
    void start(int routeId, int totalStops, String route, int driverId, String driverName);
    void stop(int routeId);
}

// VMM/campus/tranport.java
// package VMM.campus

// import VMM.interface.vehicle
public class Transport implements Vehicle{
    @Override
    public void start(int routeId,int totalRoutes,String routes,int did, String dname,){
        System.out.println("Trasport STARTING from Route "+routeId+": "+routes+"\n Driver Details: \n DriverID:"+did+"\n Driver Name:"+dname);
    }
    @Override
    public void stop(int routeId){
        System.out.println("Tranporting Stopped for the Route "+routeId);
    }
    
    public String CompletedTranport(
            int routeId,int totalRoutes,String routes,
            int did, String dname,
        ){
        String a = "Trasport COMPLETED for Route "+routeId+": "+routes+"\n Driver Details: \n DriverID:"+did+"\n Driver Name:"+dname;
        return a;
    };
}

// VMM/campus/route.java
// package VMM.campus

// import VMM.interface.vehicle
public class Route {
    private int routeId;
    private int totalStops;
    private String route;

    public Route(int routeId, int totalStops, String route) {
        this.routeId = routeId;
        this.totalStops = totalStops;
        this.route = route;
    }

    public int getRouteId() { return routeId; }
    public int getTotalStops() { return totalStops; }
    public String getRoute() { return route; }
}

// VMM/campus/driver.java
// package VMM.campus

// import VMM.interface.vehicle
public class Driver {
    private int driverId;
    private String driverName;

    public Driver(int id, String name) {
        this.driverId = id;
        this.driverName = name;
    }

    public int getDriverId() { return driverId; }
    public String getDriverName() { return driverName; }
}


// import VMM.interface.vehicles    

// VMM/main/main.java
// package VMM.main

// import VMM.campus.route;
// import VMM.campus.driver;
// import VMM.campus.transport;

// filehandler class
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.util.List;
// import java.nio.file.StandarOpenOption;

// lamda interface
@FunctionalInterface
interface SortestRouteInterface{
    String checkSortRoute(Route r1, Route r2);
}

// class FileHandler{
//     public void readFile(String dir) throws IOException{
//         List<String> lines = Files.readAllLines(Path.of(dir));
//         for(String line:lines){
//             System.out.println(line);
//         }
//     }
//     public void writeFile(String dir, String Content) throws IOException{
//         Files.writeString(Path.of(dir),content);
//     }
//     public void appendContent(String dir, String content) throws IOException{
//         Files.writeString(
//             Path.of(dir), 
//             content,
//             StandarOpenOption.CREATE, 
//             StandarOpenOption.APPEND
//         );
//     }
//     public void read(String dir){
//         try{
//             readFile(dir);
//         } catch ( IOException e ){
//             System.out.println("IO Exception\n"+e);
//         } catch (Exception e){
//             System.out.println("Errors\n"+e);
//         }
//     }
//     public void write(String dir, String content){
//         try{
//             writeFile(dir, content);
//         } catch (IOException e){
//             System.out.println("IO Exception\n"+e);
//         } catch (Exception e){
//             System.out.println("Errors\n"+e);
//         }
//     }
//     public void append(String dir, String content){
//         try{
//             appendContent(dir, content);
//         } catch (FileNotFoundException e){
//             System.out.println("File not found\n"+e);
//         } catch ( IOException e ){
//             System.out.println("IO Exception\n"+e);
//         } catch (Exception e){
//             System.out.println("Errors\n"+e);
//         }
//     }
    
// }

// TransportUnit Class -> main (Renamed)
public class A implements SortestRouteInterface{
    
public static void main(String[] args){
    SortestRouteInterface checkSortRoute = (R1, R2) -> {
            int dif;
            int id1 = R1.routeId;
            int id2 = R2.routeId;
            int t1 = R1.totalStops;
            int t2 = R2.totalStops;
            String r1 = R1.routes;
            String r2 = R2.routes;
            if (t1<t2){
                dif = t2 - t1;
                return("Route "+id1+": is sorter than Route "+id2+" by "+dif+" routes."+"\nRoute to take:"+r1);
            }
            dif = t1 - t2;
            return ("Route "+id2+": is sorter than Route "+id1+" by "+dif+" routes."+"\nRoute to take:"+r2);
        }
    
        Driver d1 = new driver(101,"David");
        Route r1 = new route(1,3,"Main Gate -> Liberary -> Hostel");
        Route r2 = new route(2,4,"Main Gate -> Building No. 4 -> Liberary -> Hostel");
        
        Vehicle t1 = new Transport();
        t1.start(r1.getRouteId(), r1.getotalRoutes(), r1.getRoutes(),
                d1.getDriverId(), d1.getDriverName());
        t1.stop(r1.getRouteId());
        t1.start(r1.getRouteId(), r1.getotalRoutes(), r1.getRoutes(),
                d1.getDriverId(), d1.getDriverName());
        fileHandler f = new fileHandler();
        f.append("./sample.txt",t1.CompletedTranport(
            r1.getRouteId(), 
            r1.getotalRoutes(), 
            r1.getRoutes(),
            d1.getDriverId(), 
            d1.getDriverName()
        ));
        System.out.println("Sortest Route to take"+checkSortRoute(r1,r2));
    }
}