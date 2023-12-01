import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener {
    private final int SCREEN_WIDTH = 300;
    private final int SCREEN_HEIGHT = 300;
    private final int UNIT_SIZE = 10;
    private final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    private final int DELAY = 75;
    private final int[] x = new int[GAME_UNITS];
    private final int[] y = new int[GAME_UNITS];
    private int bodyParts = 6;
    private int applesEaten = 0;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;
    private final Random random;
    private JButton restartButton;

    public SnakeGame() {
        random = new Random();
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        placeApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void placeApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            placeApple();
        }
    }

    public void checkCollision() {
        // Check if the head collides with the body
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }
        // Check if the head touches the left border
        if (x[0] < 0) {
            running = false;
        }
        // Check if the head touches the right border
        if (x[0] >= SCREEN_WIDTH) {
            running = false;
        }
        // Check if the head touches the top border
        if (y[0] < 0) {
            running = false;
        }
        // Check if the head touches the bottom border
        if (y[0] >= SCREEN_HEIGHT) {
            running = false;
        }
        if (!running) {
            timer.stop();
            addRestartButton();
        }
    }

    public void gameOver(Graphics g) {
        // Game Over text
        String message = "Game Over";
        Font font = new Font("Helvetica", Font.BOLD, 30);
        g.setColor(Color.red);
        g.setFont(font);
        g.drawString(message, SCREEN_WIDTH / 2 - 85, SCREEN_HEIGHT / 2 - 15);
        // Display the score
        String scoreMessage = "Score: " + applesEaten;
        Font scoreFont = new Font("Helvetica", Font.PLAIN, 20);
        g.setFont(scoreFont);
        g.drawString(scoreMessage, SCREEN_WIDTH / 2 - 40, SCREEN_HEIGHT / 2 + 20);
    }

    public void addRestartButton() {
        restartButton = new JButton("Restart");
        restartButton.setBounds(SCREEN_WIDTH / 2 - 60, SCREEN_HEIGHT / 2 + 50, 120, 30);
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(restartButton);
                startGame();
            }
        });
        add(restartButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollision();
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            // Draw the apple
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // Draw the snake
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }

            // Display the score
            g.setColor(Color.white);
            g.setFont(new Font("Helvetica", Font.PLAIN, 15));
            g.drawString("Score: " + applesEaten, 10, 20);
        } else {
            gameOver(g);
        }
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        // Create a JFrame and add the SnakeGame panel to it.
        JFrame frame = new JFrame("Snake Game");
        SnakeGame game = new SnakeGame();
        frame.add(game);

        // Configure the frame.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
