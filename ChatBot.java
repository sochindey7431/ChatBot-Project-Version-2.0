import java.net.*;
import java.io.*;

public class ChatBot implements BotService {

    private static final String API_KEY = "Api-key-here";

    
    @Override
    public String getResponse(String input) {
        return getAIResponse(input);
    }

    public String getResponse() {
        return "Hello! Ask me something";
    }

    private String getAIResponse(String input) {
        try {
            URL url = new URL("https://openrouter.ai/api/v1/chat/completions");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            input = input.replace("\"", "\\\"");

            String jsonInput = "{"
                    + "\"model\":\"openai/gpt-oss-120b:free\","
                    + "\"messages\":["
                    + "{\"role\":\"system\",\"content\":\"You are a friendly chatbot.\"},"
                    + "{\"role\":\"user\",\"content\":\"" + input + "\"}"
                    + "]"
                    + "}";

            OutputStream os = conn.getOutputStream();
            os.write(jsonInput.getBytes("utf-8"));
            os.close();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8")
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }

            br.close();

            String result = response.toString();

            int start = result.indexOf("\"content\":\"") + 11;
            int end = result.indexOf("\"", start);

            if (start < 11 || end == -1) {
                return "Sorry, I couldn't understand";
            }

            return result.substring(start, end)
                    .replace("\\n", "\n")
                    .replace("\\\"", "\"");

        } catch (Exception e) {
            return "Error: API failed";
        }
    }
}