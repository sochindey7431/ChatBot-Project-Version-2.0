# ChatBot Project Version 2.0

A console-based Java chatbot with user authentication and AI-powered responses via the OpenRouter API. Built with Object-Oriented Programming (OOP) principles.

## Features

- **User Registration & Login** — Sign up with email or phone number; credentials stored in `users.txt`
- **AI Chatbot** — Real-time responses powered by OpenRouter (GPT model)
- **OOP Design** — Interface-based bot service, inheritance (`SmartChatBot` extends `ChatBot`), and separate auth/user classes
- **Console UI** — Color-coded terminal output for a clean chat experience

## Screenshots

### Login & Registration

![Login Screen](https://github.com/sochindey7431/ChatBot-Project-Version-2.0/blob/a770616982ad86a4f8b02211e60d631d46ce487e/Screenshot%202026-06-11%20133658.png)

### Chat Interface

![Chat Screen](https://github.com/sochindey7431/ChatBot-Project-Version-2.0/blob/a770616982ad86a4f8b02211e60d631d46ce487e/Screenshot%202026-06-11%20134155.png)

## Project Structure

```
ChatBot-Project-Version-2.0/
├── Main.java          # Entry point — auth flow and chat loop
├── AuthService.java   # Register, login, and user file I/O
├── User.java          # User model
├── Admin.java         # Admin user (extends User)
├── BotService.java    # Bot interface
├── ChatBot.java       # OpenRouter API integration
├── SmartChatBot.java  # Extended chatbot implementation
├── users.txt          # Stored user credentials
└── assets/            # Screenshots
```

## Requirements

- Java JDK 8 or higher
- OpenRouter API key ([openrouter.ai](https://openrouter.ai))

## Setup

1. **Clone the repository**

   ```bash
   git clone https://github.com/sochindey7431/ChatBot-Project-Version-2.0.git
   cd ChatBot-Project-Version-2.0
   ```

2. **Add your API key**

   Open `ChatBot.java` and replace `Api-key-here` with your OpenRouter API key:

   ```java
   private static final String API_KEY = "your-openrouter-api-key";
   ```

3. **Compile and run**

   ```bash
   javac *.java
   java Main
   ```

## Usage

1. Choose **Register** to create an account, or **Login** with existing credentials.
2. After login, type messages to chat with the bot.
3. Type `bye` to exit the chat.

## Tech Stack

| Component   | Technology              |
|------------|-------------------------|
| Language   | Java                    |
| AI API     | OpenRouter              |
| Storage    | Plain text (`users.txt`)|
| Paradigm   | OOP (interfaces, inheritance) |

## Author

[sochindey7431](https://github.com/sochindey7431)

## License

This project is open source and available for educational use.
