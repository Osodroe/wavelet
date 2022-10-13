import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    List<String> library = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            String StringOut = String.join(", ", library);
            return String.format("Current library: %s", StringOut);
        } 
        else if (url.getPath().equals("/search")) {
            System.out.println("Path: " + url.getPath());
            String[] parameters = url.getQuery().split("=");
            List<String> buffer = new ArrayList<String>();
            if (parameters[0].equals("s")) {
                for (int i = 0; i < library.size(); i += 1)
                    if(library.get(i).contains(parameters[1]))
                        buffer.add(library.get(i));
                String StringOut = String.join(", ", buffer);
                return String.format("Searched library: %s", StringOut);
            }
        } 
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    library.add(parameters[1]);
                    return String.format("%s added to library!", parameters[1]);
                }
            }
        }
        return "404 Not Found!";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
