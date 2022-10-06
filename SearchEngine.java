import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    string[25] library = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Library size: %d", library.length());
        } 
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] search = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    return String.format("%s added to library! There are now %d words!", parameters[1], num);;
                }
            }
            else if (url.getPath().contains("/search")) {
                String[] search = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    return String.format("Number increased by %s! It's now %d", parameters[1], num);
                }
                return library;
            }
            return "404 Not Found!";
        }
    }
}

class NumberServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
