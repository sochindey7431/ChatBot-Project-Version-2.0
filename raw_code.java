import java.util.*;
import java.time.*;

public class raw_code {

    private static String userName = "";
    private static String lastMood = "";
    private static final Random random = new Random();


    private static final String[] GREET_RESPONSES = {
            "Hey %s! Great to see you!",
            "Hello %s! How's it going?",
            "Hi %s! Ready to chat?",
            "Hey there, %s! What's up?"
    };

    private static final String[] TIRED_RESPONSES = {
            "You sound tired. Take a short break — you deserve it!",
            "Rest is productive too! A 10-minute nap can do wonders.",
            "Don't forget: sleep helps your brain recharge!",
            "Grab some water or stretch a little!"
    };

    private static final String[] STUDY_RESPONSES = {
            "Try the Pomodoro technique — 25 mins focus, 5 mins break!",
            "Consistency beats cramming every time.",
            "Teach the topic to someone else — it boosts memory!",
            "Make mind maps to visualize concepts."
    };

    private static final String[] FALLBACK = {
            "Hmm, I didn't understand that.",
            "Try saying 'help' to see what I can do!",
            "Interesting... tell me more!"
    };

    private static final Set<String> MOOD_WORDS = new HashSet<>(Arrays.asList(
            "tired","exhausted","sleepy","stressed","burnout","overwhelmed",
            "sad","happy","angry","excited","bored"
    ));

  
    private static String pick(String[] arr) {
        return arr[random.nextInt(arr.length)];
    }

    private static String nameTag() {
        return userName.isEmpty() ? "friend" : userName;
    }

    private static String capitalize(String s) {
        if (s.isEmpty()) return s;
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }

   
    private static String extractName(String originalInput) {

        String input = originalInput.toLowerCase();
        String[] patterns = {"my name is ", "call me "};

        for (String p : patterns) {
            if (input.contains(p)) {
                String namePart = originalInput.substring(
                        input.indexOf(p) + p.length()
                ).replaceAll("[^a-zA-Z ]","").trim();

                if (namePart.isEmpty()) return "";

                String lower = namePart.toLowerCase();

                // Reject if mood words inside
                for (String mood : MOOD_WORDS) {
                    if (lower.contains(mood)) return "";
                }

                String[] words = namePart.split(" ");
                if (words.length <= 2) {
                    StringBuilder finalName = new StringBuilder();
                    for (String w : words) {
                        if (!w.isEmpty())
                            finalName.append(capitalize(w)).append(" ");
                    }
                    return finalName.toString().trim();
                }
            }
        }
        return "";
    }

    // ─────────────────────────────────────────────
    private static String timeGreeting() {
        int hour = LocalTime.now().getHour();

        if (hour >= 5 && hour < 12)
            return "Good morning, " + nameTag() + "!";
        if (hour >= 12 && hour < 17)
            return "Good afternoon, " + nameTag() + "!";
        if (hour >= 17 && hour < 21)
            return "Good evening, " + nameTag() + "!";
        return "Good night, " + nameTag() + "!";
    }


    public static String getResponse(String userInput) {

        String input = userInput.toLowerCase().trim();

        if (input.matches(".*\\b(bye|exit|quit|goodbye)\\b.*"))
            return "Goodbye, " + nameTag() + "! Take care!";

        String detected = extractName(userInput);
        if (!detected.isEmpty()) {
            userName = detected;
            return "Nice to meet you, " + userName + "!";
        }


        if (input.matches(".*\\b(hi|hello|hey|yo|sup)\\b.*"))
            return String.format(pick(GREET_RESPONSES), nameTag());


        if (input.contains("how are you")) {
            return "I'm doing great! How about you, " + nameTag() + "?";
        }

        if (input.contains("i am fine") || input.contains("i'm fine") ||
            input.contains("i am good") || input.contains("i'm good") ||
            input.contains("i am okay") || input.contains("i'm okay")) {

            return "That's great to hear, " + nameTag() + "!";
        }

       if (input.contains("i am not fine") || input.contains("i'm not fine") ||
           input.contains("i am bad") || input.contains("i'm bad")||
           input.contains("i am not good") || input.contains("i'm not good")) {

             return "I'm sorry to hear that, " + nameTag() + ". Want to talk about it?";
      }

        int hour = LocalTime.now().getHour();


       if (input.contains("good morning")) {
           if (hour >= 5 && hour < 12)
               return "Good morning, " + nameTag() + "!";
           else if (hour >= 12 && hour < 17)
               return "It's not morning, friend. It's afternoon (noon) now!";
           else if (hour >= 17 && hour < 21)
               return "It's not morning, friend. It's evening now!";
           else
                return "It's not morning, friend. It's night now!";
       }

       if (input.contains("good noon") || input.contains("good afternoon")) {
           if (hour >= 12 && hour < 17)
               return "Good afternoon, " + nameTag() + "!";
           else if (hour >= 5 && hour < 12)
               return "It's actually morning now, friend!";
           else if (hour >= 17 && hour < 21)
               return "It's evening now, friend!";
           else
               return "It's night now, friend!";
       }

       if (input.contains("good evening")) {
           if (hour >= 17 && hour < 21)
               return "Good evening, " + nameTag() + "!";
           else if (hour >= 5 && hour < 12)
               return "It's morning now, friend!";
           else if (hour >= 12 && hour < 17)
               return "It's afternoon (noon) now, friend!";
           else
               return "It's night now, friend!";
       }


       if (input.contains("good night")) {
           if (hour >= 21 || hour < 5)
               return "Good night, " + nameTag() + "! Sleep well and sweet dreams!";
           else if (hour >= 17 && hour < 21)
               return "Not night yet, friend. It's evening now!";
           else if (hour >= 12 && hour < 17)
               return "It's afternoon now, not night!";
           else
               return "It's morning now, friend!";
    }
        if (input.contains("time") || input.contains("what time") || input.contains("current time")) {

    LocalTime now = LocalTime.now();

    int hour1 = now.getHour();
    int minute = now.getMinute();

    String ampm = (hour1 >= 12) ? "PM" : "AM";

    int hour12 = hour1 % 12;
    if (hour12 == 0) hour12 = 12;

    return "Current time is " + hour12 + ":" +
            String.format("%02d", minute) + " " + ampm +
            ", " + nameTag();
}

   
        if (input.contains("study") || input.contains("exam") || input.contains("assignment"))
            return pick(STUDY_RESPONSES);

     
        if (input.contains("what is your name") || input.contains("who are you"))
            return "I'm your friendly chatbot! I don't have a name, but you can call me ChatBot!";

        for (String mood : MOOD_WORDS) {
            if (input.contains(mood)) {
                lastMood = mood;

                if (mood.equals("tired") || mood.equals("sleepy"))
                    return pick(TIRED_RESPONSES);

                if (mood.equals("sad"))
                    return "I'm sorry you're feeling sad, " + nameTag() + ". Things will improve!";

                if (mood.equals("happy"))
                    return "That's awesome, " + nameTag() + "! Keep smiling!";

                return "I see you're feeling " + mood + ", " + nameTag() + ". Want to talk about it?";
            }
        }

        
        if (input.contains("better") && !lastMood.isEmpty())
            return "I'm glad you're feeling better than before, " + nameTag() + "!";

        
        if (input.contains("thank"))
            return "You're welcome, " + nameTag() + "!";

        if (input.contains("help"))
            return "I can chat, remember your mood, give study tips, and more!";

        return pick(FALLBACK);
    }

        public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("╔══════════════════════════════╗");
        System.out.println("║         Normal ChatBot       ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println();

        System.out.print("Bot: Hey, What's your name? ");
        String first = sc.nextLine();

        String name = extractName(first);
        if (!name.isEmpty()) {
            userName = name;
        } else {
            userName = capitalize(first.replaceAll("[^a-zA-Z]",""));
        }

        if (!userName.isEmpty())
            System.out.println("Bot: Nice to meet you, " + userName + "! ");
        else
            System.out.println("Bot: No problem! ");

        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine();

            if (input.trim().isEmpty()) continue;

            String response = getResponse(input);
            System.out.println("Bot: " + response);

            if (input.toLowerCase().matches(".*\\b(bye|exit|quit|goodbye)\\b.*"))
                break;
        }

        sc.close();
    }
}