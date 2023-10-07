import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
public class Main {
    public static void main(String[] args) throws Exception {
        // Username input
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter username: ");
        String username = inputReader.readLine();
        URL usernameLink = new URL("https://www.ecs.soton.ac.uk/people/"+username);

        // Getting page data
        BufferedReader webContent = new BufferedReader(new InputStreamReader(usernameLink.openStream()));


        // Defining variables for filtering
        boolean foundUser = false;
        String userNotFoundTitle = "People | Electronics and Computer Science | University of Southampton";

        String inputLine;
        while ((inputLine = webContent.readLine()) != null) {
            if (inputLine.contains("<title>") && !inputLine.contains(userNotFoundTitle)) {

                inputLine = inputLine.trim();
                int[] indexes = {(inputLine.indexOf("<title>") + "<title>".length()), inputLine.indexOf(" | University of Southampton")};

                String outputLine = inputLine.substring(indexes[0],indexes[1]);

                foundUser = true;
                System.out.println(outputLine);
                webContent.close();
                break;
            }
        }


        if (!foundUser) {
            System.out.println("User not found");
        }
    }
}