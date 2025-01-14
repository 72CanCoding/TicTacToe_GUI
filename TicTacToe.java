import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    private JFrame frame;
    private JButton[][] buttons;
    private boolean isXTurn;

    public TicTacToe() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        isXTurn = true; 

        initializeButtons();

        frame.setVisible(true);
    }

    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.BOLD, 60));
                buttons[row][col] = button;
                frame.add(button);

              
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!button.getText().isEmpty()) {
                            return; 
                        }

                        if (isXTurn) {
                            button.setText("X");
                        } else {
                            button.setText("O");
                        }

                        isXTurn = !isXTurn; 
                        checkWinner();
                    }
                });
            }
        }
    }

    private void checkWinner() {
       
        for (int i = 0; i < 3; i++) {
           
            if (checkLine(buttons[i][0], buttons[i][1], buttons[i][2])) return;

          
            if (checkLine(buttons[0][i], buttons[1][i], buttons[2][i])) return;
        }

       
        if (checkLine(buttons[0][0], buttons[1][1], buttons[2][2])) return;
        if (checkLine(buttons[0][2], buttons[1][1], buttons[2][0])) return;

        
        if (isDraw()) {
            JOptionPane.showMessageDialog(frame, "It's a draw!");
            resetBoard();
        }
    }

    private boolean checkLine(JButton b1, JButton b2, JButton b3) {
        if (!b1.getText().isEmpty() && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText())) {
            JOptionPane.showMessageDialog(frame, b1.getText() + " wins!");
            resetBoard();
            return true;
        }
        return false;
    }

    private boolean isDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        isXTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
