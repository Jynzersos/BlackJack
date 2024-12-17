# Blackjack Game

This is a simple implementation of the **Blackjack** card game using **Java**. It allows multiple players to compete against a dealer, where the goal is to get as close to 21 points as possible without going over.

## Table of Contents

- [Introduction](#introduction)
- [How to Play](#how-to-play)
- [Game Flow](#game-flow)
- [Game Features](#game-features)
- [Technologies Used](#technologies-used)
- [License](#license)

## Introduction

**Blackjack** is a popular card game typically played at casinos. The game is also known as **21** and is played with one or more decks of cards. Each card has a point value:
- Cards **2-10** are worth their face value.
- **Face cards** (Jack, Queen, King) are worth **10 points**.
- **Ace** can be worth **1** or **11**, depending on the player's hand.

The goal of the game is to have a hand total as close to 21 points as possible without exceeding it. If a player's hand exceeds 21, they "bust" and lose the game. The game is played against a dealer, and the player who has the highest hand value without busting wins.

## How to Play

1. **Number of Players**: You can have **2 to 8 players** in a game.
2. **Dealing Cards**: Each player is dealt two cards, and the dealer also receives two cards, but one card is hidden until the dealer's turn.
3. **Turn Sequence**: Each player, including the dealer, takes turns to either:
    - **Hit**: Draw a new card to increase the hand value.
    - **Stand**: Stop drawing cards and keep the current hand.
4. **Dealer's Rules**: The dealer must continue drawing cards until they have at least 17 points.
5. **Winning**: The player with the highest hand value that does not exceed 21 points wins. If the dealer busts, all remaining players win.

## Game Flow

1. **Player Setup**:
   - The game first asks how many players will participate (between 2 and 8 players).
   - Players then input their names.
   - The dealer is selected from one of the players.
   
2. **Initial Deal**:
   - Each player and the dealer are dealt two cards each.
   - The dealer’s second card is hidden until the dealer’s turn.

3. **Player's Turn**:
   - Each player takes their turn, deciding whether to **Hit** or **Stand**.
   - If the player exceeds 21 points, they **bust** and are out of the game.
   - Once the player stands, the dealer’s turn begins.

4. **Dealer's Turn**:
   - The dealer reveals their hidden card and hits until they have 17 or more points.
   - If the dealer busts, all remaining players win.

5. **Determine the Winner**:
   - Players who have the highest score (21 or less) compared to the dealer’s score win the game.
   - If multiple players tie with the highest score, they all win together.

## Game Features

- **Multiple Players**: Supports between **2 to 8 players**.
- **Dealer**: The dealer is selected from the list of players.
- **Interactive Gameplay**: Players interact with the game via **JOptionPane** (GUI).
- **Deck Management**: The deck is dynamically created based on the number of players.
- **Card Value Calculation**: The Ace can be counted as 1 or 11, depending on the player's hand.
- **Win Condition**: Players who get the highest value (without going over 21) win the game.

## Technologies Used

- **Java**: The game is implemented in Java.
- **Swing**: For GUI components (JOptionPane) to interact with the user.
- **Random**: Used to simulate the shuffling and drawing of cards.

## Running the Game

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/blackjack-game.git
   cd blackjack-game
