import javax.swing.*;
import java.awt.*;
import java.awt.event.*;;

public class App implements ActionListener{
private JFrame frame;
private JPanel panel;
private JButton[] buttons = new JButton[9];
private boolean xTurn = true;

public App() {
    frame = new JFrame("X & O");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new JPanel();
    panel.setLayout(new GridLayout(3,3));
    panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

    for(int i = 0; i < 9; i++){
        buttons[i] = new JButton();
        buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
        buttons[i].addActionListener(this);
        panel.add(buttons[i]);
    }

    frame.add(panel, BorderLayout.CENTER);
    frame.setSize(400, 400);
    frame.setVisible(true);
}

public void actionPerformed(ActionEvent e){
    JButton button = (JButton) e.getSource();
    if(xTurn == true){
        button.setText("X");
    }else{
        button.setText("O");
    }
    button.setEnabled(false);
    xTurn = !xTurn;

    check_for_winner();
}

public void check_for_winner(){
    for(int i = 0; i < 3; i ++){
        if (!buttons[i * 3].getText().equals("") && buttons[i * 3].getText().equals(buttons[i * 3 + 1].getText())
        && buttons[i * 3].getText().equals(buttons[i * 3 + 2].getText())){
            JOptionPane.showMessageDialog(frame, buttons[i * 3].getText() + " wins!");
            reset_game();
            return;
        }
    }
    for(int i = 0; i < 3; i++){
        if (!buttons[i].getText().equals("") && buttons[i].getText().equals(buttons[i + 3].getText())
        && buttons[i].getText().equals(buttons[i + 6].getText())){
            JOptionPane.showMessageDialog(frame, buttons[i].getText() + " wins!");
            reset_game();
            return;
        }
    }

    if (!buttons[0].getText().equals("") && buttons[0].getText().equals(buttons[4].getText())
    && buttons[0].getText().equals(buttons[8].getText())) {
        JOptionPane.showMessageDialog(frame, buttons[0].getText() + " wins!");
        reset_game();
        return;
    }

    if (!buttons[2].getText().equals("") && buttons[2].getText().equals(buttons[4].getText())
    && buttons[2].getText().equals(buttons[6].getText())){
        JOptionPane.showMessageDialog(frame, buttons[2].getText() + " wins!");
        reset_game();
        return;
    }

    //Checking for a draw
    boolean draw = true;
    for(int i = 0; i < 9; i++){
        if(buttons[i].isEnabled()){
            draw = false;
            break;
        }
    }
    if(draw == true){
        JOptionPane.showMessageDialog(frame, "It's a draw");
        reset_game();
    }
}

public void reset_game(){
    for(int i = 0; i < 9; i++){
        buttons[i].setText("");
        buttons[i].setEnabled(true);
    }
    xTurn = true;
}
    public static void main(String[] args) throws Exception {
        new App();
    }
}
