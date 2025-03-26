package Crazy8s;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Main application class for Crazy 8s card game using Swing
 */
public class Crazy8sSwingApp extends JFrame {
    
    private Player player;
    private Bot bot;
    private DeckOfCards deck;
    private Card topCard;
    
    private JPanel playerHandPanel;
    private JPanel gameTablePanel;
    private JLabel topCardLabel;
    private JLabel statusLabel;
    private JLabel botCardsLabel;
    
    /**
     * Creates the main application window
     */
    public Crazy8sSwingApp() {
        // Set up the frame
        setTitle("Crazy 8s Card Game");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Set up the game
        setupGame();
        
        // Set up UI components
        setupUI();
        
        // Make the frame visible
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Sets up the game components
     */
    private void setupGame() {
        // Initialize the player - use direct console input as a fallback
        String playerName = "Player"; // Default name
        
        try {
            // Try to show a dialog for name input
            playerName = JOptionPane.showInputDialog(null, 
                    "Enter your name:", "Player Setup", JOptionPane.QUESTION_MESSAGE);
            
            // If dialog was canceled or empty name entered, use default
            if (playerName == null || playerName.trim().isEmpty()) {
                playerName = "Player";
            }
        } catch (Exception e) {
            // If dialog fails, fall back to console input
            System.out.print("Enter your name: ");
            try {
                playerName = new java.util.Scanner(System.in).nextLine();
                if (playerName.trim().isEmpty()) {
                    playerName = "Player";
                }
            } catch (Exception e2) {
                System.out.println("Using default name: Player");
            }
        }
        
        player = new Player();
        player.setName(playerName);
        
        // Initialize the bot
        bot = new Bot();
        
        // Initialize the deck
        deck = new DeckOfCards();
        deck.shuffle();
        
        // Deal cards
        deck.dealOut(player, bot);
        
        // Play first card
        deck.playFirst();
        topCard = deck.getPile()[deck.getPileNumber() - 1];
    }
    
    /**
     * Sets up the user interface
     */
    private void setupUI() {
        // Create the game table panel (center)
        gameTablePanel = new JPanel();
        gameTablePanel.setLayout(new BorderLayout());
        gameTablePanel.setBackground(new Color(0, 100, 0)); // Dark green
        gameTablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Top card display
        JPanel topCardPanel = new JPanel();
        topCardPanel.setBackground(new Color(0, 100, 0));
        topCardLabel = new JLabel(createCardText(topCard));
        topCardLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topCardLabel.setForeground(Color.WHITE);
        topCardPanel.add(new JLabel("Top Card: "));
        topCardPanel.add(topCardLabel);
        
        // Bot cards display
        JPanel botPanel = new JPanel();
        botPanel.setBackground(new Color(0, 100, 0));
        botCardsLabel = new JLabel("Bot has " + bot.getNumCards() + " cards");
        botCardsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        botCardsLabel.setForeground(Color.WHITE);
        botPanel.add(botCardsLabel);
        
        // Add components to game table
        gameTablePanel.add(botPanel, BorderLayout.NORTH);
        gameTablePanel.add(topCardPanel, BorderLayout.CENTER);
        
        // Create player hand panel (bottom)
        playerHandPanel = new JPanel();
        playerHandPanel.setBackground(new Color(0, 120, 0)); // Lighter green
        updatePlayerHandDisplay();
        
        // Create control panel
        JPanel controlPanel = new JPanel();
        JButton drawButton = new JButton("Draw Card");
        JButton passButton = new JButton("Pass");
        statusLabel = new JLabel("Your turn. Play a card, draw, or pass.");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Add action listeners
        drawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleDrawCard();
            }
        });
        
        passButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handlePass();
            }
        });
        
        controlPanel.add(drawButton);
        controlPanel.add(passButton);
        controlPanel.add(statusLabel);
        
        // Add panels to the main frame
        add(new JLabel(player.getName() + "'s Hand:", SwingConstants.CENTER), BorderLayout.NORTH);
        add(gameTablePanel, BorderLayout.CENTER);
        add(playerHandPanel, BorderLayout.SOUTH);
        add(controlPanel, BorderLayout.NORTH);
    }
    
    /**
     * Updates the display of player's hand
     */
    private void updatePlayerHandDisplay() {
        playerHandPanel.removeAll();
        playerHandPanel.setLayout(new FlowLayout());
        
        for (int i = 0; i < player.getNumCards(); i++) {
            Card card = player.getHand()[i];
            if (card != null) {
                JButton cardButton = createCardButton(card, i);
                playerHandPanel.add(cardButton);
            }
        }
        
        playerHandPanel.revalidate();
        playerHandPanel.repaint();
    }
    
    /**
     * Creates a button representing a card
     */
    private JButton createCardButton(Card card, int index) {
        JButton button = new JButton(createCardText(card));
        button.setPreferredSize(new Dimension(120, 160));
        button.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Set card color based on suit
        if (card.getSuit() == Suit.Hearts || card.getSuit() == Suit.Diamonds) {
            button.setForeground(Color.RED);
        } else {
            button.setForeground(Color.BLACK);
        }
        
        // Add action listener
        final int cardIndex = index;
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleCardPlay(cardIndex);
            }
        });
        
        return button;
    }
    
    /**
     * Creates a text representation of a card
     */
    private String createCardText(Card card) {
        String valueStr = getCardValueString(card.getValue());
        String suitStr = getSuitSymbol(card.getSuit());
        
        return "<html><center>" + valueStr + "<br><br><font size='+3'>" + suitStr + "</font></center></html>";
    }
    
    /**
     * Returns a string for the card's value
     */
    private String getCardValueString(int value) {
        switch (value) {
            case 1: return "A";
            case 11: return "J";
            case 12: return "Q";
            case 13: return "K";
            default: return String.valueOf(value);
        }
    }
    
    /**
     * Returns a symbol for the card's suit
     */
    private String getSuitSymbol(Suit suit) {
        switch (suit) {
            case Hearts: return "♥";
            case Diamonds: return "♦";
            case Clubs: return "♣";
            case Spades: return "♠";
            default: return "?";
        }
    }
    
    /**
     * Handles playing a card
     */
    private void handleCardPlay(int cardIndex) {
        Card selectedCard = player.getHand()[cardIndex];
        Card currentTopCard = deck.getPile()[deck.getPileNumber() - 1];
        
        // Check if the card can be played
        if (selectedCard.getSuit().equals(currentTopCard.getSuit()) || 
            selectedCard.getValue() == currentTopCard.getValue() || 
            selectedCard.getValue() == 8) {
            
            // Handle wild card (8)
            if (selectedCard.getValue() == 8) {
                String[] options = {"Hearts", "Diamonds", "Clubs", "Spades"};
                int choice = JOptionPane.showOptionDialog(this,
                    "Choose a suit for your wild card:",
                    "Select Suit",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
                
                Suit selectedSuit = Suit.Hearts; // Default
                switch (choice) {
                    case 0: selectedSuit = Suit.Hearts; break;
                    case 1: selectedSuit = Suit.Diamonds; break;
                    case 2: selectedSuit = Suit.Clubs; break;
                    case 3: selectedSuit = Suit.Spades; break;
                }
                
                // Play the card
                deck.play(player, cardIndex, player.getHand());
                
                // Set the suit
                deck.getPile()[deck.getPileNumber() - 1].setSuit(selectedSuit);
                topCard = deck.getPile()[deck.getPileNumber() - 1];
                statusLabel.setText("You played an 8 and changed suit to " + selectedSuit);
            } else {
                // Play a regular card
                deck.play(player, cardIndex, player.getHand());
                topCard = deck.getPile()[deck.getPileNumber() - 1];
                statusLabel.setText("You played " + selectedCard.toString());
            }
            
            // Update displays
            topCardLabel.setText(createCardText(topCard));
            updatePlayerHandDisplay();
            
            // Check if player won
            if (player.getNumCards() == 0) {
                JOptionPane.showMessageDialog(this, 
                    "Congratulations! You won the game!",
                    "Victory",
                    JOptionPane.INFORMATION_MESSAGE);
                resetGame();
                return;
            }
            
            // Bot's turn
            botTurn();
        } else {
            // Invalid play
            JOptionPane.showMessageDialog(this,
                "That card cannot be played. It must match the suit or value of the top card.",
                "Invalid Move",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Handles drawing a card
     */
    private void handleDrawCard() {
        if (player.getNumCards() < player.getMaxCards()) {
            player.getHand()[player.getNumCards()] = deck.deal();
            player.setNumCards(player.getNumCards() + 1);
            
            statusLabel.setText("You drew a card");
            updatePlayerHandDisplay();
            
            // Bot's turn
            botTurn();
        } else {
            JOptionPane.showMessageDialog(this,
                "Your hand is full, you cannot draw any more cards.",
                "Hand Full",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Handles passing turn
     */
    private void handlePass() {
        statusLabel.setText("You passed your turn");
        
        // Bot's turn
        botTurn();
    }
    
    /**
     * Bot plays its turn
     */
    private void botTurn() {
        // Add a short delay for better UX
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int playIndex = bot.botPlay(deck);
                
                if (playIndex == 14) {
                    // Bot draws a card
                    if (bot.getNumCards() < bot.getMaxCards()) {
                        bot.getHand()[bot.getNumCards()] = deck.deal();
                        bot.setNumCards(bot.getNumCards() + 1);
                        statusLabel.setText("Bot drew a card");
                    } else {
                        statusLabel.setText("Bot passed");
                    }
                } else {
                    // Bot plays a card
                    Card playedCard = bot.getHand()[playIndex];
                    
                    // Handle wild card (8)
                    if (playedCard.getValue() == 8) {
                        Suit preferredSuit = bot.prefered();
                        deck.play(bot, playIndex, bot.getHand());
                        
                        // Set the preferred suit
                        deck.getPile()[deck.getPileNumber() - 1].setSuit(preferredSuit);
                        topCard = deck.getPile()[deck.getPileNumber() - 1];
                        statusLabel.setText("Bot played an 8 and changed suit to " + preferredSuit);
                    } else {
                        deck.play(bot, playIndex, bot.getHand());
                        topCard = deck.getPile()[deck.getPileNumber() - 1];
                        statusLabel.setText("Bot played " + topCard.toString());
                    }
                    
                    // Update displays
                    topCardLabel.setText(createCardText(topCard));
                    
                    // Check if bot won
                    if (bot.getNumCards() == 0) {
                        JOptionPane.showMessageDialog(null, 
                            "The bot won this time. Better luck next game!",
                            "Game Over",
                            JOptionPane.INFORMATION_MESSAGE);
                        resetGame();
                        return;
                    }
                }
                
                // Update bot cards count
                botCardsLabel.setText("Bot has " + bot.getNumCards() + " cards");
            }
        });
        
        timer.setRepeats(false);
        timer.start();
    }
    
    /**
     * Resets the game for a new round
     */
    private void resetGame() {
        // Reset game state
        deck = new DeckOfCards();
        deck.shuffle();
        
        player.clear();
        bot.clear();
        
        deck.dealOut(player, bot);
        
        deck.playFirst();
        topCard = deck.getPile()[deck.getPileNumber() - 1];
        
        // Update UI
        topCardLabel.setText(createCardText(topCard));
        botCardsLabel.setText("Bot has " + bot.getNumCards() + " cards");
        statusLabel.setText("New game started. Your turn.");
        updatePlayerHandDisplay();
    }
    
    /**
     * Main method to launch the application
     */
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Launch the application on the event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Crazy8sSwingApp();
            }
        });
    }
}
